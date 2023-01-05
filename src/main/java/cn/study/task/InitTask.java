package cn.study.task;

import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.study.constant.CommonConstants;
import cn.study.entity.VCron;
import cn.study.mapper.VCronMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 初始化定时任务
 * @author: zhangcc
 * @create 2022-10-18
 **/
@Slf4j
@Order(2)
@Component
public class InitTask implements ApplicationRunner {

    @Resource
    private VCronMapper vCronMapper;
    @Resource
    private CronUtils cronUtils;

    @Override
    public void run(ApplicationArguments args) {

        List<VCron> taskList = vCronMapper.selectList(Wrappers.<VCron>lambdaQuery()
                .eq(VCron::getStauts, "0")
                .eq(VCron::getDelFlag, CommonConstants.SUCCESS));
        log.info("共发现定时任务有{}个", taskList.size());
        if (null != taskList && taskList.size() > 0) {
            for (VCron task : taskList) {

                CronUtil.schedule(task.getId().toString(), task.getCron(), new Task() {
                    @Override
                    public void execute() {
                        task.setType("0");
                        cronUtils.run(task);
                    }
                });
            }
        }
        CronUtil.setMatchSecond(true);
        CronUtil.start();
    }
}
