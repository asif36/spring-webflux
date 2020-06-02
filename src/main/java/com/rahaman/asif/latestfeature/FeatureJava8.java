package com.rahaman.asif.latestfeature;

public class FeatureJava8 {

    public static void getProduct(){
        System.out.println("Item 1");
    }

    public static void getProductOverride() {
        // Referring static method
        Product product = FeatureJava8::getProduct;
        // Calling interface method
        product.get();
    }
}
