package com.generation.javazon.model.dto.ordine;

import java.util.List;

import com.generation.javazon.model.dto.prodotto.ProdottoDTONoList;
import com.generation.javazon.model.entity.Cliente;
import com.generation.javazon.model.entity.Ordine;
import com.generation.javazon.model.entity.Prodotto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdineDTOFull extends OrdineDTONoList {

	private List<ProdottoDTONoList> prodotti;

	public OrdineDTOFull(Ordine o) {
		super(o);
		this.prodotti = o.getProdottiInOrdine()
				.stream()
				.map(prodotto -> new ProdottoDTONoList(prodotto))
				.toList();
	}


	@Override
	public Ordine convertToOrdine(Cliente cliente) {
		Ordine o = super.convertToOrdine(cliente);
		List<Prodotto> converted = prodotti
									.stream()
									.map(prodottoDTO -> prodottoDTO.convertToProdotto())
									.toList();
		o.setProdottiInOrdine(converted);
		return o;
	}

}
