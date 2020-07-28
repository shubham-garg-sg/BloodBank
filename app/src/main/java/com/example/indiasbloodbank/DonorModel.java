package com.example.indiasbloodbank;

public class DonorModel {

    private String name;
    private int age;
    private String gender;
    private int weight;
    private int haemoglobin;
    private long contact;

    private DonorModel() {}

    private DonorModel(String name, int age, String gender, int weight, int haemoglobin, long contact) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.haemoglobin = haemoglobin;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHaemoglobin() {
        return haemoglobin;
    }

    public void setHaemoglobin(int haemoglobin) {
        this.haemoglobin = haemoglobin;
    }
    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

}
