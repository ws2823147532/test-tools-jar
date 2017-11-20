package com.tools.jar.jps;

import java.util.Date;

/**
 * 模拟Jps命令获取JVM内存数据的数据模型
 *
 * @author wangshang
 */
public class JpsModel {

    /**
     * local VM identifier
     */
    private Integer lvmid;

    /**
     * 主类名
     */
    private String mainClassname;

    /**
     * 全类名
     */
    private String jarFilename;

    /**
     * 应用启动参数列表
     */
    private String args;

    /**
     * JVM 启动参数列表
     */
    private String jvmArgs;

    /**
     * 输出通过flag文件传递到JVM中的参数(.hotspotrc文件或-XX:Flags=所指定的文件
     */
    private String jvmFlags;

    /**
     * 错误信息
     * <p>
     * Note: The VM associated with the current VM id may
     * no longer be running so these queries may fail. We
     * already added the VM id to the output stream above.
     * If one of the queries fails, then we try to add a
     * reasonable message to indicate that the requested
     * info is not available.
     */
    private String errorString;

    /**
     * 异常信息
     * <p>
     * MonitorException 的异常信息
     */
    private String exceptionString;

    /**
     * 监控数据采集时间
     */
    private String captureTime;

    public Integer getLvmid() {
        return lvmid;
    }

    public void setLvmid(Integer lvmid) {
        this.lvmid = lvmid;
    }

    public String getMainClassname() {
        return mainClassname;
    }

    public void setMainClassname(String mainClassname) {
        this.mainClassname = mainClassname;
    }

    public String getJarFilename() {
        return jarFilename;
    }

    public void setJarFilename(String jarFilename) {
        this.jarFilename = jarFilename;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getJvmArgs() {
        return jvmArgs;
    }

    public void setJvmArgs(String jvmArgs) {
        this.jvmArgs = jvmArgs;
    }

    public String getJvmFlags() {
        return jvmFlags;
    }

    public void setJvmFlags(String jvmFlags) {
        this.jvmFlags = jvmFlags;
    }

    public String getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(String captureTime) {
        this.captureTime = captureTime;
    }

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    public String getExceptionString() {
        return exceptionString;
    }

    public void setExceptionString(String exceptionString) {
        this.exceptionString = exceptionString;
    }

    @Override
    public String toString() {
        return "JpsModel{" +
                "lvmid=" + lvmid +
                ", mainClassname='" + mainClassname + '\'' +
                ", jarFilename='" + jarFilename + '\'' +
                ", args='" + args + '\'' +
                ", jvmArgs='" + jvmArgs + '\'' +
                ", jvmFlags='" + jvmFlags + '\'' +
                ", errorString='" + errorString + '\'' +
                ", exceptionString='" + exceptionString + '\'' +
                ", captureTime='" + captureTime + '\'' +
                '}';
    }
}
