package com.bidanet.bdcms.plugin.timeTask.service.impl;

import com.bidanet.bdcms.core.dao.Dao;
import com.bidanet.bdcms.core.service.impl.BaseServiceImpl;

import com.bidanet.bdcms.plugin.timeTask.dao.TimeTaskDao;
import com.bidanet.bdcms.plugin.timeTask.entity.TimeTaskBean;
import com.bidanet.bdcms.plugin.timeTask.entity.status.TaskStatus;
import com.bidanet.bdcms.plugin.timeTask.service.TimeTaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by xuejike on 2016/11/29.
 */
@org.springframework.stereotype.Service
public class TimeTaskServiceImpl extends BaseServiceImpl<TimeTaskBean> implements TimeTaskService {


    @Autowired
    private TimeTaskDao dao;

    @Override
    protected Dao getDao() {
        return dao;
    }

    @Override
    public List<TimeTaskBean> getNoExecTimeTask(int pageNo, int pageSize){
        long time = new Date().getTime();
        List<TimeTaskBean> list = dao.findByHqlPage("select * from TimeTaskBean where execTime < ? and status = ?", pageNo, pageSize, time, TaskStatus.wait);

        return list;

    }

    @Override
    public void timeTaskFinishT(long id) {
        TimeTaskBean timeTaskBean = dao.get(id);
        timeTaskBean.setRealExecTime(new Date().getTime());
        timeTaskBean.setStatus(TaskStatus.finish);
        dao.update(timeTaskBean);
    }

    @Override
    public void timeTaskErrorT(long id, String msg) {
        TimeTaskBean timeTaskBean = dao.get(id);
        timeTaskBean.setRealExecTime(new Date().getTime());
        timeTaskBean.setStatus(TaskStatus.error);
        timeTaskBean.setError(msg);
        dao.update(timeTaskBean);
    }




}
