package com.list.contacts.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


import java.time.LocalDateTime;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message="No puede quedar en blanco")
    private String name;

    @NotBlank(message="No puede quedar en blanco")
    @Email
    private String email;

    @NotBlank(message="No puede quedar en blanco")
    private String cellphoneNumber;

    private LocalDateTime dateOfEntry;

    public Contact(){}

    public Contact(Integer id, String name, String email, String cellphoneNumber, LocalDateTime dateOfEntry) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellphoneNumber = cellphoneNumber;
        this.dateOfEntry = dateOfEntry;
    }

    @PrePersist
    public void setDateOfRegister(){
        dateOfEntry = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public LocalDateTime getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(LocalDateTime dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }
}
