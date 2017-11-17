package com.tools.jar.models;

import java.util.List;

/**
 * Jps 收集的监控数据结果集
 *
 * @author wangshang
 */
public class JpsCollections {

    /**
     * jps 监控数据实体
     */
    private List<JpsModel> jpsModels;

    /**
     * 监控异常
     */
    private String monitorException;

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
                "jpsModels=" + jpsModels +
                ", monitorException='" + monitorException + '\'' +
                '}';
    }
}
