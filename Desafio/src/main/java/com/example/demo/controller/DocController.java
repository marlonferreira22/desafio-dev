package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Doc;
import com.example.demo.model.Financeiro;
import com.example.demo.model.Loja;
import com.example.demo.model.Usuario;
import com.example.demo.service.DocStorageService;
import com.example.demo.service.FinanceiroStorageService;
import com.example.demo.service.LojaStorageService;
import com.example.demo.service.UsuarioStorageService;

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
	
	@GetMapping("/")
	public String get(Model model) {
		/*List<Doc> docs = docStorageService.getFiles();
		model.addAttribute("docs", docs);
		return "doc";*/
		List<Financeiro> fins = financeiroStorageService.getFinancas();
		model.addAttribute("fins", fins);
		return "index";
	}
	
	@GetMapping("/details/{lojaId}")
	public String getDetail(Model model, @PathVariable Integer lojaId) {
		/*List<Doc> docs = docStorageService.getFiles();
		model.addAttribute("docs", docs);
		return "doc";*/
		List<Financeiro> fins = financeiroStorageService.getFinancasByLojaId(lojaId);
		model.addAttribute("fins", fins);
		return "detalhe";
	}
	
	@PostMapping("/uploadFiles")
	public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) throws IOException {
		for (MultipartFile file: files) {
			
			//Interpretar ("parsear") o arquivo recebido, normalizar os dados, e salvar corretamente a informação em um banco
			
			
			/*byte[] content = file.getBytes();
			String att = new String(content);
			
			Financeiro fin = setValoresFinanceiro(att);
			
			System.out.println(fin);
			
			financeiroStorageService.saveFinanceiro(fin);
			
			docStorageService.saveFile(file);*/
			
			InputStream inputStream = file.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            List<String> list = br.lines().collect(Collectors.toList());
            
            for (int i = 0; i < list.size(); i++) {
            	 //System.out.println(list.get(i));
            	 Financeiro fin = setValores(list.get(i));
            	 System.out.println(fin);
            	 
            	 financeiroStorageService.saveFinanceiro(fin);
     			
     			 docStorageService.saveFile(file);
			}
			
		}
		return "redirect:/";
	}
	
	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
		Doc doc = docStorageService.getFile(fileId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(doc.getDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
				.body(new ByteArrayResource(doc.getData()));
	}
	
	public Financeiro setValores(String att) {
		
		Financeiro fin = new Financeiro();
		
		String tipo = att.substring(0, 1);
		String data = att.substring(1, 9);
		String valor = att.substring(9, 19);
		String cpf = att.substring(19, 30);
		String cartao = att.substring(30, 42);
		String hora = att.substring(42, 48);
		String responsavel = att.substring(48, 62);
		String loja = att.substring(62, 80);
		
		fin.setTipo(tipo);
		fin.setData(data);
		fin.setValor(convertToBigDecimal(valor));
		//fin.setCpf(cpf);
		fin.setCartao(cartao);
		fin.setHora(hora);
		//fin.setResponsavel(responsavel);
		//fin.setLoja(loja);
		
		Loja lojaObj = lojaStorageService.getLojaByNome(loja);
		if(lojaObj == null) {
			Loja lojaNova = new Loja(loja);
			Loja lojaSaved = lojaStorageService.saveLoja(lojaNova);
			fin.setLoja(lojaSaved);
		} else {
			fin.setLoja(lojaObj);
		}
		
		
		Usuario usuario = usuarioStorageService.getUsuario(cpf);
		BigDecimal novoSaldo = new BigDecimal(0);
		
		if(usuario == null) {
			if(tipo.equalsIgnoreCase("2") || tipo.equalsIgnoreCase("3") || tipo.equalsIgnoreCase("9")) {
				novoSaldo = convertToBigDecimal(valor).negate();
			} else {
				novoSaldo = convertToBigDecimal(valor);
			}
			Usuario user = new Usuario(responsavel, cpf, novoSaldo);
			Usuario UsuarioSaved = usuarioStorageService.saveUsuario(user);
			fin.setUsuario(UsuarioSaved);
		} else {
			fin.setUsuario(usuario);			
			switch (tipo) {
			case "1":
				novoSaldo = usuario.getSaldo().add(convertToBigDecimal(valor));
				break;
			case "2":
				novoSaldo = usuario.getSaldo().subtract(convertToBigDecimal(valor));
				break;
			case "3":
				novoSaldo = usuario.getSaldo().subtract(convertToBigDecimal(valor));
				break;
			case "4":
				novoSaldo = usuario.getSaldo().add(convertToBigDecimal(valor));
				break;
			case "5":
				novoSaldo = usuario.getSaldo().add(convertToBigDecimal(valor));
				break;
			case "6":
				novoSaldo = usuario.getSaldo().add(convertToBigDecimal(valor));
				break;
			case "7":
				novoSaldo = usuario.getSaldo().add(convertToBigDecimal(valor));
				break;
			case "8":
				novoSaldo = usuario.getSaldo().add(convertToBigDecimal(valor));
				break;
			case "9":
				novoSaldo = usuario.getSaldo().subtract(convertToBigDecimal(valor));
				break;
			default:
				novoSaldo = usuario.getSaldo();
				break;
			}
			
			//BigDecimal novoSaldo = usuario.getSaldo().add(convertToBigDecimal(valor));
			usuario.setSaldo(novoSaldo);
			usuarioStorageService.saveUsuario(usuario);
		}
		
		/*System.out.println("tipo: " + tipo); 
		System.out.println("data: " + data);
		System.out.println("valor: " + valor);
		System.out.println("cpf: " + cpf);
		System.out.println("cartao: " + cartao);
		System.out.println("hora: " + hora);
		System.out.println("responsavel: " + responsavel);
		System.out.println("loja: " + loja);*/
		
		return fin;
		
	}
	
	public BigDecimal convertToBigDecimal(String valor) {
		
		BigDecimal valorConvertido = new BigDecimal(valor);
		BigDecimal divisor = new BigDecimal("100.00");
		return valorConvertido.divide(divisor);
	}
	
}
