package com.generic;

import java.awt.*;
import java.lang.reflect.Method;

/**
 * Created by xuyifei01 on 14-7-17.
 */
class Mime {
    public void walkAgainistTheWind() {
    }

    public void sit() {
        System.out.println("Pretending to sit");
    }

    public void pushInvisibleWalls() {

    }

    public String toString() {
        return "Mime";
    }
}

class SmartDog {
    public void speak() {
        System.out.println("woof!");
    }

    public void sit() {
        System.out.println("sitting");
    }

    public void reproduce() {
    }
}

class CommunicateReflectively {
    public static void perform(Object speaker) {
        Class<?> spkr = speaker.getClass();
        try {
            Method speak = spkr.getMethod("speak");
            speak.invoke(speaker);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Method sit = spkr.getMethod("sit");
            sit.invoke(speaker);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class LatenReflection {
    public static void main(String[] args) {
        CommunicateReflectively.perform(new SmartDog());
        CommunicateReflectively.perform(new Mime());
    }
}
