package com.yifei.ConcurrencyCookbook.folkJoin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuyifei01 on 14-8-27.
 */
public class ProductListGenerator {
    public List<Product>generate (int size) {
        List<Product> ret = new ArrayList<>();
        for (int i=0;i<size;i++) {
            Product p = new Product();
            p.setName("Product" + i);
            p.setPrice(10);
            ret.add(p);
        }
        return ret;
    }

}
