package com.github.sejoung.reactive.test;

public class ControlTest {
    public static void main(String[] args) throws Exception {
        GoControl go = new GoControl(new Control());
        System.out.println(go.gogo());
        System.out.println(go.gogosing());
    }
}
