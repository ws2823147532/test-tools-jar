package com.tools.jar;

/**
 * @author wangshang
 */
public enum MonitorStatus {

    /**
     * success
     */
    SUCCESS(200),

    /**
     * failed
     */
    FAILED(100);

    private int value;

    MonitorStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
