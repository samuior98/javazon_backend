package com.generation.javazon.model.dto.cliente;

import com.generation.javazon.model.dto.ordine.OrdineDTOFull;
import com.generation.javazon.model.entity.Cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTOwCart extends ClienteDTONoList {

	OrdineDTOFull carrello;

	public ClienteDTOwCart(Cliente c) {
		super(c);
		carrello= c.getOrdini().stream()
				.filter(ordine -> !ordine.isSpedito())
				.map(ordine -> new OrdineDTOFull(ordine)).findFirst().get();
	}

}