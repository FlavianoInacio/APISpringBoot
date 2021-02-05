package com.example.carros.api;

import java.util.List;
import java.util.Optional;

import com.example.carros.dto.CarroDTO;
import com.example.carros.entidades.Carro;
import com.example.carros.services.CarroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {
	
	@Autowired
	private CarroService services;
	
	@GetMapping
	public ResponseEntity<Iterable<CarroDTO>> get() {
		return ResponseEntity.ok(services.getCarro());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity getById(@PathVariable("id") long  id){
		Optional<Carro> carro = services.getCarroById(id);
		if(carro.isPresent()) {
			return ResponseEntity.ok(carro);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/tipos/{tipo}")
	public ResponseEntity getByTipo(@PathVariable("tipo") String tipo){
		List<CarroDTO> carros = services.getCarroByTipo(tipo);
		return carros.isEmpty()?
				ResponseEntity.noContent().build():
				ResponseEntity.ok(carros);
	}
	
	@PostMapping
	public String post(@RequestBody Carro carro) {
		Carro carroSalvo = services.save(carro);
		return "Carro salvo com sucesso! id = " + carroSalvo.getId();
	}
	
	@PutMapping("/{id}")
	public String put(@PathVariable("id") long id, @RequestBody Carro carro) {
		String response = services.put(id,carro);
		return response;
	}
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		services.delete(id);
		return "Carro Deletado com sucesso!";
		
	}

}
