package com.example.car_dealership.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    @Column(name="fuelType")//χρησιμοποιω το ονομα που εχω στη βαση
    private String fuelType;
    private int thesis;
    @Column(name="numberOfSameCars")
    private int numberOfSameCars;
    private Double price;

    //Σχέση με την κλάση DealerShip
    @ManyToOne
    @JoinColumn(name="dealership_id")
    private DealerShip dealerShip;

    //Σχεση με την κλάση Appointment
    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL)
    private List<Appointment> appointmentList;
    //-- Constructors
    public Car() {}

    public Car(String brand, String model,String fuelType,int thesis,int numberOfSameCars,
               Double price, DealerShip dealerShip) {
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.thesis = thesis;
        this.numberOfSameCars = numberOfSameCars;
        this.price = price;
        this.dealerShip = dealerShip;
    }

    // -- Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getFuelType() { return fuelType; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }

    public int getThesis() { return thesis; }
    public void setThesis(int thesis) { this.thesis = thesis; }

    public int getNumberOfSameCars() { return numberOfSameCars; }
    public void setNumberOfSameCars(int numberOfSameCars) {this.numberOfSameCars = numberOfSameCars;}

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public DealerShip getDealerShip() { return dealerShip; }
    public void setDealerShip(DealerShip dealerShip) { this.dealerShip = dealerShip; }
}