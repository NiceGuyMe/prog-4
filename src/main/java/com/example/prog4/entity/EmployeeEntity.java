package com.example.prog4.entity;


import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotBlank
  private String firstname;

  @NotBlank
  private String lastname;

  @DateTimeFormat(pattern = "yyyy-MM-dd")

  private Date birthdate;

  @ElementCollection
  @Column(unique = true)
  private List<String> phoneNumbers;

  private String workEmail;

  private String personalEmail;

  private String CIN;

  private String address;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date hireDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date resignationDate;

  private String CNAPS;

  private String position;


  private int children;

  private String sex;

  private String category;


  private String employeeNumber;


  @Column(columnDefinition = "TEXT")
  private String imageBase64;

}
