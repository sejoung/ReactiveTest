package com.github.sejoung.reactive.test;

public class GoControl2 {
    private Control control;
    public GoControl2(Control control){
        this.control = control;
    }

    public String gogo(int i) throws Exception {
        if("A".equals(this.control.process(i))){
            return "A";
        }else if("B".equals(this.control.process(i))){
            return "B";
        }else if("C".equals(this.control.process(i))){
            return "C";
        }else{
            throw new Exception("에러닷");
        }
    }

}
