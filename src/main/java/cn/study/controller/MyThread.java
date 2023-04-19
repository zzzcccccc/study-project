package cn.study.controller;


import cn.study.entity.VLink;
import cn.study.entity.VLink2;
import cn.study.mapper.Study2Mapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MyThread implements Runnable {
    private List<VLink> msgList = null;
    private CountDownLatch latch = null;
    private String name;
    private Date parse;

    private Study2Mapper study2Mapper = BeanContext.getBean(Study2Mapper.class);

    public MyThread(List<VLink> msgList, Date parse, CountDownLatch latch, String name) {
        this.msgList = msgList;
        this.parse = parse;
        this.latch = latch;
        this.name = name;
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + "正在执行。。。");

        if (msgList != null && msgList.size() > 0) {
            for (VLink all : msgList) {
                VLink2 branchOfficeExperienceViewHr = new VLink2();
                BeanUtils.copyProperties(all, branchOfficeExperienceViewHr);

                Integer id = all.getId();
                Integer countRecord = study2Mapper.selectCount(Wrappers.<VLink2>lambdaQuery()
                        .eq(VLink2::getId, id));
                if (countRecord == 1) {
                    study2Mapper.updateById(branchOfficeExperienceViewHr);
                } else {
                    study2Mapper.insert(branchOfficeExperienceViewHr);
                }
            }
        }
        latch.countDown();
    }

}

