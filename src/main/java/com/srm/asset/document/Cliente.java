package com.srm.asset.document;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cliente implements Serializable {

	private static final long serialVersionUID = -3382573624228782219L;

	@Id
	private String id;
	private String nome;
	private double limiteCredito; // FIXME Não utilizei BigDecimal pois o FireBase apresentou erro e a solução
									// seria um pouco chata e trabalhosa...(Teria que fazer um delegate observando o
									// valor enviado e fazendo a troca para string para persistir no FireBase)
	private Integer risco;
	private boolean edicao;

	public Cliente(String id, String nome, double limiteCredito, Integer risco) {
		this.id = id;
		this.nome = nome;
		this.limiteCredito = limiteCredito;
		this.risco = risco;
	}

	public Cliente() {
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public Integer getRisco() {
		return risco;
	}

	public void setRisco(Integer risco) {
		boolean riscoValido = false;
		for (RiscoEnum riscoEnum : RiscoEnum.values()) {
			if (risco == riscoEnum.getRisco()) {
				this.risco = risco;
				riscoValido = true;
			}
		}

		if (riscoValido == Boolean.FALSE) {
			// TODO Criar Exception
		}
	}

	public boolean isEdicao() {
		return edicao;
	}

	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", limiteCredito=" + limiteCredito + ", risco=" + risco
				+ ", edicao=" + edicao + "]";
	}

	
}
