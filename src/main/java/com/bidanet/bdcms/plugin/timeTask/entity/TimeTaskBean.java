package com.bidanet.bdcms.plugin.timeTask.entity;

import com.bidanet.bdcms.plugin.timeTask.entity.status.TaskStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private TaskStatus status;
    private String error;

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
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
