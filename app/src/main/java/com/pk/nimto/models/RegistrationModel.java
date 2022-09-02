package com.pk.nimto.models;

public class RegistrationModel {
    private String firstName, lastName, email, country, address, phoneNumber, password, password2;

    public String getFirstName() { return firstName; }
    public void setFirstName(String value) { this.firstName = value; }

    public String getLastName() { return lastName; }
    public void setLastName(String value) { this.lastName = value; }

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }

    public String getCountry() { return country; }
    public void setCountry(String value) { this.country = value; }

    public String getAddress() { return address; }
    public void setAddress(String value) { this.address = value; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String value) { this.phoneNumber = value; }

    public String getPassword() { return password; }
    public void setPassword(String value) { this.password = value; }

    public String getPassword2() { return password2; }
    public void setPassword2(String value) { this.password2 = value; }
}
