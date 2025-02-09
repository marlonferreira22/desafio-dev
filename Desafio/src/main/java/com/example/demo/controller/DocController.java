package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Financeiro;
import com.example.demo.model.Loja;
import com.example.demo.model.Usuario;
import com.example.demo.service.DocStorageService;
import com.example.demo.service.FinanceiroStorageService;
import com.example.demo.service.LojaStorageService;
import com.example.demo.service.UsuarioStorageService;
import com.example.demo.util.Util;

@Controller
public class DocController {

	@Autowired 
	private DocStorageService docStorageService;
	
	@Autowired 
	private FinanceiroStorageService financeiroStorageService;
	
	@Autowired
	private UsuarioStorageService usuarioStorageService;
	
	@Autowired
	private LojaStorageService lojaStorageService;
	
	private Util util = new Util();
	
	@GetMapping("/")
	public String get(Model model) {
		
		List<Financeiro> fins = financeiroStorageService.getFinancas();
		model.addAttribute("fins", fins);
		return "index";
	}
	
	@GetMapping("/details/{lojaId}")
	public String getDetail(Model model, @PathVariable Integer lojaId) {
		
		List<Financeiro> fins = financeiroStorageService.getFinancasByLojaId(lojaId);
		model.addAttribute("fins", fins);
		return "detalhe";
	}
	
	@PostMapping("/uploadFiles")
	public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) throws IOException, ParseException {
		for (MultipartFile file: files) {
			
			InputStream inputStream = file.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            List<String> list = br.lines().collect(Collectors.toList());
            
            // Percorre cada registro do arquivo normalizando os dados
            for (int i = 0; i < list.size(); i++) {
            	 Financeiro fin = setValores(list.get(i));
            	 System.out.println(fin);
            	 
            	 financeiroStorageService.saveFinanceiro(fin);
     			
     			 //docStorageService.saveFile(file);
			}
			
		}
		return "redirect:/";
	}
	
	/*@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
		Doc doc = docStorageService.getFile(fileId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(doc.getDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
				.body(new ByteArrayResource(doc.getData()));
	}*/
	
	public Financeiro setValores(String att) throws ParseException {
		
		Financeiro fin = new Financeiro();
		
		String tipo = att.substring(0, 1);
		String data = att.substring(1, 9);
		String valor = att.substring(9, 19);
		String cpf = att.substring(19, 30);
		String cartao = att.substring(30, 42);
		String hora = att.substring(42, 48);
		String responsavel = att.substring(48, 62);
		String loja = att.substring(62, 80);
		
		fin.setTipo(util.convetStringToTipoOperacao(tipo));
		fin.setData(util.convertStringToDate(data));
		fin.setValor(util.convertStringToBigDecimal(valor));
		fin.setCartao(cartao);
		fin.setHora(util.convertStringToHours(hora));
		
		// Remove os espaços em branco no fim da string
		loja = StringUtils.trimWhitespace(loja);
		
		// Verifica se a loja já está cadastrada para que não tenha dados duplicados
		Loja lojaObj = lojaStorageService.getLojaByNome(loja);
		if(lojaObj == null) {
			Loja lojaNova = new Loja(loja);
			Loja lojaSaved = lojaStorageService.saveLoja(lojaNova);
			fin.setLoja(lojaSaved);
		} else {
			fin.setLoja(lojaObj);
		}
		
		// Remove os espaços em branco no fim da string
		responsavel = StringUtils.trimWhitespace(responsavel);
		
		Usuario usuario = usuarioStorageService.getUsuario(cpf);
		BigDecimal novoSaldo = new BigDecimal(0);
		
		/**
		 * Verifica se o usuário já está cadastrado, 
		 * caso positivo realiza o calculo do saldo.
		 * Caso negativo, verifica se a operação é de entrada ou saída, 
		 * caso a operação seja de saída o novo usuário é cadastrado com saldo negativo. 
		 */
		if(usuario == null) {
			if(tipo.equalsIgnoreCase("2") || tipo.equalsIgnoreCase("3") || tipo.equalsIgnoreCase("9")) {
				novoSaldo = util.convertStringToBigDecimal(valor).negate();
			} else {
				novoSaldo = util.convertStringToBigDecimal(valor);
			}
			Usuario user = new Usuario(responsavel, cpf, novoSaldo);
			Usuario UsuarioSaved = usuarioStorageService.saveUsuario(user);
			fin.setUsuario(UsuarioSaved);
		} else {
			fin.setUsuario(usuario);			
			switch (tipo) {
			case "1":
				novoSaldo = usuario.getSaldo().add(util.convertStringToBigDecimal(valor));
				break;
			case "2":
				novoSaldo = usuario.getSaldo().subtract(util.convertStringToBigDecimal(valor));
				break;
			case "3":
				novoSaldo = usuario.getSaldo().subtract(util.convertStringToBigDecimal(valor));
				break;
			case "4":
				novoSaldo = usuario.getSaldo().add(util.convertStringToBigDecimal(valor));
				break;
			case "5":
				novoSaldo = usuario.getSaldo().add(util.convertStringToBigDecimal(valor));
				break;
			case "6":
				novoSaldo = usuario.getSaldo().add(util.convertStringToBigDecimal(valor));
				break;
			case "7":
				novoSaldo = usuario.getSaldo().add(util.convertStringToBigDecimal(valor));
				break;
			case "8":
				novoSaldo = usuario.getSaldo().add(util.convertStringToBigDecimal(valor));
				break;
			case "9":
				novoSaldo = usuario.getSaldo().subtract(util.convertStringToBigDecimal(valor));
				break;
			default:
				novoSaldo = usuario.getSaldo();
				break;
			}
			
			usuario.setSaldo(novoSaldo);
			usuarioStorageService.saveUsuario(usuario);
		}
		
		return fin;
		
	}
}
