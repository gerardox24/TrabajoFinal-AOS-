package com.example.demo.models.service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import com.example.demo.models.entity.Paradero;

public interface IParaderoService {
	public Collection<Paradero> findAll();
	public void save(Paradero t);
	public Optional<Paradero> findById(String t);
	public void deleteById(String t);
	public Map<String, Object> Map();
	RutaParaderoMatch findParaderoRutaMatch(String rutaID);
}
