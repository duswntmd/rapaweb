package com.test.sku.pet;

import org.json.simple.JSONObject;

public class PetVO {
    private int no;
    private String name;
    private String origin;
    private double weight;
    private java.sql.Date birth;
    private int price;
    private String pic;

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("no", this.getNo());
        obj.put("name", this.getName());
        obj.put("origin", this.getOrigin());
        obj.put("weight", this.getWeight());
        obj.put("birth", this.getBirth().toString());
        obj.put("price", this.getPrice());
        obj.put("pic", this.getPic());
        return obj;
    }
    
    // Getters and Setters
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public java.sql.Date getBirth() {
        return birth;
    }

    public void setBirth(java.sql.Date birth) {
        this.birth = birth;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
