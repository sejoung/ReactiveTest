package com.github.sejoung.reactive.test;

public class StampTest {

    public static void main(String[] args) {
        Data data = new Data();
        data.setAge(1);
        data.setName("zolla");
        StampCode.printAge(data);
        StampCode.printName(data);
    }
}
