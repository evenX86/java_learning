package com.yifei.ConcurrencyCookbook;

import java.util.concurrent.Callable;

/**
 * Created by xuyifei01 on 14-8-21.
 */
public class TaskValidator implements Callable<String>{
    private UserValidator validator;
    private String user;
    private String password;

    public TaskValidator(UserValidator validator, String user, String password) {
        this.validator = validator;
        this.user = user;
        this.password = password;
    }


    @Override
    public String call() throws Exception {
        if (!validator.validate(user,password)){
            System.out.println("Unvalidate");
            throw  new Exception("Error validating user");
        }
        System.out.println(validator.getName()+"has not been found");
        return validator.getName();
    }
    public static void main(String[] args) {
        String user = "test";
        String passwd = "test";
        UserValidator userValidator = new UserValidator("LDAP");
        UserValidator userValidator1 = new UserValidator("DataBase");


    }
}
