package com.example.demo.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.entity.Persona;

public interface IPersonaDAO extends JpaRepository<Persona, String>{

}
