package com.example.demo.service;

import com.example.demo.entity.Member;
import com.example.demo.model.SignupRequest;
import com.example.demo.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    @Autowired
    private MemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public boolean isUsernameAvailable(String username) {
        if (!username.equals("") && !username.equals("null") && !username.trim().isEmpty() && !username.contains(" ")) {
            return repository.findByUsername(username) == null;
        }else {
            return repository.findByUsername(username) != null;
        }
    }

    public void createUser(SignupRequest user) {
        Member record = modelMapper.map(user, Member.class);

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        record.setPassword(hashedPassword);

        record.setRole("ROLE_USER");

        repository.save(record);
    }

    public Member getUser(String username) {
        return repository.findByUsername(username);
    }

}
