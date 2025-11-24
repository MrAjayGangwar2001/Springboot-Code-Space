package com.crud.CRUD.DTO;

public class Studentdto {


    private int id;
    private String Name;
    private String Email;

    public Studentdto() {
    }
    public Studentdto(int id, String name, String email) {
        this.id = id;
        this.Name = name;
        this.Email = email;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
}
