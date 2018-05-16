package com.github.sejoung.reactive.test;

public class GoControl {

    private Control control;
    public GoControl(Control control){
        this.control = control;
    }

    public String gogo() throws Exception {
        return this.control.process(1);
    }

    public String gogosing() throws Exception {
        return this.control.process(2);
    }
}
