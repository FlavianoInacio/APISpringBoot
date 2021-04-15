package com.example.carros.dto;

import org.modelmapper.ModelMapper;

import com.example.carros.entidades.Carro;

import lombok.Data;

@Data
public class CarroDTO {
	private long id;
	private String nome;
	private String tipo;
	private String descricao;
	private String url_foto;
	
	// ModelMapper converte uma classe em outra
	public static CarroDTO create(Carro carro) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(carro, CarroDTO.class);
	}
}
