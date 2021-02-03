package com.example.carros.api;

import java.util.List;
import java.util.Optional;

import com.example.carros.entidades.Carro;
import com.example.carros.services.CarroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {
	
	@Autowired
	private CarroService services;
	
	@GetMapping
	public Iterable<Carro> get() {
		return services.getCarro();
	}
	
	@GetMapping("/{id}")
	public Optional<Carro> getById(@PathVariable("id") long  id){
		return services.getCarroById(id);
	}

}
