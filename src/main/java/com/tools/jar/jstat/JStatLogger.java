package com.tools.jar.jstat;

import java.util.*;
import java.io.*;

import sun.jvmstat.monitor.*;
import sun.jvmstat.monitor.event.*;
import sun.tools.jstat.OutputFormatter;

import java.util.regex.PatternSyntaxException;

/**
 * Class to sample and output various jvmstat statistics for a target Java
 * a target Java Virtual Machine.
 *
 * @author Brian Doherty
 * @since 1.5
 */
public class JStatLogger {


    public JStatLogger() {
    }

    /**
     * print samples according to the given format.
     */
    public void logSamples(OutputFormatter formatter, Map<String, String> monitorResult)
            throws MonitorException {
        System.out.println(formatter.getHeader());

        String[] headers = formatter.getHeader().split("\\s+");

        System.out.println(formatter.getRow());
        String[] rowItems = formatter.getRow().split("\\s+");

        for (int i = 0; i < headers.length; i++) {

            monitorResult.put(headers[i], rowItems[i+1]);

        }

    }
}
