package com.strava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import com.strava.entity.Usuario;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<Usuario, String> {
	
}

