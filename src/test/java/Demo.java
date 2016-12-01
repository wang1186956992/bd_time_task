import com.bidanet.bdcms.plugin.timeTask.TimeTaskBuilder;
import com.bidanet.bdcms.plugin.timeTask.entity.TimeTaskBean;

/**
 * Created by xuejike on 2016/12/1.
 */
public class Demo {
    public void createTimeTask(){
        TimeTaskBean save = TimeTaskBuilder.create("task1", "订单超时处理:sss")
                .addParam("id", 10).execTime(10000000000000L).save();
    }
}
