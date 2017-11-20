package com.tools.jar.jps;

import com.google.common.collect.Lists;
import com.tools.jar.MonitorStatus;
import sun.jvmstat.monitor.HostIdentifier;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.MonitoredVmUtil;
import sun.jvmstat.monitor.VmIdentifier;
import sun.tools.jps.Arguments;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Jps 工具模拟实现
 *
 * @author wangshang
 */
public class JpsUtil {

    private static Arguments arguments;

    public static JpsCollections execute(String[] args) {
        JpsCollections jpsCollections = new JpsCollections();
        List<JpsModel> jpsModels = Lists.newArrayList();
        try {
            arguments = new Arguments(args);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            Arguments.printUsage(System.err);
            System.exit(1);
        }

        if (arguments.isHelp()) {
            Arguments.printUsage(System.err);
            System.exit(0);
        }

        try {
            HostIdentifier hostId = arguments.hostId();
            MonitoredHost monitoredHost =
                    MonitoredHost.getMonitoredHost(hostId);

            // get the set active JVMs on the specified host.
            Set<Integer> jvms = monitoredHost.activeVms();

            for (Integer jvm : jvms) {
                JpsModel jpsModel = new JpsModel();
                Throwable lastError = null;

                int lvmid = jvm;

                jpsModel.setLvmid(lvmid);

                MonitoredVm vm;
                String vmidString = "//" + lvmid + "?mode=r";

                String errorString = null;

                try {
                    // Note: The VM associated with the current VM id may
                    // no longer be running so these queries may fail. We
                    // already added the VM id to the output stream above.
                    // If one of the queries fails, then we try to add a
                    // reasonable message to indicate that the requested
                    // info is not available.

                    errorString = " -- process information unavailable";
                    VmIdentifier id = new VmIdentifier(vmidString);
                    vm = monitoredHost.getMonitoredVm(id, 0);

                    errorString = " -- main class information unavailable";
                    jpsModel.setMainClassname(MonitoredVmUtil.mainClass(vm,
                            arguments.showLongPaths()));

                    if (arguments.showMainArgs()) {
                        errorString = " -- main args information unavailable";
                        String mainArgs = MonitoredVmUtil.mainArgs(vm);
                        if (mainArgs != null && mainArgs.length() > 0) {
                            jpsModel.setArgs(mainArgs);
                        }
                    }
                    if (arguments.showVmArgs()) {
                        errorString = " -- jvm args information unavailable";
                        String jvmArgs = MonitoredVmUtil.jvmArgs(vm);
                        if (jvmArgs != null && jvmArgs.length() > 0) {
                            jpsModel.setJvmArgs(jvmArgs);
                        }
                    }
                    if (arguments.showVmFlags()) {
                        errorString = " -- jvm flags information unavailable";
                        String jvmFlags = MonitoredVmUtil.jvmFlags(vm);
                        if (jvmFlags != null && jvmFlags.length() > 0) {
                            jpsModel.setJvmFlags(jvmFlags);
                        }
                    }

                    errorString = " -- detach failed";
                    monitoredHost.detach(vm);

                    errorString = null;
                } catch (URISyntaxException e) {
                    // unexpected as vmidString is based on a validated hostid
                    lastError = e;
                    assert false;
                } catch (Exception e) {
                    lastError = e;
                } finally {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");

                    jpsModel.setCaptureTime(simpleDateFormat.format(new Date()));
                    if (errorString != null) {
                        /*
                         * we ignore most exceptions, as there are race
                         * conditions where a JVM in 'jvms' may terminate
                         * before we get a chance to list its information.
                         * Other errors, such as access and I/O exceptions
                         * should stop us from iterating over the complete set.
                         */
                        jpsModel.setErrorString(errorString);
                        if (arguments.isDebug()) {
                            if ((lastError != null)
                                    && (lastError.getMessage() != null)) {
                                jpsModel.setExceptionString(lastError.getMessage());
                            }
                        }
                        if (arguments.printStackTrace() && lastError != null) {
                            lastError.printStackTrace();
                        }
                    }
                }
                jpsModels.add(jpsModel);
            }
        } catch (MonitorException e) {
            jpsCollections.setCode(MonitorStatus.FAILED.getValue());
            if (e.getMessage() != null) {
                System.err.println(e.getMessage());
                jpsCollections.setMonitorException(e.getMessage());
            } else {
                Throwable cause = e.getCause();
                if ((cause != null) && (cause.getMessage() != null)) {
                    System.err.println(cause.getMessage());
                    jpsCollections.setMonitorException(cause.getMessage());
                } else {
                    e.printStackTrace();
                }
            }
        }
        jpsCollections.setJpsModels(jpsModels);
        return jpsCollections;
    }
}
