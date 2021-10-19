package com.example.demo.controller.rest;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Financeiro;
import com.example.demo.model.Loja;
import com.example.demo.model.Usuario;
import com.example.demo.service.FinanceiroStorageService;
import com.example.demo.service.LojaStorageService;
import com.example.demo.service.UsuarioStorageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
@Api(tags = "API", description = "API dos serviços")
public class ApiController {
	
	@Autowired 
	private FinanceiroStorageService financeiroStorageService;
	
	@Autowired
	private LojaStorageService lojaStorageService;
	
	@Autowired
	private UsuarioStorageService usuarioStorageService;
	
	@ApiOperation(value = "Lista os dados financeiros")
	@GetMapping(value = "/financeiro", produces = "application/json")
	public List<Financeiro> get() {
		List<Financeiro> fins = financeiroStorageService.getFinancas();		
		
		return fins;
	}
	
	@ApiOperation(value = "Lista as lojas")
	@GetMapping(value = "/loja", produces = "application/json")
	public List<Loja> getLoja() {
		List<Loja> loja = lojaStorageService.getLojas();		
		
		return loja;
	}
	
	@ApiOperation(value = "Lista os usuários")
	@GetMapping(value = "/usuario", produces = "application/json")
	public List<Usuario> getUsuario() {
		List<Usuario> usuario = usuarioStorageService.getUsuarios();	
		
		return usuario;
	}
}
