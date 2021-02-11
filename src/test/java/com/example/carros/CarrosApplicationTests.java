package com.example.carros;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;
import com.example.carros.dto.CarroDTO;
import com.example.carros.entidades.Carro;
import com.example.carros.services.CarroService;

@SpringBootTest
class CarrosApplicationTests {
	
	@Autowired
	CarroService services;

	@Test
	void saveCarro() {
		Carro carro = new Carro();
		carro.setNome("Teste Carro");
		carro.setTipo("classicos");
		CarroDTO carrodto = services.save(carro);
		assertNotNull(carrodto);
		assertNotNull(carrodto.getId());
	}
	
	@Test
	void obterCarro() {

	}
	@Test
	void atualizarCarro() {
		
	}
	@Test 
	void deletarCarro() {
		
	}

}
