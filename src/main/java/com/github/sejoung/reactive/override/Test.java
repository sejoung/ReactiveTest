package com.github.sejoung.reactive.override;

public class Test {
    public static void main(String[] args) {
        Car car = new SuperCar();
        System.out.println("car "+car.owner);
        System.out.println("car "+car.getOwner());
        SuperCar b = (SuperCar)car;
        System.out.println("car "+b.owner);
    }
}
