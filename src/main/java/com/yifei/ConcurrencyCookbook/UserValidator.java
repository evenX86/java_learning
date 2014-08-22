package com.yifei.ConcurrencyCookbook;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 实现用户验证过程
 * Created by xuyifei01 on 14-8-21.
 */
public class UserValidator {
    private String name;

    public UserValidator(String name) {
        this.name = name;
    }
    public boolean validate(String name,String password) {
        Random random = new Random();

        try {
            long duration = (long) (Math.random()*10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            return false;
        }
        return random.nextBoolean();
    }
    public String getName() {
        return name;
    }
}
