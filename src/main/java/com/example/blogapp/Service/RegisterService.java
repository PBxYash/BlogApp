package com.example.blogapp.Service;

import com.example.blogapp.Entity.Register;
import com.example.blogapp.Payload.LoginDTO;
import com.example.blogapp.Payload.LoginMessage;
import com.example.blogapp.Payload.RegisterDto;
import com.example.blogapp.Repository.RegisterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RegisterService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RegisterRepository userRepository;

    public String createUser(RegisterDto userDto) {
        Register user = new Register();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return "User Register SuccussFully";
    }


    public LoginMessage loginVerify(LoginDTO loginDTO) {
        Register user = userRepository.findByEmail(loginDTO.getEmail());
        if (user != null) {
            String password = loginDTO.getPassword();
            String encodePassword = user.getPassword();
            boolean isPwdRight = passwordEncoder.matches(password, encodePassword);
            if (isPwdRight) {
                return new LoginMessage("Login Success", true);
            } else {
                return new LoginMessage("Password is wrong", false);
            }
        } else {
            return new LoginMessage("Email not exists", false);
        }
    }


    public List<RegisterDto> getAllData() {
        List<Register> all = userRepository.findAll();
        List<RegisterDto> collect = all.stream().map(c -> MaptoDto(c)).collect(Collectors.toList());
        return collect;
    }
    public Register findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    RegisterDto MaptoDto(Register user){
        RegisterDto map = modelMapper.map(user, RegisterDto.class);
        return map;
    }
}
