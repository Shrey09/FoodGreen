package com.example.foodgreen;

public class model_new_sell_order {
    String id;
    private String data_dish_name;
    private String data_dish_price;
    private String data_dish_quantity;
    private String data_dish_description;
    private String data_cook_time;
    private String data_cook_date;
    private String data_expire_time;
    private String data_expire_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData_dish_name() {
        return data_dish_name;
    }

    public void setData_dish_name(String data_dish_name) {
        this.data_dish_name = data_dish_name;
    }

    public String getData_dish_price() {
        return data_dish_price;
    }

    public void setData_dish_price(String data_dish_price) {
        this.data_dish_price = data_dish_price;
    }

    public String getData_dish_quantity() {
        return data_dish_quantity;
    }

    public void setData_dish_quantity(String data_dish_quantity) {
        this.data_dish_quantity = data_dish_quantity;
    }

    public String getData_dish_description() {
        return data_dish_description;
    }

    public void setData_dish_description(String data_dish_description) {
        this.data_dish_description = data_dish_description;
    }

    public String getData_cook_time() {
        return data_cook_time;
    }

    public void setData_cook_time(String data_cook_time) {
        this.data_cook_time = data_cook_time;
    }

    public String getData_cook_date() {
        return data_cook_date;
    }

    public void setData_cook_date(String data_cook_date) {
        this.data_cook_date = data_cook_date;
    }

    public String getData_expire_time() {
        return data_expire_time;
    }

    public void setData_expire_time(String data_expire_time) {
        this.data_expire_time = data_expire_time;
    }

    public String getData_expire_date() {
        return data_expire_date;
    }

    public void setData_expire_date(String data_expire_date) {
        this.data_expire_date = data_expire_date;
    }

    public model_new_sell_order(String data_dish_name, String data_dish_price, String data_dish_quantity, String data_dish_description, String data_cook_time, String data_cook_date, String data_expire_time, String data_expire_date){
        this.data_dish_name = data_dish_name;
        this.data_dish_price = data_dish_price;
        this.data_dish_quantity = data_dish_quantity;
        this.data_dish_description = data_dish_description;
        this.data_cook_time = data_cook_time;
        this.data_cook_date = data_cook_date;
        this.data_expire_time = data_expire_time;
        this.data_expire_date = data_expire_date;
    }
}