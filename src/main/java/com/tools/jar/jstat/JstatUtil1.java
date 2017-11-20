package com.tools.jar.jstat;

import com.google.common.collect.Lists;
import com.tools.jar.MonitorStatus;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.VmIdentifier;
import sun.jvmstat.monitor.event.HostEvent;
import sun.jvmstat.monitor.event.HostListener;
import sun.jvmstat.monitor.event.VmStatusChangeEvent;
import sun.tools.jstat.Arguments;
import sun.tools.jstat.OptionFormat;
import sun.tools.jstat.OptionOutputFormatter;
import sun.tools.jstat.OutputFormatter;

import java.util.List;
import java.util.Map;

/**
 * Jstat 模拟工具实现
 *
 * @author wangshang
 */
public class JstatUtil1 {
    private static Arguments arguments;

    /**
     * 执行监控，返回监控的数据
     * <p>
     * Timestamp:   Displays a timestamp column as the first column of output.
     * The time stamp is the time since the start time of the target JVM.
     * S0C:         Current survivor space 0 capacity (kB).
     * S1C:         Current survivor space 1 capacity (kB).
     * S0U:         Survivor space 0 utilization (kB).
     * S1U:         Survivor space 1 utilization (kB).
     * EC:          Current eden space capacity (kB).
     * EU:          Eden space utilization (kB).
     * OC:          Current old space capacity (kB).
     * OU:          Old space utilization (kB).
     * MC:          Metaspace capacity (kB).
     * MU:          Metacspace utilization (kB).
     * CCSC:        Compressed class space capacity (kB).
     * CCSU:        Compressed class space used (kB).
     * YGC:         Number of young generation garbage collection events.
     * YGCT:        Young generation garbage collection time.
     * FGC:         Number of full GC events.
     * FGCT:        Full garbage collection time.
     * GCT:         Total garbage collection time.
     *
     * @param args
     * @return
     */
    public static JstatCollections execute(String[] args) {
        try {
            arguments = new Arguments(args);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            Arguments.printUsage(System.err);
            System.exit(1);
        }

        JstatCollections jstatCollections = new JstatCollections();

        try {
            jstatCollections.setJstatModels(logSamples());
            return jstatCollections;
        } catch (MonitorException e) {
            jstatCollections.setCode(MonitorStatus.FAILED.getValue());
            if (e.getMessage() != null) {
                jstatCollections.setMonitorException(e.getMessage());
            } else {
                Throwable cause = e.getCause();
                if ((cause != null) && (cause.getMessage() != null)) {
                    jstatCollections.setMonitorException(cause.getMessage());
                } else {
                    e.printStackTrace();
                }
            }
            return jstatCollections;
        }
    }

    private static List<Map<String, String>> logSamples() throws MonitorException {
        final VmIdentifier vmId = arguments.vmId();
        int interval = arguments.sampleInterval();
        final MonitoredHost monitoredHost =
                MonitoredHost.getMonitoredHost(vmId);
        MonitoredVm monitoredVm = monitoredHost.getMonitoredVm(vmId, interval);
        final JStatLogger1 logger = new JStatLogger1(monitoredVm);
        OutputFormatter formatter = null;

        if (arguments.isSpecialOption()) {
            OptionFormat format = arguments.optionFormat();
            formatter = new OptionOutputFormatter(monitoredVm, format);
        }

        // handle user termination requests by stopping sampling loops
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                logger.stopLogging();
            }
        });

        // handle target termination events for targets other than ourself
        HostListener terminator = new HostListener() {
            @Override
            public void vmStatusChanged(VmStatusChangeEvent ev) {
                Integer lvmid = vmId.getLocalVmId();
                if (ev.getTerminated().contains(lvmid)) {
                    logger.stopLogging();
                } else if (!ev.getActive().contains(lvmid)) {
                    logger.stopLogging();
                }
            }

            @Override
            public void disconnected(HostEvent ev) {
                if (monitoredHost == ev.getMonitoredHost()) {
                    logger.stopLogging();
                }
            }
        };

        if (vmId.getLocalVmId() != 0) {
            monitoredHost.addHostListener(terminator);
        }

        List<Map<String, String>> result = Lists.newArrayList();
        logger.logSamples(formatter,
                arguments.sampleInterval(), arguments.sampleCount(),
                result);

        // detach from host events and from the monitored target jvm
        monitoredHost.removeHostListener(terminator);
        monitoredHost.detach(monitoredVm);

        return result;
    }

}
