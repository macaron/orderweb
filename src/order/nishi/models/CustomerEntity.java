package order.nishi.models;

import java.util.ArrayList;

public class CustomerEntity {
    private ArrayList<Integer> custom_c = new ArrayList<>();
    private ArrayList<String> custom_nam = new ArrayList<>();

    public void addCustomC(int custom_c) {
        this.custom_c.add(custom_c);
    }

    public void addCustomNam(String custom_nam) {
        this.custom_nam.add(custom_nam);
    }

    public int getCustomC() {
        return  this.custom_c.get(0);
    }

    public int getCustomC(int n) {
        return  this.custom_c.get(n);
    }

    public String getCustomNam() {
        return this.custom_nam.get(0);
    }

    public String getCustomNam(int n) {
        return this.custom_nam.get(n);
    }

    public int getArrayTotal() {
        return this.custom_c.size();
    }
}
