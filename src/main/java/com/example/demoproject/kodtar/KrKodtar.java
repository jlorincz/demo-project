package com.example.demoproject.kodtar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "KR_KODTAR_TBL")
public class KrKodtar {

    @Id
    @Column(name = "ID", nullable = false, precision = 10, scale = 0)
    private BigDecimal id;

    @Column(name = "CSOPORT_KOD", nullable = false, length = 10)
    private String csoportKod;

    @Column(name = "ERTEK_KOD", nullable = false, length = 50)
    private String ertekKod;

    @Column(name = "ERTEK", nullable = false)
    private String ertek;

    @Column(name = "ERV_KEZD", nullable = false)
    private LocalDateTime ervKezd;

    @Column(name = "ERV_VEGE", nullable = false)
    private LocalDateTime ervVege;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCsoportKod() {
        return csoportKod;
    }

    public void setCsoportKod(String csoportKod) {
        this.csoportKod = csoportKod;
    }

    public String getErtekKod() {
        return ertekKod;
    }

    public void setErtekKod(String ertekKod) {
        this.ertekKod = ertekKod;
    }

    public String getErtek() {
        return ertek;
    }

    public void setErtek(String ertek) {
        this.ertek = ertek;
    }

    public LocalDateTime getErvKezd() {
        return ervKezd;
    }

    public void setErvKezd(LocalDateTime ervKezd) {
        this.ervKezd = ervKezd;
    }

    public LocalDateTime getErvVege() {
        return ervVege;
    }

    public void setErvVege(LocalDateTime ervVege) {
        this.ervVege = ervVege;
    }
}
