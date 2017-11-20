package com.tools.jar.jstat;

import com.tools.jar.MonitorStatus;

import java.util.List;
import java.util.Map;

/**
 * @author wangshang
 */
public class JstatCollections {

    /**
     * 监控的状态码
     * 成功：200
     * 失败：100
     */
    private Integer code = MonitorStatus.SUCCESS.getValue();

    /**
     * jstat 监控结果集
     */
    private List<Map<String, String>> jstatModels;

    /**
     * 监控异常
     */
    private String monitorException;

    @Override
    public String toString() {
        return "JstatCollections{" +
                "code=" + code +
                ", jstatModels=" + jstatModels +
                ", monitorException='" + monitorException + '\'' +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<Map<String, String>> getJstatModels() {
        return jstatModels;
    }

    public void setJstatModels(List<Map<String, String>> jstatModels) {
        this.jstatModels = jstatModels;
    }

    public String getMonitorException() {
        return monitorException;
    }

    public void setMonitorException(String monitorException) {
        this.monitorException = monitorException;
    }

}
