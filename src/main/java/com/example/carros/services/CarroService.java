package com.example.carros.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.carros.dto.CarroDTO;
import com.example.carros.entidades.Carro;
import com.example.carros.entidades.CarroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {
	
	@Autowired
	CarroRepository carroRep;
	
	
   public Iterable<CarroDTO> getCarro() {
		List<Carro> carros = carroRep.findAll();
		return carrosToCarrosDTO(carros);

	}
   
   private List<CarroDTO> carrosToCarrosDTO(List<Carro> carros){
		List<CarroDTO> carrosDTO = new ArrayList<>();
		for(Carro c: carros) {
			carrosDTO.add(CarroDTO.create(c));
		}
		return carrosDTO;
   }
	
	public List<Carro> getCarrosFake() {
		 List<Carro> carros = new ArrayList<Carro>();
		return carros;
	}

	public Optional<CarroDTO> getCarroById(long id) {
		Optional<Carro> carro = carroRep.findById(id);
		if(carro.isPresent()) {
			return Optional.of(CarroDTO.create(carro.get()));
		}
		else {
			return null;
		}
	}

	public List<CarroDTO> getCarroByTipo(String tipo) {
		List<Carro> carros = carroRep.findByTipo(tipo);
		return carrosToCarrosDTO(carros);
	}

	public Carro save(Carro carro) {
		return carroRep.save(carro);
		
	}

	public String put(long id, Carro carro) {
			Optional<Carro> optional = carroRep.findById(id);
			if(optional.isPresent()) {
				Carro db = optional.get();
				db.setNome(carro.getNome());
				db.setTipo(carro.getTipo());
				carroRep.save(db);
				return "Carro salvo com Sucesso";
			}
			else {
				return "Não existe carro com esse id";
			}
		
	}

	public void delete(Long id) {
		Optional<Carro> carro = carroRep.findById(id);
		if(carro.isPresent()) {
			carroRep.deleteById(id);
		}
	}

}