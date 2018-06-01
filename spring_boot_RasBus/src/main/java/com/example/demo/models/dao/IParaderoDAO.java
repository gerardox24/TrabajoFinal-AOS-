package com.example.demo.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.entity.Paradero;

public interface IParaderoDAO extends JpaRepository<Paradero, String>{

}
