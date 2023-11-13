package com.generation.javazon.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.javazon.controller.util.InvalidEntityException;
import com.generation.javazon.model.dto.prodotto.ProdottoDTONoList;
import com.generation.javazon.model.entity.Ordine;
import com.generation.javazon.model.entity.Prodotto;
import com.generation.javazon.model.repositories.ClienteRepository;
import com.generation.javazon.model.repositories.OrdineRepository;
import com.generation.javazon.model.repositories.ProdottoRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProdottoController
{

	@Autowired
	ClienteRepository cRepo;

	@Autowired
	OrdineRepository oRepo;

	@Autowired
	ProdottoRepository pRepo;

	@GetMapping("/prodotti")
	public List<ProdottoDTONoList> getAllProdotti()
	{
		List<ProdottoDTONoList> prodottiDTO = pRepo.findAll()
											.stream()
											.map(prodotto -> new ProdottoDTONoList(prodotto))
											.toList();

		return prodottiDTO;
	}

	@GetMapping("/prodotti/{id}/nolist")
	public ProdottoDTONoList getOneProdotto(@PathVariable Integer id)
	{
		Optional<Prodotto> p= pRepo.findById(id);
		if(p.isEmpty())
			throw new NoSuchElementException("Non ho trovato nessun elemento con quell'id");

		return new ProdottoDTONoList(p.get());
	}

	@PostMapping("/prodotti")
	public ProdottoDTONoList postNewProdotto(@RequestBody ProdottoDTONoList dto)
	{
		Prodotto toInsert = dto.convertToProdotto();

		if(!toInsert.isValid())
			throw new InvalidEntityException(toInsert.getErrors().stream().reduce("Errore: \n",String::concat));

		return new ProdottoDTONoList(pRepo.save(toInsert));
	}

	@PutMapping("/prodotto/{id}")
	public ProdottoDTONoList updateProdotto(@PathVariable int id,@RequestBody ProdottoDTONoList dto)
	{
		Prodotto toUpdate = pRepo.findById(id).get();
		if(toUpdate == null)
			throw new NoSuchElementException("Non ho trovato sul db il prodotto che vuoi modificare");

		List<Ordine> ordini = toUpdate.getPresenteInOrdini();

		Prodotto updated = dto.convertToProdotto();
		updated.setPresenteInOrdini(ordini);

		if(!updated.isValid())
			throw new InvalidEntityException(updated.getErrors().stream().reduce("Errore: \n",String::concat));

		return new ProdottoDTONoList(pRepo.save(updated));
	}

	@DeleteMapping("/prodotto/{id}")
	public void deleteOneProdotto(@PathVariable Integer id)
	{
		Optional<Prodotto> toDisable = pRepo.findById(id);

		if(toDisable.isEmpty())
			throw new NoSuchElementException("Non ho trovato nessun elemento da cancellare con quell'id");

		toDisable.get().setAttivo(false);
		pRepo.save(toDisable.get());

	}


}
