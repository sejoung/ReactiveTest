package com.github.sejoung.reactive.test;

public class Control {

    private String run1(){
        return "A";
    }


    private String run2(){
        return "B";
    }

    private String run3(){
        return "C";
    }

    public String process(int flag) throws Exception {
        switch (flag){
            case 1: return this.run1();
            case 2: return this.run2();
            case 3: return this.run2();
            default: throw new Exception("invalid param");
        }
    }
}
