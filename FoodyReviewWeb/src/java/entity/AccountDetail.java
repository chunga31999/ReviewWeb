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
public class AccountDetail {

    private int ID;
    private String Fullname;
    private String DOB;
    private int gender;

    public AccountDetail(int ID, String Fullname, String DOB, int gender) {
        this.ID = ID;
        this.Fullname = Fullname;
        this.DOB = DOB;
        this.gender = gender;
    }

    public AccountDetail() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
    
}
