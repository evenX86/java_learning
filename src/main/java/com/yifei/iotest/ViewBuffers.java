package com.yifei.iotest;

import java.awt.image.AreaAveragingScaleFilter;
import java.nio.*;
import java.util.Arrays;

/**
 * Created by xuyifei01 on 14-7-27.
 */
public class ViewBuffers {
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[12]);
        bb.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(bb.array()));
        bb.rewind();
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(bb.array()));
        System.out.println(System.nanoTime());

    }
}
