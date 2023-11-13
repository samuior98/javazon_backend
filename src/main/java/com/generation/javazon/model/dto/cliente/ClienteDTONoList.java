package com.generation.javazon.model.dto.cliente;

import com.generation.javazon.model.entity.Cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTONoList {

	protected int id;
	protected String username, nome, cognome, indirizzo;


	public ClienteDTONoList(Cliente toTransfer) {
		this.id = toTransfer.getId();
		this.username = toTransfer.getUsername();
		this.nome = toTransfer.getNome();
		this.cognome = toTransfer.getCognome();
		this.indirizzo = toTransfer.getIndirizzo();
	}


	public Cliente convertToCliente() {
		Cliente c = new Cliente();
		c.setId(id);
		c.setUsername(username);
		c.setNome(nome);
		c.setCognome(cognome);
		c.setIndirizzo(indirizzo);
		return c;
	}

}
