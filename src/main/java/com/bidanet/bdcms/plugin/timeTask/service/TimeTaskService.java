package com.bidanet.bdcms.plugin.timeTask.service;

import com.bidanet.bdcms.core.service.Service;
import com.bidanet.bdcms.plugin.timeTask.entity.TimeTaskBean;

import java.util.List;

/**
 * Created by xuejike on 2016/11/29.
 */
public interface TimeTaskService extends Service<TimeTaskBean> {
    List<TimeTaskBean> getNoExecTimeTask(int pageNo, int pageSize);

    void timeTaskFinishT(long id);
    void timeTaskErrorT(long id,String msg);
}
