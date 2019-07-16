package com.android.priyanka.sqliteexample.Model;

public class Details {
    private int id;
    private String name, email, phone,hobbies,gender,qualification,dob;

    public Details() {


    }

    public Details(int id, String name, String email, String phone, String hobbies, String gender, String qualification, String dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.hobbies = hobbies;
        this.gender = gender;
        this.qualification = qualification;
        this.dob = dob;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
