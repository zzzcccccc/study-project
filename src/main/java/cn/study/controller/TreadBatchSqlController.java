package cn.study.controller;


import cn.study.commons.MyThread;
import cn.study.entity.VLink;
import cn.study.mapper.StudyMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 029302 on 2021/7/3.
 * 分批次处理sql 线程池
 */
@RestController
@RequestMapping("/batch")
@Api(value = "分批次处理sql", tags = "分批次处理sql")
public class TreadBatchSqlController {

    private final Logger logger = LoggerFactory.getLogger(TreadBatchSqlController.class);

    @Resource
    StudyMapper studyMapper;

    private List<VLink> allList = new ArrayList();

    @GetMapping("/getEcdEmptype")
    @ResponseBody
    @ApiOperation(value = "测试", notes = "测试")
    public void getEcdEmptype() {

        Date parse = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            parse = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int groupSize = 5; //分几组
        allList = studyMapper.selectList(null);
        System.out.println(allList.size()+"条--------");
        //拆分数据
        List<List<VLink>> tt = spliteList(allList,groupSize);
        CountDownLatch latch = new CountDownLatch(groupSize);
        final ExecutorService exec = Executors.newFixedThreadPool(5); //5个线程
        //多线程运行
        long startTime = System.currentTimeMillis();
        for (int ii = 0; ii < 5; ii++) {
            exec.submit(new MyThread(tt.get(ii),parse, latch, "Threadzcy" + ii));
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        exec.shutdown();
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("1程序运行时间： " + (endTime - startTime) + "ms");
    }

    public List<List<VLink>> spliteList(List<VLink> allList,int groupSize) {
        int length = allList.size();
        // 计算可以分成多少组
        int num = length / groupSize;
        List<List<VLink>> newList = new ArrayList<>(num);
        if (num == 0) {
            newList.add(allList);
        } else {
            for (int i = 1; i <= groupSize; i++) {
                // 开始位置
                int fromIndex = (i - 1) * num;
                // 结束位置
                int toIndex = i * num;
                if (i == groupSize) {
                    toIndex = i * num + length % groupSize;
                }

                newList.add(allList.subList(fromIndex, toIndex));
            }
        }
        return newList;
    }

}

