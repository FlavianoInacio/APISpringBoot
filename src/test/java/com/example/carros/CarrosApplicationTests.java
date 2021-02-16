package com.example.carros;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import com.example.carros.dto.CarroDTO;
import com.example.carros.entidades.Carro;
import com.example.carros.services.CarroService;

@SpringBootTest
class CarrosApplicationTests {
	
	@Autowired
	CarroService services;

	@Test
	void crudCarro() {
		// Salvar o Carro
		Carro carro = new Carro();
		carro.setNome("Teste Carro");
		carro.setTipo("classicos");
		CarroDTO carrodto = services.save(carro);
		assertNotNull(carrodto);
		assertNotNull(carrodto.getId());
		
		// buscar o carro Salvo
		Optional<CarroDTO> carroSalvo = services.getCarroById(carrodto.getId());
		assertTrue(carroSalvo.isPresent());
		
		carrodto = carroSalvo.get();
		assertEquals("Teste Carro", carrodto.getNome());
		assertEquals("classicos", carrodto.getTipo());
		
		
		// fazer atualização no carro
		carro.setNome("Teste Carro update");
		carro.setTipo("luxo");
		CarroDTO carroUpdate = services.put(carrodto.getId(), carro);
		
		// buscar o carro Salvo
		Optional<CarroDTO> carroUpdateBanco = services.getCarroById(carrodto.getId());
		carrodto = carroUpdateBanco.get();
		assertEquals("Teste Carro update", carrodto.getNome());
		assertEquals("luxo", carrodto.getTipo());
		
		// Deletar o carro
		 services.delete(carro.getId());
		 assertNull(services.getCarroById(carro.getId()));
	}

	@Test
	void listarCarros(){
		List<CarroDTO> carros = services.getCarro();
		assertEquals(30, carros.size());
	}
	@Test
	void listarCarrosTipo(){
		List<CarroDTO> carros = services.getCarroByTipo("classicos");
		assertEquals(10, carros.size());
	}

}
