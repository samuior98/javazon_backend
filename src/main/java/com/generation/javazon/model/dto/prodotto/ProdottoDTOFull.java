package com.generation.javazon.model.dto.prodotto;

import java.util.List;

import com.generation.javazon.model.dto.ordine.OrdineDTONoList;
import com.generation.javazon.model.entity.Cliente;
import com.generation.javazon.model.entity.Ordine;
import com.generation.javazon.model.entity.Prodotto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdottoDTOFull extends ProdottoDTONoList
{
	List<OrdineDTONoList> presenteInOrdini;

	public ProdottoDTOFull(Prodotto p)
	{
		super(p);

		this.presenteInOrdini = p.getPresenteInOrdini()
								.stream()
								.map(ordine -> new OrdineDTONoList(ordine))
								.toList();
	}

	public Prodotto convertToProdotto(Cliente cliente)
	{
		Prodotto p = super.convertToProdotto();

		List<Ordine> converted = presenteInOrdini
				.stream()
				.map(ordineDTO -> ordineDTO.convertToOrdine(cliente))
				.toList();

		p.setPresenteInOrdini(converted);

		return p;
	}
}
