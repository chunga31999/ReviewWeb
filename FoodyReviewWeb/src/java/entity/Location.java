/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author DELL
 */
public class Location {

    private int id;
    private String name;
    private String address;
    private String timeOpen;
    private String timeClose;
    private int minPrice;
    private int maxPrice;
    private Type type;
    private String img;
    private String userID;
    private float score;

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
    
    public Location() {
    }

    public Location(int id, String name, String address, String timeOpen, String timeClose, int minPrice, int maxPrice, Type type, String img) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.timeOpen = timeOpen;
        this.timeClose = timeClose;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.type = type;
        this.img = img;
    }

    public Location(int id, String name, String address, String timeOpen, String timeClose, int minPrice, int maxPrice, Type type, String img, String userID) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.timeOpen = timeOpen;
        this.timeClose = timeClose;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.type = type;
        this.img = img;
        this.userID = userID;
    }
 
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(String timeOpen) {
        this.timeOpen = timeOpen;
    }

    public String getTimeClose() {
        return timeClose;
    }

    public void setTimeClose(String timeClose) {
        this.timeClose = timeClose;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
}
