package com.srm.asset.service.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.srm.asset.document.Cliente;
import com.srm.asset.document.RiscoEnum;
import com.srm.asset.repository.ClienteRepository;
import com.srm.asset.repository.ClienteRepositoryFirebase;
import com.srm.asset.service.ClienteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(value = "clienteService")
public class ClienteServiceImpl implements ClienteService {

	private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteRepositoryFirebase clienteRepositoryFirebase;

	public List<Cliente> findAll() {
		// return clienteRepository.findAll();
		return clienteRepositoryFirebase.findAll();
	}

	public Cliente add(Cliente cliente) {
		cliente.setId(UUID.randomUUID().toString());
		cliente.setLimiteCredito(getLimiteDeCreditoAplicadoTaxaDeJuros(cliente));
		// return clienteRepository.save(cliente);
		return clienteRepositoryFirebase.save(cliente);
	}

	public void delete(String id) {
		// clienteRepository.delete(clienteRepository.findById(UUID.fromString(id).toString()));
		clienteRepositoryFirebase.delete(id);
	}

	public Cliente update(Cliente cliente) {
		cliente.setLimiteCredito(getLimiteDeCreditoAplicadoTaxaDeJuros(cliente));
		// return clienteRepository.save(cliente);
		return clienteRepositoryFirebase.update(cliente);
	}

	/**
	 * Aplica a taxa de juros pelo Risco B(10%) ou C(20%).
	 * 
	 * @param cliente
	 * @return
	 */
	private double getLimiteDeCreditoAplicadoTaxaDeJuros(Cliente cliente) {
		log.info("Início da aplicação da Taxa de Juros pelo Risco B(10%) ou C(20%)");
		double limiteDeCredito = cliente.getLimiteCredito();

		if (RiscoEnum.B.getRisco() == cliente.getRisco()) {
			limiteDeCredito = Double.sum(RiscoEnum.B.getRisco() * cliente.getLimiteCredito() / 100,
					cliente.getLimiteCredito());
		} else if (RiscoEnum.C.getRisco() == cliente.getRisco()) {
			limiteDeCredito = Double.sum(RiscoEnum.C.getRisco() * cliente.getLimiteCredito() / 100,
					cliente.getLimiteCredito());
		}

		log.info("Fim da aplicação da Taxa de Juros pelo Risco B(10%) ou C(20%)");
		
		return limiteDeCredito;
	}

}