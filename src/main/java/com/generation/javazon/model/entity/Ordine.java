package com.generation.javazon.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * Entità, classe che modella un Ordine
 * @author samui
 *
 */
public class Ordine extends BaseEntity {

	private LocalDateTime data_acquisto;
	private Double costo_spedizione = Math.random()*20;
	private boolean spedito;

	@ManyToMany(mappedBy = "presenteInOrdini", fetch = FetchType.EAGER)
	private List<Prodotto> prodottiInOrdine;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinColumn(name = "id_cliente")
	private Cliente id_cliente;


	@Override
	public List<String> getErrors() {
		List<String> res= new ArrayList<>();
		if(costo_spedizione<0)
			res.add("costo spedizione non valido");
		return res;
	}


	public double getCostoTot() {
		double tot= costo_spedizione;
		for(Prodotto p : prodottiInOrdine)
			tot += p.getPrezzo();
		return tot;
	}


	public boolean isSpedito() {
		return this.data_acquisto != null;
	}

}