package com.example.carros.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
	@GetMapping
	public String get() {
		return "Get Spring Boot";
	}
	@GetMapping("/hello")
	public String getParametro() {
		return "Get url Spring Boot";
	}
	@GetMapping("/RequestParam")
	public String getParametro(@RequestParam String parametro1, @RequestParam String parametro2) {
		return "GET RequestParam - " + parametro1 + " - " + parametro2;
	}
	@GetMapping("/Path/{a}/{b}")
	public String getParametroUrl(@PathVariable String a, @PathVariable String b) {
		return "GET @PathVariable - " + a + " - " + b;
	}
	@PostMapping("/Path")
	public String getParametroUrlPost(@RequestParam String a, @RequestParam String b) {
		return "Post RequestParam - " + a + " - " + b;
	}
	@PostMapping
	public String post() {
		return "Post Spring Boot";
	}
	@PutMapping
	public String put() {
		return "Put Spring Boot";
	}
	@DeleteMapping
	public String delete() {
		return "Delete Spring Boot";
	}

}
