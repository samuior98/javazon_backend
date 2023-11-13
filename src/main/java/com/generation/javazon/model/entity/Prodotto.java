package com.generation.javazon.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
 * Entità, classe che modella un Prodotto
 * @author samui
 *
 */
public class Prodotto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String nome, tipologia,	descrizione, url_foto;
	private Double prezzo;
	private boolean attivo;


	@ManyToMany
	@JoinTable(
			name = "prodotto_to_ordine",
			joinColumns = @JoinColumn(name="id_prodotto"),
			inverseJoinColumns = @JoinColumn(name= "id_ordine")
	)
	private List<Ordine> presenteInOrdini;


	/**
	 * INPUT: nulla
	 * OUTPUT: lista di stringhe contenente gli errori
	 * @return
	 */
	public List<String> getErrors() {
		List<String> res= new ArrayList<>();
		if(tipologia == null || tipologia.isBlank() || tipologia.isEmpty())
			res.add("tipologia non valida");

		if(nome == null || nome.isBlank() || nome.isEmpty())
			res.add("nome non valido");

		if(descrizione == null || descrizione.isBlank() || descrizione.isEmpty())
			res.add("descrizione non valida");

		if(url_foto == null || url_foto.isBlank() || url_foto.isEmpty())
			res.add("url_foto non valida");

		if(prezzo == null || prezzo<=0)
			res.add("prezzo non valido");
		return res;
	}


	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

}