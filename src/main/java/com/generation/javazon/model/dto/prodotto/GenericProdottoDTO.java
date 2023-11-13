package com.generation.javazon.model.dto.prodotto;

import com.generation.javazon.model.entity.Prodotto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
/**
 * DTO, classe astratta che funge da base per i DTO Prodotto futuri
 * @author samui
 *
 */
public abstract class GenericProdottoDTO {

	protected int id;
	protected String nome, tipologia, descrizione, url_foto;
	protected Double prezzo;
	protected boolean attivo;

	public GenericProdottoDTO() {}

	public GenericProdottoDTO(Prodotto p) {
		id= p.getId();
		nome= p.getNome();
		tipologia= p.getTipologia();
		descrizione= p.getDescrizione();
		url_foto= p.getUrl_foto();
		prezzo= p.getPrezzo();
		attivo= true;
	}

	public abstract Prodotto convertiInProdotto();
}
