package order.nishi.models;

import java.util.ArrayList;

public class SalesEntity {
    private ArrayList<String> sales_c = new ArrayList<>();
    private ArrayList<String> sales_nam = new ArrayList<>();

    public void setSalesC(String sales_c) {
        this.sales_c.add(sales_c);
    }

    public void setSalesNam(String sales_nam) {
        this.sales_nam.add(sales_nam);
    }

    public String getSalesC() {
        return this.sales_c.get(0);
    }

    public String getSalesC(int n) {
        return this.sales_c.get(n);
    }

    public String getSalesNam() {
        return  this.sales_nam.get(0);
    }

    public String getSalesNam(int n) {
        return  this.sales_nam.get(n);
    }

    public int getArrayTotal() {
        return this.sales_c.size();
    }
}
