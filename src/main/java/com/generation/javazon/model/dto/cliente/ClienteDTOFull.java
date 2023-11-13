package com.generation.javazon.model.dto.cliente;

import java.util.List;

import com.generation.javazon.model.dto.ordine.OrdineDTOFull;
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
public class ClienteDTOFull extends ClienteDTONoList {

	List<OrdineDTOFull> ordini;

	public ClienteDTOFull(Cliente cliente) {
		super(cliente);
		this.ordini = cliente.getOrdini()
						.stream()
						.map(ordine -> new OrdineDTOFull(ordine))
						.toList();
	}


	@Override
	public Cliente convertToCliente() {
		Cliente c = super.convertToCliente();
		List<Ordine> converted = ordini
									.stream()
									.map(ordineDTO -> ordineDTO.convertToOrdine(c))
									.toList();
		c.setOrdini(converted);
		return c;
	}

}
