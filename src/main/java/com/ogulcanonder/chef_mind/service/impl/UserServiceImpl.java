package com.ogulcanonder.chef_mind.service.impl;

import com.ogulcanonder.chef_mind.model.User;
import com.ogulcanonder.chef_mind.repository.UserRepository;
import com.ogulcanonder.chef_mind.service.IUserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void create(User user) {
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("User already exists");
        }

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
