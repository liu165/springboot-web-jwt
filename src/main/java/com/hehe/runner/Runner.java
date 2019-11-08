package com.hehe.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * ClassName: Runner <br/>
 * Description: <br/>
 * date: 2019/10/25 17:34<br/>
 *
 * @author me<br />
 * @since JDK 1.8
 */
@Component
public class Runner implements ApplicationRunner {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("这里是启动后打印的东西：");
        System.out.println("项目成功启动 输入：http://localhost:8080/");
    }
}
