package com.bidanet.bdcms.plugin.timeTask;

import com.alibaba.fastjson.JSON;
import com.bidanet.bdcms.core.common.SpringWebTool;
import com.bidanet.bdcms.plugin.timeTask.entity.TimeTaskBean;
import com.bidanet.bdcms.plugin.timeTask.entity.status.TaskStatus;
import com.bidanet.bdcms.plugin.timeTask.service.TimeTaskService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 定时任务构造器
 */
public class TimeTaskBuilder {
    protected TimeTaskBean timeTaskBean=new TimeTaskBean();
    protected Map<String,Object> params=new HashMap<String,Object>();

    /**
     * 创建一个定时任务
     * @param taskCode 任务代码
     * @param taskName 任务名称
     * @return
     */
    public static TimeTaskBuilder create(String taskCode,String taskName){
        TimeTaskBuilder builder = new TimeTaskBuilder(taskCode,taskName);
        return builder;
    }
    public static TimeTaskBuilder create(TimeTaskBean timeTaskBean){
        TimeTaskBuilder builder = new TimeTaskBuilder(timeTaskBean.getTaskCode(),timeTaskBean.getTaskName());
        builder.addParams(JSON.parseObject(timeTaskBean.getParams()));
        builder.createInfo("create from id="+timeTaskBean.getId());
        return builder;
    }

    /**
     * 创建一个定时任务
     * @param taskCode 任务代码
     * @param taskName 任务名称
     * @param execTime 执行时间
     * @return
     */
    public static TimeTaskBuilder create(String taskCode,String taskName,Long execTime){
        TimeTaskBuilder builder = new TimeTaskBuilder(taskCode,taskName);
        builder.execTime(execTime);
        return builder;
    }

    /**
     * 创建一个定时任务
     * @param taskCode 任务代码
     * @param taskName 任务名称
     * @param execTime 执行时间
     * @return
     */
    public static TimeTaskBuilder create(String taskCode,String taskName,Date execTime){
        TimeTaskBuilder builder = new TimeTaskBuilder(taskCode,taskName);
        builder.execTime(execTime);
        return builder;
    }


    private TimeTaskBuilder(String taskCode,String taskName) {
        timeTaskBean.setTaskCode(taskCode);
        timeTaskBean.setTaskName(taskName);
        timeTaskBean.setCreateTime(new Date().getTime());
        timeTaskBean.setStatus(TaskStatus.wait);
    }

    /**
     * 设置执行时间
     * @param execTime 时间戳 毫秒级别
     * @return
     */
    public TimeTaskBuilder execTime(Long execTime){
        timeTaskBean.setExecTime(execTime);
        return this;
    }

    /**
     * 设置执行时间
     * @param date 执行时间
     * @return
     */
    public TimeTaskBuilder execTime(Date date){
        return execTime(date.getTime());
    }

    /**
     * 添加执行参数
     * @param key 参数键
     * @param val 参数值
     * @return
     */
    public TimeTaskBuilder addParam(String key,Object val){
        params.put(key, val);
        return this;
    }
    /**
     * 批量添加执行参数
     * @param params 参数Map
     * @return
     */
    public TimeTaskBuilder addParams(Map<String,Object> params){
        params.putAll(params);
        return this;
    }

    public TimeTaskBuilder removeParam(String key){
        params.remove(key);
        return this;
    }

    public TimeTaskBuilder createInfo(String info){
        timeTaskBean.setCreateInfo(info);
        return this;
    }
    /**
     * 保存定时任务
     * @return
     */
    public TimeTaskBean save(){
        timeTaskBean.setParams(JSON.toJSONString(params));
        TimeTaskService service = SpringWebTool.getBean(TimeTaskService.class);
        service.insertT(timeTaskBean);
        return timeTaskBean;
    }


}
