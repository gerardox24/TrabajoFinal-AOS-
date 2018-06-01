package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.entity.Ruta;
import com.example.demo.models.service.IParaderoService;
import com.example.demo.models.service.IRutaService;

@Controller
@RequestMapping("/rutas")
public class RutaController {
	@Autowired
	public IRutaService rutaService;
	
	@Autowired
	public IParaderoService paraderoService;

	@GetMapping
	public String listar(Model model) {
		model.addAllAttributes(rutaService.Map());
		model.addAttribute("rutas", rutaService.findAll());
		return "rutas.html";
	}

	@GetMapping({ "/add", "/edit/{rutaID}" })
	public String rutaAddEdit(Model model, @PathVariable(required = false, name = "rutaID") String rutaID) {
		if (null != rutaID) {
			model.addAttribute("title", rutaService.Map().get("editTitle"));
			model.addAttribute("submitValue", rutaService.Map().get("edit"));
			//model.addAttribute("ruta", rutaService.findById(rutaID));
		} else {
			model.addAttribute("title", rutaService.Map().get("addTitle"));
			model.addAttribute("submitValue", rutaService.Map().get("add"));
			//model.addAttribute("ruta", new Ruta());
		}
		model.addAttribute("rutaParaderoMatch",paraderoService.findParaderoRutaMatch(rutaID));
		//model.addAttribute("matches", paraderoService.findParaderoRutaMatch(rutaID));
		return "rutaAddEdit";
	}

	@PostMapping("/add")
	public String rutaUpdate(Model model, Ruta ruta) {
		rutaService.save(ruta);
		model.addAllAttributes(rutaService.Map());
		model.addAttribute("rutas", rutaService.findAll());
		return "rutas";
	}
}
