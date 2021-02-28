package com.example.carros;

import com.example.carros.dto.CarroDTO;
import com.example.carros.entidades.Carro;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarrosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarrosApiTest {

    @Autowired
    protected TestRestTemplate rest;

    private ResponseEntity<CarroDTO> getCarroById(String url){
        return rest.withBasicAuth("user","123").getForEntity(url,CarroDTO.class);
    }
    private ResponseEntity<List<CarroDTO>> getCarros(String url){
        return rest.withBasicAuth("user","123").exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CarroDTO>>() {
                }
        );
    }
    private ResponseEntity put(String url, HttpEntity entity){
        return  rest.withBasicAuth("admin","123").exchange(
                url,
                HttpMethod.PUT,
                entity,
                CarroDTO.class
        );
    }

    @Test
    public void getCarroById(){
        ResponseEntity<CarroDTO> carroDto = getCarroById("/api/v1/carros/2");
        assertEquals(carroDto.getStatusCode(), HttpStatus.OK);
        CarroDTO carro = carroDto.getBody();
        assertEquals("Chevrolet Corvette",carro.getNome());
    }
    @Test
    public void getCarros(){
        ResponseEntity<List<CarroDTO>> carros = getCarros("/api/v1/carros/");
        assertEquals(carros.getStatusCode(), HttpStatus.OK);
        assertNotNull(carros);
        List<CarroDTO> list = carros.getBody();
        assertEquals(list.size(),30);
    }

    @Test
    public void getCarrosTipo(){
        ResponseEntity<List<CarroDTO>> carros = getCarros("/api/v1/carros/tipos/classicos");
        assertEquals(carros.getStatusCode(), HttpStatus.OK);
        assertNotNull(carros);
        List<CarroDTO> list = carros.getBody();
        assertEquals(list.size(),10);
    }
    @Test
    public void save(){
        Carro carro = new Carro();
        carro.setNome("Teste");
        carro.setTipo("classicos");
        carro.setDescricao("Descrição Teste");
        ResponseEntity response = rest.withBasicAuth("admin","123").postForEntity("/api/v1/carros",carro,null);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        String location = response.getHeaders().get("location").get(0);

        CarroDTO carroDTO = getCarroById(location).getBody();
        assertNotNull(carroDTO);
        assertEquals("Teste",carroDTO.getNome());
        assertEquals("classicos",carroDTO.getTipo());

        rest.withBasicAuth("admin","123").delete(location);
        assertEquals(HttpStatus.NOT_FOUND, getCarros(location).getStatusCode());
    }
    @Test
    public void update(){
        CarroDTO carroInicio = getCarroById("/api/v1/carros/3").getBody();
        Carro carro = new Carro();
        carro.setNome("Teste update");
        carro.setTipo("luxo");
        HttpEntity<Carro> entity = new HttpEntity<>(carro, null);
        ResponseEntity response = put("/api/v1/carros/3",entity);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        CarroDTO carroDTO = getCarroById("/api/v1/carros/3").getBody();
        assertEquals("Teste update",carroDTO.getNome());
        assertEquals("luxo",carroDTO.getTipo());

        carro.setNome(carroInicio.getNome());
        carro.setTipo(carroInicio.getTipo());
        HttpEntity<Carro> entityInicio = new HttpEntity<Carro>(carro, null);

        response = put("/api/v1/carros/3",entityInicio);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        carroDTO = getCarroById("/api/v1/carros/3").getBody();
        assertEquals(carroInicio.getNome(),carroDTO.getNome());
        assertEquals(carroInicio.getTipo(),carroDTO.getTipo());

    }
}
