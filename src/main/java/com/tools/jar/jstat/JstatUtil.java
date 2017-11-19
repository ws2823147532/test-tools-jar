package com.tools.jar.jstat;

import com.google.common.collect.Maps;
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

import java.util.Map;

/**
 * Jstat 模拟工具实现
 *
 * @author wangshang
 */
public class JstatUtil {
    private static Arguments arguments;

    /**
     * 执行监控，返回监控的数据
     *
     * Timestamp:   Displays a timestamp column as the first column of output.
     *              The time stamp is the time since the start time of the target JVM.
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
    public static Map<String, String> execute(String[] args) {
        try {
            arguments = new Arguments(args);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            Arguments.printUsage(System.err);
            System.exit(1);
        }

        try {
            return logSamples();
        } catch (MonitorException e) {
            Map<String, String> errorResult = Maps.newHashMap();
            errorResult.put("code", "100");
            if (e.getMessage() != null) {
                errorResult.put("errorMsg", e.getMessage());
                System.err.println(e.getMessage());
            } else {
                Throwable cause = e.getCause();
                if ((cause != null) && (cause.getMessage() != null)) {
                    errorResult.put("errorMsg", cause.getMessage());
                    System.err.println(cause.getMessage());
                } else {
                    e.printStackTrace();
                }
            }
            return errorResult;
        }
    }

    private static Map<String, String> logSamples() throws MonitorException {
        final VmIdentifier vmId = arguments.vmId();
        int interval = arguments.sampleInterval();
        final MonitoredHost monitoredHost =
                MonitoredHost.getMonitoredHost(vmId);
        MonitoredVm monitoredVm = monitoredHost.getMonitoredVm(vmId, interval);
        final JStatLogger logger = new JStatLogger();
        OutputFormatter formatter = null;

        if (arguments.isSpecialOption()) {
            OptionFormat format = arguments.optionFormat();
            formatter = new OptionOutputFormatter(monitoredVm, format);
        }


//        FileOutputStream fdOut = null;
//        try {
//            fdOut = new FileOutputStream("jstat.monitor");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        PrintStream out = null;
//        try {
//            assert fdOut != null;
//            out = new PrintStream(new BufferedOutputStream(fdOut, 128), true, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//        }
        Map<String, String> result = Maps.newHashMap();
        result.put("code", "200");
        logger.logSamples(formatter, result);

        // detach from host events and from the monitored target jvm
        monitoredHost.detach(monitoredVm);

        return result;
    }

}
