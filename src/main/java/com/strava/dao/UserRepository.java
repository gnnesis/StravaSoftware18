package com.strava.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strava.entity.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, String>{

	Optional<Usuario> findByNickname(String nickname);
    Optional<Usuario> findByEmail(String email);
}