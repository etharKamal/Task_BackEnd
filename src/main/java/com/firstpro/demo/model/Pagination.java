package com.firstpro.demo.model;

public class Pagination {
    private int total;
    private long count;
    private int per_page;
    private int current_page;
    private double total_pages;

    public Pagination(int total, long count, int per_page, int current_page, double total_pages){
        this.total = total;
        this.count = count;
        this.per_page = per_page;
        this.current_page = current_page;
        this.total_pages = total_pages;
    }

    public Pagination(int total, double total_pages, int size, int pagesize, int page, double ceil) {
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public double getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(double total_pages) {
        this.total_pages = total_pages;
    }
}
