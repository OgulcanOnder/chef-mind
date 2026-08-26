package com.ogulcanonder.chef_mind.service;

import com.ogulcanonder.chef_mind.model.User;

import java.util.Optional;

public interface IUserService {
    public void create(User user);

    public Optional<User> findByEmail(String email);

    public boolean existsByEmail(String email);

    public boolean existsByUsername(String username);

}
