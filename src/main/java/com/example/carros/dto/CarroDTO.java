package com.example.carros.dto;

import com.example.carros.entidades.Carro;

import lombok.Data;

@Data
public class CarroDTO {
	private long id;
	private String nome;
	private String tipo;
	
	public CarroDTO(Carro carro) {
		this.id = carro.getId();
		this.nome = carro.getNome();
		this.tipo = carro.getTipo();
	}
}
