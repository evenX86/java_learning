package com.yifei.iotest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuyifei01 on 14-7-28.
 */

class House implements Serializable {
}

class Animal implements Serializable {
    private String name;
    private House preferreHouse;

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", preferreHouse=" + preferreHouse +
                '}';
    }

    Animal(String nm, House h) {
        name = nm;
        preferreHouse = h;
    }
}

public class MyWorld {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        House house = new House();
        List<Animal>animals = new ArrayList<>();
        animals.add(new Animal("Bosco the dog",house));
        animals.add(new Animal("Ralph the Hasmster",house));
        animals.add(new Animal("Molly the cat",house));
        System.out.println("Animals :"+animals);
        ByteArrayOutputStream buf1 = new ByteArrayOutputStream();//通过一个字节数组使对象序列化。
        ObjectOutputStream o1 =  new ObjectOutputStream(buf1);
        o1.writeObject(animals);
        o1.writeObject(animals);
        ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
        ObjectOutputStream o2 = new ObjectOutputStream(buf2);
        o2.writeObject(animals);
        ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buf1.toByteArray()));
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(buf2.toByteArray()));
        List animals1 = (List) in1.readObject();
        List animals2 = (List) in1.readObject();
        List animals3 = (List) in2.readObject();
        System.out.println("animals 1:"+animals1);
        System.out.println("animals 2:"+animals2);
        System.out.println("animals 3:"+animals3);
    }
}
