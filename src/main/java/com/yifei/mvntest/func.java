package com.yifei.mvntest;

/**
 * Created by xuyifei01 on 14-7-29.
 */
public class func {
    public static void main(String[] args) {
        String sql = "",
                sql2 ="CREATE TABLE `tb3` (\n" +
                        "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                        "  `siteid` varchar(256) DEFAULT NULL,\n" +
                        "  `pv` int(11) DEFAULT NULL,\n" +
                        "  `uv` int(11) DEFAULT NULL,\n" +
                        "  `insert_date` date DEFAULT NULL,\n" +
                        "  PRIMARY KEY (`id`)\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

        for (int i = 7; i < 130; i++) {
            sql = "CREATE DATABASE `dbtest" + i + "` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;";

            System.out.println(sql);
            System.out.println("use dbtest"+i+";");
            System.out.println(sql2);
        }
    }
}
