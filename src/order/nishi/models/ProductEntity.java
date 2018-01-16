package order.nishi.models;

import java.util.ArrayList;

public class ProductEntity {
    private ArrayList<Integer> productCode = new ArrayList<>();
    private ArrayList<String> productName = new ArrayList<>();
    private ArrayList<Double> productPrice = new ArrayList<>();

    public void addCode(int code) {
        this.productCode.add(code);
    }

    public void addName(String name) {
        this.productName.add(name);
    }

    public void addPrice(double price) {
        this.productPrice.add(price);
    }

    public int getCode() {
        return this.productCode.get(0);
    }

    public int getCode(int n) {
        return this.productCode.get(n);
    }

    public String getName() {
        return this.productName.get(0);
    }

    public String getName(int n) {
        return this.productName.get(n);
    }


    public double getPrice() {
        return this.productPrice.get(0);
    }

    public double getPrice(int n) {
        return this.productPrice.get(n);
    }

    public int getArrayTotal() {
        return productCode.size();
    }
}
