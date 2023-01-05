package cn.study.task;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.Scheduler;
import cn.hutool.cron.task.Task;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.study.constant.CommonConstants;
import cn.study.entity.VCron;
import cn.study.entity.VCronException;
import cn.study.mapper.VCronExceptionMapper;
import cn.study.mapper.VCronMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@AllArgsConstructor
@Slf4j
public class CronUtils {

    @Resource
    private VCronMapper vCronMapper;
    @Resource
    private VCronExceptionMapper vCronExceptionMapper;


    public Boolean runTask(VCron task) {
        log.info("准备添加定时任务:{}", task.getTitle());
        CronUtil.schedule(task.getId().toString(), task.getCron(), new Task() {
            @Override
            public void execute() {
                run(task);
            }
        });

        CronUtil.setMatchSecond(true);//匹配秒的部分
        CronUtil.start();

        Scheduler scheduler = CronUtil.getScheduler();
        if (!scheduler.isStarted()) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;

    }


    public void run(VCron task) {
        log.info("开始执行定时数据：{},任务id:{}", task.getTitle(), task.getId());
        try {
            HttpRequest request = HttpUtil.createGet(task.getClassPath());
            request.execute();
            if (!"0".equals(task.getType())){
                task.setStauts("0");
                vCronMapper.insert(task);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("任务id:【{}】, 任务:【{}】执行失败,原因:【{}】", task.getId(), task.getTitle(), e.getCause().getMessage());
            String msg = "任务id:【" + task.getId() + "】, 任务名称:【{" + task.getTitle() + "}】执行失败,原因:【{" + e.getCause().getMessage() + "}】";
            VCronException taskException = new VCronException();
            taskException.setTaskId(task.getId());
            taskException.setMsg(msg);
            vCronExceptionMapper.insert(taskException);
        }
    }

    public void removeSysJob(String taskId) {
        VCron vCron = vCronMapper.selectById(taskId);
        log.info("删除定时任务：{},任务id:{}", vCron.getTitle(), vCron.getId());
        if (null != vCron) {
            vCron.setStauts("1");
            vCron.setDelFlag(CommonConstants.STATUS_DEL);
            vCronMapper.updateById(vCron);
            CronUtil.remove(taskId);
        }
    }
}
