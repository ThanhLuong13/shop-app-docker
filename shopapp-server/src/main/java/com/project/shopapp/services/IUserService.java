package com.project.shopapp.services;

import com.project.shopapp.dto.UpdateUserDTO;
import com.project.shopapp.dto.UserDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.model.UserEntity;

public interface IUserService {
    UserEntity createUser(UserDTO userDTO) throws Exception;
    String login(String phoneNumber, String password) throws Exception;

    UserEntity getUserDetailByToken(String token) throws DataNotFoundException;

    UserEntity updateUser(int userId, UpdateUserDTO updateUserDTO) throws Exception;
}
