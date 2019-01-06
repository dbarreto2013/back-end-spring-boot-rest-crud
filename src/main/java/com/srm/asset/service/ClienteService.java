package com.srm.asset.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.srm.asset.document.Cliente;

@Component
public interface ClienteService {

	public List<Cliente> findAll();

	public Cliente add(Cliente cliente);

	public void delete(String id);

	public Cliente update(Cliente cliente);

}