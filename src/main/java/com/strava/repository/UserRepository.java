package com.strava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.strava.entity.Usuario;

public interface UserRepository extends JpaRepository<Usuario, String> {
}

