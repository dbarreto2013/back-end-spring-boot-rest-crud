package com.srm.asset.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import com.srm.asset.document.Cliente;

@Component
public interface ClienteRepository extends MongoRepository<Cliente, Integer> {

	/**
	 * Pesquisa de Cliente por id
	 * 
	 * @param id
	 * @return
	 */
	@Query("{ 'id' : ?0 }")
	Cliente findById(String id);

}
