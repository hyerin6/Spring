package net.skhu.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserRegistrationModel {

    @NotEmpty
    @Size(min=3, max=12)
    String userid;

    @NotEmpty
    @Size(min=6, max=12)
    String passwd1;

    String passwd2;

    @NotEmpty
    @Size(min=2, max=30)
    String name;

    @Email
    String email;

    Integer departmentId;
}
