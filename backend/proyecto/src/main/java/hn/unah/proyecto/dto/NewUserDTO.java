package hn.unah.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewUserDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String parentCity;
    private String job;
    private String typeJob;
    private String placeJob;
    private String schoolName;
    private String firstYear;
    private String lastYear;
    private String birthDay;
    private String birthMonth;
    private String birthYear;
    private String profilePhoto;
    private String titular;
}
