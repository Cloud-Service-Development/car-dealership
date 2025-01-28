package com.example.car_dealership.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "customers")
@Schema(description = "Entity representing a customer in the system.")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique identifier of the customer.", example = "1")
    private int id;

    @Schema(description = "The first name of the customer.", example = "John")
    private String firstName;

    @Schema(description = "The last name of the customer.", example = "Doe")
    private String lastName;

    @Schema(
            description = "The tax number of the customer. Must be unique and alphanumeric.",
            example = "ABC1234567"
    )
    private String taxNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @Schema(description = "The user account associated with this customer.")
    private InternalUser user;

    private Customer() {}

    public Customer(
            String firstName,
            String lastName,
            String taxNumber,
            InternalUser user
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.taxNumber = taxNumber;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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