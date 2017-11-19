package com.tools.jar.jstat;

/**
 * Jstat可用选项 以及监控数据字段含义
 * <p>
 * class: Displays statistics about the behavior of the class loader.
 * compiler: Displays statistics about the behavior of the Java HotSpot VM Just-in-Time compiler.
 * gc: Displays statistics about the behavior of the garbage collected heap.
 * gccapacity: Displays statistics about the capacities of the generations and their corresponding spaces.
 * gccause: Displays a summary about garbage collection statistics (same as -gcutil), with the cause of the last and current (when applicable) garbage collection events.
 * gcnew: Displays statistics of the behavior of the new generation.
 * gcnewcapacity: Displays statistics about the sizes of the new generations and its corresponding spaces.
 * gcold: Displays statistics about the behavior of the old generation and metaspace statistics.
 * gcoldcapacity: Displays statistics about the sizes of the old generation.
 * gcmetacapacity: Displays statistics about the sizes of the metaspace.
 * gcutil: Displays a summary about garbage collection statistics.
 * printcompilation: Displays Java HotSpot VM compilation method statistics.
 * <p>
 */

/**
 * -class option
 * Class loader statistics.
 * Loaded: Number of classes loaded.
 * Bytes: Number of kBs loaded.
 * Unloaded: Number of classes unloaded.
 * Bytes: Number of Kbytes unloaded.
 * Time: Time spent performing class loading and unloading operations.
 * -compiler option
 * Java HotSpot VM Just-in-Time compiler statistics.
 * Compiled: Number of compilation tasks performed.
 * Failed: Number of compilations tasks failed.
 * Invalid: Number of compilation tasks that were invalidated.
 * Time: Time spent performing compilation tasks.
 * FailedType: Compile type of the last failed compilation.
 * FailedMethod: Class name and method of the last failed compilation.
 * -gc option
 * Garbage-collected heap statistics.
 * S0C: Current survivor space 0 capacity (kB).
 * S1C: Current survivor space 1 capacity (kB).
 * S0U: Survivor space 0 utilization (kB).
 * S1U: Survivor space 1 utilization (kB).
 * EC: Current eden space capacity (kB).
 * EU: Eden space utilization (kB).
 * OC: Current old space capacity (kB).
 * OU: Old space utilization (kB).
 * MC: Metaspace capacity (kB).
 * MU: Metacspace utilization (kB).
 * CCSC: Compressed class space capacity (kB).
 * CCSU: Compressed class space used (kB).
 * YGC: Number of young generation garbage collection events.
 * YGCT: Young generation garbage collection time.
 * FGC: Number of full GC events.
 * FGCT: Full garbage collection time.
 * GCT: Total garbage collection time.
 * -gccapacity option
 * Memory pool generation and space capacities.
 * NGCMN: Minimum new generation capacity (kB).
 * NGCMX: Maximum new generation capacity (kB).
 * NGC: Current new generation capacity (kB).
 * S0C: Current survivor space 0 capacity (kB).
 * S1C: Current survivor space 1 capacity (kB).
 * EC: Current eden space capacity (kB).
 * OGCMN: Minimum old generation capacity (kB).
 * OGCMX: Maximum old generation capacity (kB).
 * OGC: Current old generation capacity (kB).
 * OC: Current old space capacity (kB).
 * MCMN: Minimum metaspace capacity (kB).
 * MCMX: Maximum metaspace capacity (kB).
 * MC: Metaspace capacity (kB).
 * CCSMN: Compressed class space minimum capacity (kB).
 * CCSMX: Compressed class space maximum capacity (kB).
 * CCSC: Compressed class space capacity (kB).
 * YGC: Number of young generation GC events.
 * FGC: Number of full GC events.
 * -gccause option
 * This option displays the same summary of garbage collection statistics as the -gcutil option, but includes the causes of the last garbage collection event and (when applicable) the current garbage collection event. In addition to the columns listed for -gcutil, this option adds the following columns.
 * LGCC: Cause of last garbage collection
 * GCC: Cause of current garbage collection
 * -gcnew option
 * New generation statistics.
 * S0C: Current survivor space 0 capacity (kB).
 * S1C: Current survivor space 1 capacity (kB).
 * S0U: Survivor space 0 utilization (kB).
 * S1U: Survivor space 1 utilization (kB).
 * TT: Tenuring threshold.
 * MTT: Maximum tenuring threshold.
 * DSS: Desired survivor size (kB).
 * EC: Current eden space capacity (kB).
 * EU: Eden space utilization (kB).
 * YGC: Number of young generation GC events.
 * YGCT: Young generation garbage collection time.
 * -gcnewcapacity option
 * New generation space size statistics.
 * NGCMN: Minimum new generation capacity (kB).
 * NGCMX: Maximum new generation capacity (kB).
 * NGC: Current new generation capacity (kB).
 * S0CMX: Maximum survivor space 0 capacity (kB).
 * S0C: Current survivor space 0 capacity (kB).
 * S1CMX: Maximum survivor space 1 capacity (kB).
 * S1C: Current survivor space 1 capacity (kB).
 * ECMX: Maximum eden space capacity (kB).
 * EC: Current eden space capacity (kB).
 * YGC: Number of young generation GC events.
 * FGC: Number of full GC events.
 * -gcold option
 * Old generation and metaspace behavior statistics.
 * MC: Metaspace capacity (kB).
 * MU: Metaspace utilization (kB).
 * CCSC: Compressed class space capacity (kB).
 * CCSU: Compressed class space used (kB).
 * OC: Current old space capacity (kB).
 * OU: Old space utilization (kB).
 * YGC: Number of young generation GC events.
 * FGC: Number of full GC events.
 * FGCT: Full garbage collection time.
 * GCT: Total garbage collection time.
 * -gcoldcapacity option
 * Old generation size statistics.
 * OGCMN: Minimum old generation capacity (kB).
 * OGCMX: Maximum old generation capacity (kB).
 * OGC: Current old generation capacity (kB).
 * OC: Current old space capacity (kB).
 * YGC: Number of young generation GC events.
 * FGC: Number of full GC events.
 * FGCT: Full garbage collection time.
 * GCT: Total garbage collection time.
 * -gcmetacapacity option
 * Metaspace size statistics.
 * MCMN: Minimum metaspace capacity (kB).
 * MCMX: Maximum metaspace capacity (kB).
 * MC: Metaspace capacity (kB).
 * CCSMN: Compressed class space minimum capacity (kB).
 * CCSMX: Compressed class space maximum capacity (kB).
 * YGC: Number of young generation GC events.
 * FGC: Number of full GC events.
 * FGCT: Full garbage collection time.
 * GCT: Total garbage collection time.
 * -gcutil option
 * Summary of garbage collection statistics.
 * S0: Survivor space 0 utilization as a percentage of the space's current capacity.
 * S1: Survivor space 1 utilization as a percentage of the space's current capacity.
 * E: Eden space utilization as a percentage of the space's current capacity.
 * O: Old space utilization as a percentage of the space's current capacity.
 * M: Metaspace utilization as a percentage of the space's current capacity.
 * CCS: Compressed class space utilization as a percentage.
 * YGC: Number of young generation GC events.
 * YGCT: Young generation garbage collection time.
 * FGC: Number of full GC events.
 * FGCT: Full garbage collection time.
 * GCT: Total garbage collection time.
 * -printcompilation option
 * Java HotSpot VM compiler method statistics.
 * Compiled: Number of compilation tasks performed by the most recently compiled method.
 * Size: Number of bytes of byte code of the most recently compiled method.
 * Type: Compilation type of the most recently compiled method.
 * Method: Class name and method name identifying the most recently compiled method. Class name uses slash (/) instead of dot (.) as a name space separator. Method name is the method within the specified class. The format for these two fields is consistent with the HotSpot -XX:+PrintCompilation option.
 * <p>
 * #https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jstat.html#BEHHGFAE
 */
