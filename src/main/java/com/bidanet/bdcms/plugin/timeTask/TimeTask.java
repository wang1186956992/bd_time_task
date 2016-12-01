package com.bidanet.bdcms.plugin.timeTask;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bidanet.bdcms.core.common.SpringWebTool;
import com.bidanet.bdcms.plugin.timeTask.entity.TimeTaskBean;
import com.bidanet.bdcms.plugin.timeTask.entity.status.TaskStatus;
import com.bidanet.bdcms.plugin.timeTask.executor.DefaultTaskExecutor;
import com.bidanet.bdcms.plugin.timeTask.service.TimeTaskService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by xuejike on 2016/11/29.
 */

public class TimeTask {

    protected static final Logger logger=Logger.getLogger(TimeTask.class);

    private int execPageSize=100;
    @Autowired
    private TimeTaskService timeTaskService;
    /**
     * 默认处理器
     */
    private TaskExecutor defaultTaskExecutor=new DefaultTaskExecutor();







    public void execute(){

        logger.info("定时任务开始执行");
        int pageNo=1;

        List<TimeTaskBean> list;

        do {
            list= timeTaskService.getNoExecTimeTask(pageNo,execPageSize);

            for (TimeTaskBean temp : list) {
                exec(temp);
            }

            pageNo++;
        }while (list.size()>0);



        logger.info("定时任务执行结束");
    }

    protected void exec(TimeTaskBean timeTaskBean){
        String taskCode = timeTaskBean.getTaskCode();
        TaskExecutor bean = SpringWebTool.getBean(taskCode, TaskExecutor.class);
        if (bean==null){
            bean=defaultTaskExecutor;
        }
        try {
            String params = timeTaskBean.getParams();
            JSONObject jsonObject = JSON.parseObject(params);
            bean.execTask(jsonObject);
            //完成 任务
            timeTaskService.timeTaskFinishT(timeTaskBean.getId());

        } catch (Exception e) {
            logger.trace(e);
            timeTaskService.timeTaskErrorT(timeTaskBean.getId(),e.getMessage());
            bean.errorExecTask(timeTaskBean,e);
////
        }
    }



    public int getExecPageSize() {
        return execPageSize;
    }

    public void setExecPageSize(int execPageSize) {
        this.execPageSize = execPageSize;
    }

    public TaskExecutor getDefaultTaskExecutor() {
        return defaultTaskExecutor;
    }

    public void setDefaultTaskExecutor(TaskExecutor defaultTaskExecutor) {
        this.defaultTaskExecutor = defaultTaskExecutor;
    }
}
