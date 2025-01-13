package com.example.car_dealership.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dealerships")
public class DealerShip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String taxNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private InternalUser user;

    private DealerShip() {}

    public DealerShip(
            String name,
            String taxNumber,
            InternalUser user
    ) {
        this.name = name;
        this.taxNumber = taxNumber;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public InternalUser getUser() {
        return user;
    }

    public void setUser(InternalUser user) {
        this.user = user;
    }
}