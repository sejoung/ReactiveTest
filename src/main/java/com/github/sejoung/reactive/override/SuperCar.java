package com.github.sejoung.reactive.override;

public class SuperCar extends Car {
    public String owner = "간지";

    @Override
    public String getOwner() {
        return owner;
    }
}
