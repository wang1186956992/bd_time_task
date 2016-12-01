package com.bidanet.bdcms.plugin.timeTask.entity;

import com.bidanet.bdcms.plugin.timeTask.entity.status.TaskStatus;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * 定时任务实体
 */
@Entity
@Table(name = "_time_task")
public class TimeTaskBean {
    private Long id;
    /**
     * 任务代码
     */
    private String taskCode;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 任务参数，JSON
     */
    private String params;
    /**
     * 计划执行时间
     */
    private Long execTime;
    /**
     * 真正执行时间
     */
    private Long realExecTime;
    /**
     * 创建任务的时间
     */
    private Long createTime;

    private TaskStatus status;
    private String error;
    /**
     * 创建说明
     */
    private String createInfo;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "task_code")
    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    @Column(name = "task_name")
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Column(name = "params" )
    @Type(type = "text")
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Column(name = "exec_time")
    public Long getExecTime() {
        return execTime;
    }

    public void setExecTime(Long execTime) {
        this.execTime = execTime;
    }

    @Column(name = "real_exec_time")
    public Long getRealExecTime() {
        return realExecTime;
    }

    public void setRealExecTime(Long realExecTime) {
        this.realExecTime = realExecTime;
    }

    public TaskStatus getStatus() {
        return status;
    }
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Column(name = "create_info")
    @Type(type = "text")
    public String getCreateInfo() {
        return createInfo;
    }

    public void setCreateInfo(String createInfo) {
        this.createInfo = createInfo;
    }
}
