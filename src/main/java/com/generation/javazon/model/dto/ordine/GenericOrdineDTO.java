package com.generation.javazon.model.dto.ordine;

import java.time.LocalDateTime;

import com.generation.javazon.model.entity.Ordine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
/**
 * DTO, classe base per un OrdineDTO
 * @author samui
 *
 */
public class GenericOrdineDTO {

	protected Integer id;
	protected LocalDateTime data_acquisto;
	protected final static Double costo_spedizione = Math.random()*20;
	protected boolean spedito;
	protected int id_cliente;

	public GenericOrdineDTO(Ordine o) {
		id= o.getId();
		data_acquisto= o.getData_acquisto();
	}

	public Ordine convertiInOrdine() {
		Ordine ordine = new Ordine();
		ordine.setId(id);
		ordine.setData_acquisto(data_acquisto);
		ordine.setSpedito(spedito);
		return ordine;
	}

}
