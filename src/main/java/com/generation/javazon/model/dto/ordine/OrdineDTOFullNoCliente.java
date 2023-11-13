package com.generation.javazon.model.dto.ordine;

import java.time.LocalDateTime;

import com.generation.javazon.model.entity.Cliente;
import com.generation.javazon.model.entity.Ordine;

public class OrdineDTOFullNoCliente
{
	protected int id;
	protected LocalDateTime data_acquisto;
	protected int id_cliente;
	protected boolean spedito;
	protected Double costo_spedizione;

	public OrdineDTOFullNoCliente(Ordine ordine)
	{
		this.id= ordine.getId();
		this.data_acquisto = ordine.getData_acquisto();
		this.spedito = ordine.isSpedito();
		this.costo_spedizione = ordine.getCosto_spedizione();

	}

	public Ordine convertToOrdine(Cliente cliente)
	{
		Ordine ordine = new Ordine();
		ordine.setId(id);
		ordine.setCosto_spedizione(costo_spedizione);
		ordine.setSpedito(spedito);
		ordine.setData_acquisto(data_acquisto);

		return ordine;
	}

}
