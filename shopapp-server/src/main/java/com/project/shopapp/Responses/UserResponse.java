package com.project.shopapp.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.model.RoleEntity;
import com.project.shopapp.model.UserEntity;
import com.project.shopapp.repositories.UserRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private int id;

    @JsonProperty("fullname")
    private String fullName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String address;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("facebook_account_id")
    private int facebookAccountId;

    @JsonProperty("google_account_id")
    private int googleAccountId;

    @JsonProperty("role")
    private RoleEntity role;

    @JsonProperty("active")
    private boolean isActive;

    public static UserResponse formUserResponse(UserEntity userEntity) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userEntity.getId());
        userResponse.setFullName(userEntity.getFullName());
        userResponse.setAddress(userEntity.getAddress());
        userResponse.setPhoneNumber(userEntity.getPhoneNumber());
        userResponse.setDateOfBirth(userEntity.getDateOfBirth());
        userResponse.setRole(userEntity.getRole());
        userResponse.setActive(userEntity.getIsActive());
        return userResponse;
    }
}
