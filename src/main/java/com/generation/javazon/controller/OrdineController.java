package com.generation.javazon.controller;

import java.time.LocalDateTime;
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

import com.generation.javazon.controller.util.ForbiddenOperationException;
import com.generation.javazon.controller.util.InvalidEntityException;
import com.generation.javazon.model.dto.ordine.OrdineDTOFull;
import com.generation.javazon.model.dto.ordine.OrdineDTONoList;
import com.generation.javazon.model.entity.Cliente;
import com.generation.javazon.model.entity.Ordine;
import com.generation.javazon.model.entity.Prodotto;
import com.generation.javazon.model.repositories.ClienteRepository;
import com.generation.javazon.model.repositories.OrdineRepository;
import com.generation.javazon.model.repositories.ProdottoRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class OrdineController {

	@Autowired
	ClienteRepository cRepo;

	@Autowired
	OrdineRepository oRepo;

	@Autowired
	ProdottoRepository pRepo;


	@GetMapping("/ordini")
	public List<OrdineDTONoList> getAll() {
		List<OrdineDTONoList> ordiniDTO = oRepo.findAll()
			.stream()
			.map(cliente -> new OrdineDTONoList(cliente))
			.toList();

		return ordiniDTO;
	}


	@GetMapping("/ordini/{id}/full")
	public OrdineDTOFull getOneOrdine(@PathVariable Integer id) {
		Optional<Ordine> op = oRepo.findById(id);
		if(op.isEmpty())
			throw new NoSuchElementException("Non ho trovato nessun elemento con quell'id");

		return new OrdineDTOFull(op.get());
	}


	@PostMapping("/ordini/{id}")
	public OrdineDTONoList acquista(@PathVariable Integer id) {
		//prendo ordine dal db
		Ordine o= oRepo.findById(id).get();
		if(!o.isValid())
			throw new InvalidEntityException("ordine non valido");

		o.setData_acquisto(LocalDateTime.now());
		oRepo.save(o);

		Ordine newCarrello= new Ordine();
		newCarrello.setSpedito(false);
		newCarrello.setId_cliente(o.getId_cliente());
		newCarrello= oRepo.save(newCarrello);

		return new OrdineDTONoList(newCarrello);
	}


	@PostMapping("/ordini")
    public OrdineDTONoList insert(@RequestBody OrdineDTONoList dto) {
        Cliente c = cRepo.findById(dto.getId_cliente()).get();
        Ordine toInsert = dto.convertToOrdine(c);
        return new OrdineDTONoList(oRepo.save(toInsert));
    }


	@PutMapping("/ordini/{ido}/prodotti/{idp}")
	public OrdineDTOFull update(@PathVariable Integer ido, @PathVariable Integer idp)
	{
		Optional<Ordine> o = oRepo.findById(ido);
		if(o.isEmpty())
			throw new NoSuchElementException("Non ho trovato nessun ordine con quell'id");
		Optional<Prodotto> p = pRepo.findById(idp);
		if(p.isEmpty())
			throw new NoSuchElementException("Non ho trovato nessun ordine con quell'id");

		if(o.get().isSpedito())
			throw new ForbiddenOperationException("Non è possibile modificare un ordine già spedito");

		Ordine toUpdate = o.get();
		Prodotto toAdd = p.get();

		toAdd.getPresenteInOrdini().add(toUpdate);
		toUpdate.getProdottiInOrdine().add(toAdd);
		pRepo.save(toAdd);

		return new OrdineDTOFull(toUpdate);
	}


	@DeleteMapping("/ordini/{ido}/prodotti/{idp}")
	public OrdineDTOFull removeProdotto(@PathVariable Integer ido, @PathVariable Integer idp) {
		Optional<Ordine> o = oRepo.findById(ido);
		if(o.isEmpty())
			throw new NoSuchElementException("Non ho trovato nessun ordine con quell'id");
		Optional<Prodotto> p = pRepo.findById(idp);
		if(p.isEmpty())
			throw new NoSuchElementException("Non ho trovato nessun ordine con quell'id");

		if(o.get().isSpedito())
			throw new ForbiddenOperationException("Non è possibile modificare un ordine già spedito");

		Ordine toUpdate = o.get();
		Prodotto toRemove = p.get();

		if(!toUpdate.getProdottiInOrdine().contains(toRemove))
			throw new ForbiddenOperationException("Il prodotto selezionato non è presente nell'ordine");

		toRemove.getPresenteInOrdini().remove(toUpdate);
		toUpdate.getProdottiInOrdine().remove(toRemove);
		pRepo.save(toRemove);

		return new OrdineDTOFull(toUpdate);
	}


	@PutMapping("/ordini/{id}")
	public void sent(@PathVariable Integer id) {
		Optional<Ordine> o = oRepo.findById(id);
		if(o.isEmpty())
			throw new NoSuchElementException("Non ho trovato nessun ordine con quell'id");
		Ordine toUpdate = o.get();
		toUpdate.setData_acquisto(LocalDateTime.now());
		toUpdate.setSpedito(true);
		oRepo.save(toUpdate);
	}


	@DeleteMapping("/ordini/{id}")
	public void removeOrdine(@PathVariable Integer id) {
		Optional<Ordine> o = oRepo.findById(id);
		if(o.isEmpty())
			throw new NoSuchElementException("Non ho trovato nessun ordine con quell'id");
		Ordine toRemove=o.get();
		if(toRemove.isSpedito())
			throw new ForbiddenOperationException("Non è possibile cancellare un ordine già spedito");
		oRepo.delete(toRemove);
	}

}
