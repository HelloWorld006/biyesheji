package com.zhiyou.video.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

/**
 * 用于测试定时调度 lyz
 */
@Component
public class Task {
    /**
     * hello:表示定时运行的方法
     */
    public void hello() {
        System.out.println("hello");
    }

    /**
     * word：表示定时运行的方法
     */
    public void word() {
        System.out.println("word");
    }

    /**
     * 测试方法
     *
     * @param args
     */
    public static void main(String[] args) throws MessagingException {
        new ClassPathXmlApplicationContext("spring.xml");

    }


}
