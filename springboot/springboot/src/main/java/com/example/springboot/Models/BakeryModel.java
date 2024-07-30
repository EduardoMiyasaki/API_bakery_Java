package com.example.springboot.Models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_BAKERY")
public class BakeryModel extends RepresentationModel<BakeryModel> implements Serializable {
    // RepresentationModel facilita a adição de hateos ou seja aqueles links que retornam o status do cliente

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nameProduct;
    private int price_in_cents;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNameProduct() {
        return this.nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getPrice_in_cents() {
        return this.price_in_cents;
    }

    public void setPrice_in_cents(int price_in_cents) {
        this.price_in_cents = price_in_cents;
    }
}
