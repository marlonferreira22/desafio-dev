package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Financeiro;
import com.example.demo.service.FinanceiroStorageService;

@RestController
@RequestMapping(value = "/index")
@CrossOrigin
public class IndexController {
	
	@Autowired 
	private FinanceiroStorageService financeiroStorageService;
		
	@GetMapping(value = "/", produces = "application/json")
	public List<Financeiro> get() {
		List<Financeiro> fins = financeiroStorageService.getFinancas();
		
		return fins;
	}
}
