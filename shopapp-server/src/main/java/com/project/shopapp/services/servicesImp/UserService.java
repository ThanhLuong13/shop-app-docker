package com.project.shopapp.services.servicesImp;

import com.project.shopapp.components.JwtTokenUtil;
import com.project.shopapp.configurations.SecurityConfig;
import com.project.shopapp.dto.ProductDTO;
import com.project.shopapp.dto.UpdateUserDTO;
import com.project.shopapp.dto.UserDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.exceptions.InvalidParamException;
import com.project.shopapp.model.ProductEntity;
import com.project.shopapp.model.RoleEntity;
import com.project.shopapp.model.UserEntity;
import com.project.shopapp.repositories.RoleRepository;
import com.project.shopapp.repositories.UserRepository;
import com.project.shopapp.services.IUserService;
import com.project.shopapp.utilities.MessageKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenUtil jwtTokenUtil;

    private final AuthenticationManager authenticationManager;


    @Override
    public UserEntity createUser(UserDTO userDTO) throws Exception  {
            String phoneNumber = userDTO.getPhoneNumber();
            // Check duplicate phone number
            if(userRepository.existsByPhoneNumber(phoneNumber)) {
                throw new DataIntegrityViolationException("Phone number already exists");
            }
            RoleEntity role = roleRepository.findById(userDTO.getRoleId())
                    .orElseThrow(() -> new DataNotFoundException("Role not found"));
            if (role.getName().toUpperCase().equals(RoleEntity.ADMIN)){
                throw new Exception("Can not register with role admin");
            }
            //convert from userDTO => user
            UserEntity newUser = new UserEntity();
            newUser.setFullName(userDTO.getFullName());
            newUser.setPhoneNumber(userDTO.getPhoneNumber());
            newUser.setAddress(userDTO.getAddress());
            newUser.setPassword(userDTO.getPassword());
            newUser.setDateOfBirth(userDTO.getDateOfBirth());
            newUser.setFacebookAccountId(userDTO.getFacebookAccountId());
            newUser.setGoogleAccountId(userDTO.getGoogleAccountId());
            newUser.setIsActive(true);
            newUser.setRole(role);
            // Check social account
            if (userDTO.getFacebookAccountId() == 0 && userDTO.getGoogleAccountId() == 0) {
                String password = userDTO.getPassword();
                String encodedPassword = passwordEncoder.encode(password);
                newUser.setPassword(encodedPassword);
            }
            return userRepository.save(newUser);
    }

    @Override
    public String login(String phoneNumber, String password) throws Exception {
        Optional<UserEntity> existingUser = userRepository.findByPhoneNumber(phoneNumber);
        if (existingUser.isEmpty()) {
            throw new DataNotFoundException("Can not find user with phone number "+phoneNumber);
        }
        UserEntity user = existingUser.get();
        if (user.getFacebookAccountId() == 0 && user.getGoogleAccountId() == 0) {
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("Wrong phone number or password");
            }
        }
        // Check Authentication
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                phoneNumber, password, user.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(user);
    }

    @Override
    public UserEntity getUserDetailByToken(String token) throws DataNotFoundException {
        String phoneNumber = jwtTokenUtil.extractPhoneNumber(token);
        Optional<UserEntity> user = userRepository.findByPhoneNumber(phoneNumber);
        if (user.isEmpty()) {
            throw new DataNotFoundException("Can not find user with phone number "+phoneNumber);
        } else {
            return user.get();
        }
    }

    @Override
    @Transactional
    public  UserEntity updateUser(int userId, UpdateUserDTO updatedUserDTO) throws Exception {
        UserEntity existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        String newPhoneNumber = updatedUserDTO.getPhoneNumber();
        if (!existingUser.getPhoneNumber().equals(newPhoneNumber)
                && userRepository.existsByPhoneNumber(newPhoneNumber)) {
            throw new DataIntegrityViolationException("Phone number already exists");
        }
        if (newPhoneNumber != null) {
            existingUser.setPhoneNumber(updatedUserDTO.getPhoneNumber());
        }
        if (updatedUserDTO.getFullName() != null) {
            existingUser.setFullName(updatedUserDTO.getFullName());
        }
        if (updatedUserDTO.getAddress() != null) {
            existingUser.setAddress(updatedUserDTO.getAddress());
        }
        if (updatedUserDTO.getDateOfBirth() != null) {
            existingUser.setDateOfBirth(updatedUserDTO.getDateOfBirth());
        }

        if (updatedUserDTO.getPassword() != null && updatedUserDTO.getRetypePassword() != null ) {
            if (!updatedUserDTO.getPassword().equals(updatedUserDTO.getRetypePassword())) {
                throw new InvalidParamException("Retype password not match");
            } else {
                String password = updatedUserDTO.getPassword();
                String encodedPassword = passwordEncoder.encode(password);
                existingUser.setPassword(encodedPassword);
            }
        }
        return userRepository.save(existingUser);
    }

}
