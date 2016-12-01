package com.bidanet.bdcms.plugin.timeTask;


import com.alibaba.fastjson.JSONObject;
import com.bidanet.bdcms.plugin.timeTask.entity.TimeTaskBean;
import com.bidanet.bdcms.plugin.timeTask.exception.TaskExecutorException;

/**
 * 任务处理器
 */
public interface TaskExecutor {
    void execTask(JSONObject jsonObject) throws TaskExecutorException;
    void errorExecTask(TimeTaskBean timeTask,Exception e);
}
