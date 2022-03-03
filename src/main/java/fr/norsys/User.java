package fr.norsys;

public class User {

    private String email;
    private Address address;
    private int age;

    public int getLoginHours() {
        return loginHours;
    }

    public void setLoginHours(int loginHours) {
        this.loginHours = loginHours;
    }

    private int loginHours;

    public User() {
    }

    public User(String email, Address address, int age, int loginHours) {
        this.email = email;
        this.address = address;
        this.age = age;
        this.loginHours = loginHours;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
