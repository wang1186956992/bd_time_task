#苏州必答网络科技有限公司-定时任务模块-使用说明[![](https://jitpack.io/v/xuejike/bd_time_task.svg)](https://jitpack.io/#xuejike/bd_time_task)
##1.引库
###添加Maven库
```xml
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
```

```xml


    <dependency>
  	    <groupId>com.github.xuejike</groupId>
  	    <artifactId>bd_time_task</artifactId>
  	    <version>v0.1.1</version>
  	</dependency>
```


##2.配置
```xml
<bean class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
        <property name="triggers">
            <list>
                <ref bean="taskTrigger" />
            </list>
        </property>

    </bean>
    
  <bean id="taskTrigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
        <property name="jobDetail" ref="taskJobDetail"/>
        <!--定义执行时间-->
        <property name="cronExpression" value="* 0/1 * * * ?" />
    </bean>

    <bean id="taskJobDetail" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
        <property name="targetObject" ref="taskJob" />
        <property name="targetMethod" value="execute" />
        <property name="concurrent" value="false" />
        <!-- 是否允许任务并发执行。当值为false时，表示必须等到前一个线程处理完毕后才再启一个新的线程 -->
    </bean>

    <bean id="taskJob" class="com.bidanet.bdcms.plugin.timeTask.TimeTask" >
        <!--执行分页-->
            <property name="execPageSize" value="1000"/>
        <!--当找不到 执行器的时候 会执行默认执行器-->
        <property name="defaultTaskExecutor" ref="defaultTaskExecutor"/>
    </bean>
    <!--定义默认执行器-->
    <bean id="defaultTaskExecutor" class="com.bidanet.bdcms.plugin.timeTask.executor.DefaultTaskExecutor"/>
```

##3.添加任务处理器
###定义任务处理器，并注入到Spring容器中
实现接口TaskExecutor
```java
/**
 * 默认处理器，当找不到处理器的时候会调用默认处理器
 */
@Service("timeTask.defaultTask") //该Service的值为taskCode的值
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
```


##4.添加定时任务
```java

   TimeTaskBean save = TimeTaskBuilder.create("task1", "订单超时处理:sss")
                .addParam("id", 10).execTime(10000000000000L).save();

```