package com.example.ksr_glocery;

public class data {
    private String items;
    private String rate;
    private String qty;
    private String total;

    public data(String items, String rate, String qty, String total) {
        this.items = items;
        this.rate = rate;
        this.qty = qty;
        this.total = total;
    }

    public String getItems() {
        return items;
    }

    public String getRate() {
        return rate;
    }

    public String getQty() {
        return qty;
    }

    public String getTotal() {
        return total;
    }
}
