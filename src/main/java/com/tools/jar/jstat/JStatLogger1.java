package com.tools.jar.jstat;

import com.google.common.collect.Maps;
import sun.jvmstat.monitor.Monitor;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.StringMonitor;
import sun.tools.jstat.OutputFormatter;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

/**
 * Class to sample and output various jvmstat statistics for a target Java
 * a target Java Virtual Machine.
 *
 * @author Brian Doherty
 * @since 1.5
 */
public class JStatLogger1 {
    private MonitoredVm monitoredVm;
    private volatile boolean active = true;

    public JStatLogger1(MonitoredVm monitoredVm) {
        this.monitoredVm = monitoredVm;
    }

    /**
     * print the monitors that match the given monitor name pattern string.
     */
    public void printNames(String names, Comparator<Monitor> comparator,
                           boolean showUnsupported, PrintStream out)
            throws MonitorException, PatternSyntaxException {

        // get the set of all monitors
        List<Monitor> items = monitoredVm.findByPattern(names);
        Collections.sort(items, comparator);

        for (Monitor m : items) {
            if (!(m.isSupported() || showUnsupported)) {
                continue;
            }
            out.println(m.getName());
        }
    }

    /**
     * print name=value pairs for the given list of monitors.
     */
    public void printSnapShot(String names, Comparator<Monitor> comparator,
                              boolean verbose, boolean showUnsupported,
                              PrintStream out)
            throws MonitorException, PatternSyntaxException {

        // get the set of all monitors
        List<Monitor> items = monitoredVm.findByPattern(names);
        Collections.sort(items, comparator);

        printList(items, verbose, showUnsupported, out);
    }

    /**
     * print name=value pairs for the given list of monitors.
     */
    public void printList(List<Monitor> list, boolean verbose, boolean showUnsupported,
                          PrintStream out)
            throws MonitorException {

        // print out the name of each available counter
        for (Monitor m : list) {

            if (!(m.isSupported() || showUnsupported)) {
                continue;
            }

            StringBuilder buffer = new StringBuilder();
            buffer.append(m.getName()).append("=");

            if (m instanceof StringMonitor) {
                buffer.append("\"").append(m.getValue()).append("\"");
            } else {
                buffer.append(m.getValue());
            }

            if (verbose) {
                buffer.append(" ").append(m.getUnits());
                buffer.append(" ").append(m.getVariability());
                buffer.append(" ").append(m.isSupported() ? "Supported"
                        : "Unsupported");
            }
            out.println(buffer);
        }
    }

    /**
     * method to for asynchronous termination of sampling loops
     */
    public void stopLogging() {
        active = false;
    }

    /**
     * print samples according to the given format.
     */
    public void logSamples(OutputFormatter formatter,
                           int sampleInterval, int sampleCount, List<Map<String, String>> result)
            throws MonitorException {

        long iterationCount = 0;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
        // if printHeader == 0, then only an initial column header is desired.
        String[] headers = formatter.getHeader().split("\\s+");

        while (active) {
            String[] rowItems = formatter.getRow().split("\\s+");

            Map<String, String> monitorResult = Maps.newHashMap();
            for (int i = 0; i < headers.length; i++) {
                monitorResult.put(headers[i], rowItems[i + 1]);
            }
            monitorResult.put("captureTime", simpleDateFormat.format(new Date()));
            result.add(monitorResult);
            // check if we've hit the specified sample count
            if (sampleCount > 0 && ++iterationCount >= sampleCount) {
                break;
            }

            try {
                Thread.sleep(sampleInterval);
            } catch (Exception e) {
            }
        }
    }
}
