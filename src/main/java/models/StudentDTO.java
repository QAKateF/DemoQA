package models;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@ToString

public class StudentDTO {

    String firstName;
    String lastName;
    String email;
    Gender gender;
    String phone;
    String birthday;
    String subjects;
    List<Hobby> hobbies;
    String address;
    String state;
    String city;
}