package com.generation.javazon.model.dto.cliente;

import com.generation.javazon.model.entity.Cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
/**
 * DTO, classe astratta che funge da base per un ClienteDTO
 * @author samui
 *
 */
public abstract class GenericClienteDTO {

	protected Integer id;
	protected String nome, cognome, indirizzo, username;

	public GenericClienteDTO() {}

	public GenericClienteDTO(Cliente a) {
		id = a.getId();
		nome = a.getNome();
		cognome = a.getCognome();
		indirizzo = a.getIndirizzo();
		username = a.getUsername();
	}

	public abstract Cliente convertiInCliente();

}
