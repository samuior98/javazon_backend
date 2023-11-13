package com.generation.javazon.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.generation.javazon.library.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
/**
 * Entità, classe che modella un Cliente
 * @author samui
 *
 */
public class Cliente extends BaseEntity {

	protected String username, nome, cognome, indirizzo;

	@OneToMany(mappedBy = "id_cliente", fetch = FetchType.EAGER)
	protected List<Ordine> ordini;


	@Override
	public List<String> getErrors() {
		List<String> res= new ArrayList<>();
		if(username == null || username.isBlank() || username.isEmpty())
			res.add("username non valida");

		if(nome == null || nome.isBlank() || nome.isEmpty())
			res.add("nome non valido");

		if(cognome == null || cognome.isBlank() || cognome.isEmpty())
			res.add("cognome non valido");

		if(indirizzo == null || indirizzo.isBlank() || indirizzo.isEmpty())
			res.add("indirizzo non valido");
		return res;
	}


	@Override
	public boolean isValid() {
		return getErrors().size()==0;
	}

}
