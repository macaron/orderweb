package order.nishi.models;

import java.util.ArrayList;

public class CartEntity {
    private ArrayList<Integer> productCode = new ArrayList<>();
    private ArrayList<String> productName = new ArrayList<>();
    private ArrayList<Double> productPrice = new ArrayList<>();
    private ArrayList<Integer> productCount = new ArrayList<>();
    private ArrayList<Double> productSubTotal = new ArrayList<>();
    public Double price = 0.0;
    public Double tax = 0.0;
    public Double bill = 0.0;

    public void addCode(int code) {
        this.productCode.add(code);
    }

    public void addName(String name) {
        this.productName.add(name);
    }

    public void addPrice(double price) {
        this.productPrice.add(price);
    }

    public void addCount(int count) {
        this.productCount.add(count);
    }

    public void addSubTotal(double price, int count) {
        this.productSubTotal.add(price * count * 1.08);
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

    public int getCount() {
        return this.productCount.get(0);
    }

    public int getCount(int n) {
        return this.productCount.get(n);
    }

    public double getSubTotal() {
        return this.productSubTotal.get(0);
    }

    public double getSubTotal(int n) {
        return this.productSubTotal.get(n);
    }

    public int getArrayTotal() {
        return productCode.size();
    }

    public void calcPrice() {
        this.price = 0.0;
        this.tax = 0.0;
        this.bill = 0.0;
        for (int i = 0; i < this.getArrayTotal(); i++) {
            this.price += this.getSubTotal(i) / 1.08;
        }
        this.tax = this.price * 0.08;
        this.bill = this.price + this.tax;
    }
}
