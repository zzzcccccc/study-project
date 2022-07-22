package cn.study;

import cn.dev33.satoken.SaManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudyProjectApplication {
    public static void main(String[] args) {

        SpringApplication.run(StudyProjectApplication.class, args);
        System.out.println("启动成功：Sa-Token配置如下：" + SaManager.getConfig());

    }
}
