package com.yifei.ConcurrencyCookbook.folkJoin;

import java.util.Random;

/**
 * Created by xuyifei01 on 14-8-28.
 */
public class Document {
    public String words[] = {"the", "hello", "goodbye", "packt", "java", "thread", "pool", "random", "class", "main"};

    public String[][] generateDocument(int numLines, int numWords, String word) {
        int counter = 0;
        String document[][] = new String[numLines][numWords];
        Random random = new Random();
        for (int i = 0; i < numLines; i++) {
            for (int j = 0; j < numWords; j++) {
                int index = random.nextInt(words.length);
                document[i][j] = words[index];
                if (word.equals(document[i][j])) {
                    counter++;
                }
            }
        }
        //输出了这个词的出现次数
        System.out.println("出现了" + counter +"次");
        return document;
    }


}
