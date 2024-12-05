package com.springboot.restJPA.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String checkIn;
    private String checkOut;

    @ManyToOne
    @JoinColumn(name = "quarto_id", nullable = false)
    private Quarto quarto;

    public Reserva() {
    }

    public Reserva(String checkIn, String checkOut, Quarto quarto) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.quarto = quarto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public LocalDate getParsedCheckIn() {
        return LocalDate.parse(this.checkIn);
    }

    public LocalDate getParsedCheckOut() {
        return LocalDate.parse(this.checkOut);
    }
}
