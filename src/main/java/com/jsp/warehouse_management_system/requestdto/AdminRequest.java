package com.jsp.warehouse_management_system.requestdto;

import java.util.List;

import com.jsp.warehouse_management_system.enums.AdminType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
public class AdminRequest {
private int adminId;
@NotNull(message = "username cannot be null")
@NotBlank(message = "username cannot be blank")
private String name;
@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ")
private String email;
@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", 
message = "Password must contain at least one letter, one number, one special character, and be at least 8 characters long")
private String password;


}
