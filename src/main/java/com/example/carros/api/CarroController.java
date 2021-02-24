package com.example.carros.api;

import java.net.URI;
import java.util.ArrayList;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {
	
	@Autowired
	private CarroService services;

	@GetMapping
	public ResponseEntity<Iterable<CarroDTO>> get() {
		List<CarroDTO> carroDTOS = new ArrayList<CarroDTO>();
		services.getCarro().forEach(carroDTOS::add);
		return ResponseEntity.ok(carroDTOS);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity getById(@PathVariable("id") long  id){
		Optional<CarroDTO> carro = services.getCarroById(id);
		if(carro != null && carro.isPresent()) {
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
	public ResponseEntity post(@RequestBody Carro carro) {
		CarroDTO carroSalvo = services.save(carro);
		URI location = getUri(carroSalvo.getId());
		return ResponseEntity.created(location).build();
	}
	
	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(id).toUri();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity put(@PathVariable("id") long id, @RequestBody Carro carro) {
		CarroDTO carroupdate = services.put(id,carro);
		return carroupdate!=null? ResponseEntity.ok(carroupdate):
			ResponseEntity.notFound().build();
	}
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		services.delete(id);
		return ResponseEntity.ok("Registro Deletado com sucesso!");
		
	}

}
