package com.github.sejoung.reactive.test;

public class DataCodeTest {
    public static void main(String[] args) {
        DataCode2 dc = new DataCode2(new DataCode());

        dc.count();
        dc.count();

        System.out.println(dc.getCounter());
    }
}