public enum JstatOption {

    /**
     * Displays statistics about the behavior of the class loader.
     */
    CLASS("-class", "class"),
    /**
     * Displays statistics about the behavior of the Java HotSpot VM Just-in-Time compiler.
     */
    COMPILER("-compiler", "compiler"),
    /**
     * Displays statistics about the behavior of the garbage collected heap.
     */
    GC("-gc", "gc"),
    /**
     * Displays statistics about the capacities of the generations and their corresponding spaces.
     */
    GCCAPACITY("-gccapacity", "gccapacity"),
    /**
     * Displays a summary about garbage collection statistics (same as -gcutil),
     * with the cause of the last and current (when applicable) garbage collection events.
     */
    GCCAUSE("-gccause", "gccause"),
    /**
     * Displays statistics about the sizes of the metaspace.
     */
    GCMETACAPACITY("-gcmetacapacity", "gcmetacapacity"),
    /**
     * Displays statistics of the behavior of the new generation.
     */
    GCNEW("-gcnew", "gcnew"),
    /**
     * Displays statistics about the sizes of the new generations and its corresponding spaces.
     */
    GCNEWCAPACITY("-gcnewcapacity", "gcnewcapacity"),
    /**
     * Displays statistics about the behavior of the old generation and metaspace statistics.
     */
    GCOLD("-gcold", "gcold"),
    /**
     * Displays statistics about the sizes of the old generation.
     */
    GCOLDCAPACITY("-gcoldcapacity", "gcoldcapacity"),
    /**
     * Displays a summary about garbage collection statistics.
     */
    GCUTIL("-gcutil", "gcutil"),
    /**
     * Displays Java HotSpot VM compilation method statistics.
     */
    PRINTCOMPILATION("-printcompilation", "printcompilation");

    private String option;

    private String name;

    JstatOption(String option, String name) {
        this.option = option;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getOption() {
        return option;
    }
}
