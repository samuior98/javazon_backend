package com.generation.javazon.model.dto.ordine;

import java.time.LocalDateTime;

import com.generation.javazon.model.entity.Cliente;
import com.generation.javazon.model.entity.Ordine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdineDTONoList {

	protected int id;
	protected LocalDateTime data_acquisto;
	protected int id_cliente;
	protected boolean spedito;
	protected Double costo_spedizione;


	public OrdineDTONoList(Ordine ordine) {
		this.id= ordine.getId();
		this.data_acquisto = ordine.getData_acquisto();
		this.id_cliente = ordine.getId_cliente().getId();
		this.spedito = ordine.isSpedito();
		this.costo_spedizione = ordine.getCosto_spedizione();
	}


	public Ordine convertToOrdine(Cliente cliente) {
		Ordine ordine = new Ordine();
		ordine.setId(id);
		ordine.setId_cliente(cliente);
		ordine.setCosto_spedizione(costo_spedizione);
		ordine.setSpedito(spedito);
		ordine.setData_acquisto(data_acquisto);
		return ordine;
	}

}
