package com.tools.jar.jps;

import com.tools.jar.MonitorStatus;

import java.util.List;

/**
 * Jps 收集的监控数据结果集
 *
 * @author wangshang
 */
public class JpsCollections {

    /**
     * 监控的状态码
     * 成功：200
     * 失败：100
     */
    private Integer code = MonitorStatus.SUCCESS.getValue();

    /**
     * jps 监控数据实体
     */
    private List<JpsModel> jpsModels;

    /**
     * 监控异常
     */
    private String monitorException;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<JpsModel> getJpsModels() {
        return jpsModels;
    }

    public void setJpsModels(List<JpsModel> jpsModels) {
        this.jpsModels = jpsModels;
    }

    public String getMonitorException() {
        return monitorException;
    }

    public void setMonitorException(String monitorException) {
        this.monitorException = monitorException;
    }

    @Override
    public String toString() {
        return "JpsCollections{" +
                "code=" + code +
                ", jpsModels=" + jpsModels +
                ", monitorException='" + monitorException + '\'' +
                '}';
    }
}
