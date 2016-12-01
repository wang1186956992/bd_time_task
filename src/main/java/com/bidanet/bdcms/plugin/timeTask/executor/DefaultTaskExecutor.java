package com.bidanet.bdcms.plugin.timeTask.executor;

import com.alibaba.fastjson.JSONObject;
import com.bidanet.bdcms.plugin.timeTask.TaskExecutor;
import com.bidanet.bdcms.plugin.timeTask.entity.TimeTaskBean;
import com.bidanet.bdcms.plugin.timeTask.exception.TaskExecutorException;
import org.springframework.stereotype.Service;

/**
 * 默认处理器，当找不到处理器的时候会调用默认处理器
 */
public class DefaultTaskExecutor implements TaskExecutor {


    @Override
    public void execTask(JSONObject jsonObject) throws TaskExecutorException {
        //定时任务执行的内容
    }

    @Override
    public void errorExecTask(TimeTaskBean timeTask, Exception e) {
        //当定时任务执行失败的时候执行的操作
    }
}
