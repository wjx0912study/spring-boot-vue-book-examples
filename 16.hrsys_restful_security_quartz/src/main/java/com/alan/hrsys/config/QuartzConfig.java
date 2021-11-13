package com.alan.hrsys.config;

import com.alan.hrsys.util.task.TestJob1;
import com.alan.hrsys.util.task.TestJob2;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    //对任务1进行配置
    public class Job1 {
        @Bean
        public JobDetail createJobDetail1() {
            return JobBuilder.newJob(TestJob1.class).withIdentity("TestJob1").storeDurably().build();
        }

        @Bean
        public Trigger createTrigger1() {
            SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(2)  //设置时间周期单位秒
                    .repeatForever();//执行次数:无限
            SimpleTrigger trigger = TriggerBuilder.newTrigger().forJob(createJobDetail1())
                    .withIdentity("TestJob1Trigger")
                    .withSchedule(scheduleBuilder)
                    .build();
            return trigger;
        }
    }

    //对任务2进行配置
    public class Job2 {
        @Bean
        public JobDetail testJob2() {
            return JobBuilder.newJob(TestJob2.class).withIdentity("TestJob2").storeDurably().build();
        }

        @Bean
        public Trigger testTrigger2() {
//            SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                    .withIntervalInSeconds(4)  //设置时间周期单位秒
//                    .withRepeatCount(3);//执行次数：3次
//            SimpleTrigger trigger= TriggerBuilder.newTrigger().forJob(testJob2())
//                    .withIdentity("TestJob2Trigger")
//                    .withSchedule(scheduleBuilder)
//                    .build();
//            return trigger;
            // 基于 Quartz Cron 表达式的调度计划的构造器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/4 * * * * ?");
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .forJob(testJob2())
                    .withIdentity("quartzJob02Trigger")
                    .withSchedule(scheduleBuilder)
                    .build();
            return trigger;
        }
    }
}