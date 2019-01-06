package com.srm.asset.resource;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srm.asset.document.Cliente;
import com.srm.asset.service.ClienteService;

@Component
@RestController
@RequestMapping("/cliente")
public class ClienteResource {

	@Autowired
	@Qualifier("clienteService")
	private ClienteService clienteService;

	@GetMapping("/clientes")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Cliente> getClientes() {
		return clienteService.findAll();
	}

	@PostMapping("/add")
	@CrossOrigin(origins = "http://localhost:4200")
	Cliente add(@RequestBody Cliente cliente) {
		return clienteService.add(cliente);
	}

	@DeleteMapping("/delete/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	void delete(@PathVariable String id) {
		clienteService.delete(id);
	}

	@PutMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	Cliente update(@RequestBody Cliente cliente, @PathVariable String id) {
		cliente.setId(UUID.fromString(id).toString());
		return clienteService.update(cliente);
	}

}