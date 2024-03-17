package nl.novi.autogarage.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class KassaMedewerkerDto {

    public Long id;


    public String firstName;


    public String lastName;

    public LocalDate dob;
}
