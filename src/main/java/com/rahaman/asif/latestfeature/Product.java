package com.rahaman.asif.latestfeature;

public interface Product {
    void get();
    default void defaultImplementation(){
        System.out.println("This is the default implementation");
    }
}
