package com.rohan.BlogApp.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    //field only related to user or taken from user, we can later may be add some other indirect
    // fields in User class
    private int id;
    @NotEmpty
    @Size(min=4,message = "Username must be alteast 4 letters")
    private String name;
    @Email(message = "Email is not valid")
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String about;


}
