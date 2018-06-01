package com.example.demo.models.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.dao.IParaderoDAO;
import com.example.demo.models.dao.IRutaDAO;
import com.example.demo.models.dao.IRutaParaderoDAO;
import com.example.demo.models.entity.Paradero;
import com.example.demo.models.entity.Ruta;
import com.example.demo.models.entity.RutaParadero;

class ParaderoMatch{
	public Paradero paradero;
	public boolean match;
	public ParaderoMatch(Paradero paradero, boolean match) {
		this.paradero = paradero;
		this.match = match;
	}
}
class RutaParaderoMatch{
	public Ruta ruta;
	public List<ParaderoMatch> paraderoMatches;
	public RutaParaderoMatch(Optional<Ruta> optional) {
		this.ruta = optional.orElse(new Ruta());
		this.paraderoMatches = new ArrayList<>();
	}
	public Ruta getRuta() {
		return ruta;
	}
	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}
	public List<ParaderoMatch> getParaderoMatches() {
		return paraderoMatches;
	}
	public void setParaderoMatches(List<ParaderoMatch> paraderoMatches) {
		this.paraderoMatches = paraderoMatches;
	}
}

@Service
public class ParaderoService implements IParaderoService {

	@Autowired
	IParaderoDAO paraderoDAO;
	
	@Autowired
	IRutaParaderoDAO rutaParaderoDAO;
	
	@Autowired
	IRutaDAO rutaDAO;
	
	public Map<String, Object> map;
	
	public ParaderoService() {
		super();
		map = new HashMap<>();
		map.put("title", "Listado de Paraderos");
		map.put("addTitle","Agregar Paradero");
		map.put("add","Registrar");
		map.put("editTitle","Editar Paradero");
		map.put("edit","Actualizar");	}
	
	@Override
	public Collection<Paradero> findAll() {
		// TODO Auto-generated method stub
		return paraderoDAO.findAll();
	}

	@Override
	public RutaParaderoMatch findParaderoRutaMatch(String rutaID){
		RutaParaderoMatch rutaParaderoMatch = new RutaParaderoMatch(rutaDAO.findById(rutaID));
		List<ParaderoMatch> matches = new ArrayList<>();
		List<RutaParadero> rutaParaderos = rutaParaderoDAO.findAll().stream().filter(x -> x.getRuta().getRutaID() == rutaID).collect(Collectors.toList());
		
		for (Paradero paradero: paraderoDAO.findAll() ) {
			boolean didMatch = false;
		    for (RutaParadero rutaParadero: rutaParaderos) {
		    	if (rutaParadero.getParadero() == paradero) {
		    		didMatch = true;
		    	}
		    }
		    matches.add(new ParaderoMatch(paradero,didMatch));
		}
		rutaParaderoMatch.paraderoMatches = matches;
		return rutaParaderoMatch;
		
	}
	@Override
	public void save(Paradero t) {
		// TODO Auto-generated method stub
		paraderoDAO.save(t);
	}

	@Override
	public Optional<Paradero> findById(String t) {
		// TODO Auto-generated method stub
		return paraderoDAO.findById(t);
	}

	@Override
	public void deleteById(String t) {
		// TODO Auto-generated method stub
		paraderoDAO.deleteById(t);
	}

	@Override
	public java.util.Map<String, Object> Map() {
		// TODO Auto-generated method stub
		return map;
	}

}
