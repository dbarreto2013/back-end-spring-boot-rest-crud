package com.srm.asset.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.srm.asset.document.Cliente;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ClienteRepositoryFirebase {

	private static final Logger log = LoggerFactory.getLogger(ClienteRepositoryFirebase.class);
	private ArrayList<Cliente> clientes = new ArrayList<>();

	/**
	 * Construtor que inicializa o FireBase
	 * 
	 */
	public ClienteRepositoryFirebase() {

		try {
			log.info("Inicializando o FireBase");
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(
							new FileInputStream("brasileiro-7898a-firebase-adminsdk-uklvg-86b9e3f94c.json")))
					.setDatabaseUrl("https://brasileiro-7898a.firebaseio.com/").build();
			FirebaseApp.initializeApp(options);

		} catch (FileNotFoundException e) {
			log.error("Arquivo admin(json) do FireBase não encontrado", e);
		} catch (IOException e) {
			log.error("Falha ao carregar o arquivo admin(json) do FireBase", e);
		}

	}

	/**
	 * Persist o Cliente no FireBase
	 * 
	 * @param cliente
	 * @return
	 */
	public Cliente save(Cliente cliente) {

		log.info("Início da persistência do cliente no FireBase");
		final FirebaseDatabase database = FirebaseDatabase.getInstance("https://brasileiro-7898a.firebaseio.com/");
		DatabaseReference ref = database.getReference("srm-asset/database");

		this.clientes.add(cliente);
		ref.setValueAsync(this.clientes);

		/*
		 * Enfrentei problemas de performance no Firebase e adicionando um sleep ele
		 * funcionou... Utilizando o wait ref.setValueAsync(this.clientes).wait(); -
		 * Apresentou outros problemas... que ficou muito em cima pra acertar
		 */
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			log.error("Falha ao persistir cliente no FireBase");
		}
		log.info("Fim da persistência do cliente no FireBase");

		return cliente;

	}

	/**
	 * Atualiza o cliente no FireBase
	 * 
	 * @param cliente
	 * @return
	 */
	public Cliente update(Cliente cliente) {
		log.info("Início da atualização do cliente no FireBase");
		
		ArrayList<Cliente> clientes = findAll();
		this.clientes = (ArrayList<Cliente>) clientes.stream()
				.map(clienteMap -> clienteMap.getId().equals(cliente.getId()) ? cliente : clienteMap)
				.collect(Collectors.toList());

		final FirebaseDatabase database = FirebaseDatabase.getInstance("https://brasileiro-7898a.firebaseio.com/");
		DatabaseReference ref = database.getReference("srm-asset/database");

		ref.setValueAsync(this.clientes);

		/*
		 * Enfrentei problemas de performance no Firebase e adicionando um sleep ele
		 * funcionou... Utilizando o wait ref.setValueAsync(this.clientes).wait(); -
		 * Apresentou outros problemas... que ficou muito em cima pra acertar
		 */
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			log.error("Falha ao atualizar cliente no FireBase - error: {}", e);
		}

		log.info("Fim da atualização do cliente no FireBase");
		
		return cliente;
	}

	
	/**
	 * Busca todos os clientes no FireBase
	 * 
	 * @return
	 */
	public ArrayList<Cliente> findAll() {
		log.info("Início da busca de todos os clientes no FireBase");
		
		clientes = new ArrayList<>();
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("srm-asset/database");

		ref.addValueEventListener(new ValueEventListener() {
			public void onDataChange(DataSnapshot dataSnapshot) {

				GenericTypeIndicator<List<Cliente>> t = new GenericTypeIndicator<List<Cliente>>() {
				};
				List<Cliente> messages = dataSnapshot.getValue(t);
				if (messages != null) {
					clientes.addAll(messages);
				}
			}

			public void onCancelled(DatabaseError databaseError) {
				log.error("Falha na busca de todos os clientes no FireBase - error: {}", databaseError.getCode());
			}

		});

		/*
		 * Enfrentei problemas de performance no Firebase e adicionando um sleep ele
		 * funcionou... Utilizando o wait ref.setValueAsync(this.clientes).wait(); -
		 * Apresentou outros problemas... que ficou muito em cima pra acertar
		 */
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			log.error("Falha ao atualizar cliente no FireBase - error: {}", e);
		}
		
		log.info("Fim da busca de todos os clientes no FireBase");
		
		return clientes;
	}

	/**
	 * Deleta um cliente por id
	 * 
	 * @param id
	 */
	public void delete(String id) {
		
		log.info("Início da chamada de deleção de cliente no FireBase");
		
		ArrayList<Cliente> clientes = findAll();
		this.clientes = (ArrayList<Cliente>) clientes.stream()
				.map(clienteMap -> (clienteMap != null && clienteMap.getId().equals(id)) ? null : clienteMap)
				.collect(Collectors.toList());

		final FirebaseDatabase database = FirebaseDatabase.getInstance("https://brasileiro-7898a.firebaseio.com/");
		DatabaseReference ref = database.getReference("srm-asset/database");
		ref.setValueAsync(this.clientes);

		/*
		 * Enfrentei problemas de performance no Firebase e adicionando um sleep ele
		 * funcionou... Utilizando o wait ref.setValueAsync(this.clientes).wait(); -
		 * Apresentou outros problemas... que ficou muito em cima pra acertar
		 */
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			log.error("Falha ao atualizar cliente no FireBase - error: {}", e);
		}
		
		log.info("Fim da chamada de deleção de cliente no FireBase");

	}

}