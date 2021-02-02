package com.example.carros.services;

import java.util.ArrayList;
import java.util.List;

import com.example.carros.entidades.Carro;
import com.example.carros.entidades.CarroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {
	
	@Autowired
	CarroRepository carroRep;
	
	
	public Iterable<Carro> getCarro() {
		return carroRep.findAll();
	}
	
	public List<Carro> getCarrosFake() {
		 List<Carro> carros = new ArrayList<Carro>();

		carros.add(new Carro("Fusca", 1l));
		carros.add(new Carro("Sandero", 2l));
		carros.add(new Carro("Onix", 3l));
		return carros;
	}

}