package com.yifei.ConcurrencyCookbook;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
            System.out.println(validator.getName()+" has not been found");
            throw  new Exception("Error validating user");
        }
        return validator.getName();
    }
    public static void main(String[] args) {
        String user = "test";
        String passwd = "test";
        UserValidator userValidator = new UserValidator("LDAP");
        UserValidator userValidator1 = new UserValidator("DataBase");
        TaskValidator taskValidator = new TaskValidator(userValidator,user,passwd);
        TaskValidator taskValidator1 = new TaskValidator(userValidator1,user,passwd);

        List<TaskValidator> taskValidatorList = new ArrayList<>();
        taskValidatorList.add(taskValidator);
        taskValidatorList.add(taskValidator1);

        ExecutorService executor = Executors.newCachedThreadPool();
        String result;
        try {
            result = executor.invokeAny(taskValidatorList);
            System.out.printf("Result : %s\n",result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
