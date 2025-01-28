package com.example.car_dealership.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "dealerships")
@Schema(description = "Entity representing a dealership.")
public class DealerShip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique identifier of the dealership.", example = "1")
    private int id;

    @Schema(description = "The name of the dealership.", example = "Prestige Motors")
    private String name;

    @Schema(
            description = "The tax number of the dealership. Must be unique and alphanumeric.",
            example = "ABC1234567"
    )
    private String taxNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @Schema(description = "The user account associated with this dealership.")
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