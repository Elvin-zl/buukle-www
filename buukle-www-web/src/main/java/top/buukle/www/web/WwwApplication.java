package top.buukle.www.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan({"top.buukle.www.dao","top.buukle.common.mvc"})
@SpringBootApplication(scanBasePackages={"top.buukle.www.*"})
@EnableFeignClients(basePackages = {"top.buukle.*"})
@EnableTransactionManagement
public class WwwApplication {
    private static volatile boolean RUNNING = true;
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WwwApplication.class, args);
        System.out.println("启动成功");
        synchronized (WwwApplication.class) {
            while (RUNNING) {
                try {
                    WwwApplication.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    SpringApplication.exit(context);
                }
            }
        }
    }
}