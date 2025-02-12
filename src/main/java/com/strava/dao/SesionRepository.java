package com.strava.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.strava.entity.Sesion;

public interface SesionRepository extends JpaRepository<Sesion, Long> {

}