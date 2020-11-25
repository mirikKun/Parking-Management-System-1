package com.kpi.parking.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String email;
    private String phone;
    @Column(name = "account_id")
    private int accountId;

    public Admin(String name, String address, String email, String phone, int account_id) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.accountId = account_id;
    }

    public Admin(int id, String name, String address, String email, String phone, int account_id) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.accountId = account_id;
    }

    public Admin() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return id == admin.id &&
                accountId == admin.accountId &&
                name.equals(admin.name) &&
                address.equals(admin.address) &&
                email.equals(admin.email) &&
                phone.equals(admin.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, email, phone, accountId);
    }
}

