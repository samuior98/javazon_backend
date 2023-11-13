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
import com.generation.javazon.model.dto.cliente.ClienteDTOFull;
import com.generation.javazon.model.dto.cliente.ClienteDTONoList;
import com.generation.javazon.model.dto.cliente.ClienteDTOwCart;
import com.generation.javazon.model.dto.ordine.OrdineDTOFullNoCliente;
import com.generation.javazon.model.entity.Cliente;
import com.generation.javazon.model.entity.Ordine;
import com.generation.javazon.model.repositories.ClienteRepository;
import com.generation.javazon.model.repositories.OrdineRepository;
import com.generation.javazon.model.repositories.ProdottoRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	ClienteRepository cRepo;

	@Autowired
	OrdineRepository oRepo;

	@Autowired
	ProdottoRepository pRepo;


	@GetMapping("/clienti/{username}")
	public ClienteDTOwCart mockLogin(@PathVariable String username) {
		if(cRepo.findByUsername(username) == null)
			throw new NoSuchElementException();
		return new ClienteDTOwCart(cRepo.findByUsername(username));
	}


	@GetMapping("/clienti/{idC}/carrello")
	public OrdineDTOFullNoCliente getCarrello(@PathVariable Integer idCliente) {
		List<Ordine> ordini= oRepo.findAll();
		Ordine carrello= ordini.stream().filter(o ->
				o.getData_acquisto() == null).findFirst().get();
		return new OrdineDTOFullNoCliente(carrello);
	}


	@GetMapping("/clienti/{idC}/carrelloId")
	public int getCarrelloId(@PathVariable Integer idCliente) {
		List<Ordine> ordini= oRepo.findAll();
		Ordine carrello= ordini.stream().filter(o ->
				o.getData_acquisto() == null).findFirst().get();
		return carrello.getId();
	}
	
	
	@GetMapping("/clienti")
	public List<ClienteDTONoList> getAllClienti() {
		List<ClienteDTONoList> clientiDTO = cRepo.findAll()
											.stream()
											.map(cliente -> new ClienteDTONoList(cliente))
											.toList();
		return clientiDTO;
	}


	@GetMapping("/clienti/{id}/full")
	public ClienteDTOFull getOneCliente(@PathVariable Integer id) {
		Optional<Cliente> c= cRepo.findById(id);
		if(c.isEmpty())
			throw new NoSuchElementException("Non ho trovato nessun elemento con quell'id");
		return new ClienteDTOFull(c.get());
	}


	@GetMapping("/clienti/{username}/username")
	public ClienteDTOFull getOneClienteByUSername(@PathVariable String username) {
		Cliente c= cRepo.findByUsername(username);
		if(c==null)
			throw new NoSuchElementException("Non ho trovato alcun utente con quell'username");
		return new ClienteDTOFull(c);
	}


	@PostMapping("/clienti")
	public ClienteDTONoList insert(@RequestBody ClienteDTONoList dto) {
		Cliente cliente= dto.convertToCliente();
		if(!cliente.isValid())
			throw new InvalidEntityException(cliente.getErrors().stream().reduce("Errore: \n", String::concat));

		if(cRepo.findByUsername(cliente.getUsername()) != null)
			throw new InvalidEntityException("Username già in uso");

		//do un ordine vuoto (carrello) al cliente appena creato (registrato)
		cliente= cRepo.save(cliente);
		Ordine carrello= new Ordine();
		carrello.setId_cliente(cliente);
		carrello.setSpedito(false);
		oRepo.save(carrello);
		return new ClienteDTONoList(cliente);
	}


	@PutMapping("/clienti/{id}")
	public ClienteDTONoList update(@PathVariable Integer id, @RequestBody ClienteDTONoList dto) {
		Cliente toUpdate= cRepo.findById(id).get();
		if(toUpdate == null)
			throw new NoSuchElementException("Non ho trovato sul db il cliente che vuoi modificare");

		Cliente	updated= dto.convertToCliente();

		if(cRepo.findByUsername(updated.getUsername()) != null && !updated.getUsername().equals(toUpdate.getUsername()))
			throw new InvalidEntityException("Username già in uso");

		if(!updated.isValid())
			throw new InvalidEntityException(updated.getErrors().stream().reduce("Errore: \n", String::concat));

		List<Ordine> orders= toUpdate.getOrdini();
		updated.setOrdini(orders);
		return new ClienteDTONoList(cRepo.save(updated));
	}


	@DeleteMapping("/clienti/{id}")
	public void delete(@PathVariable Integer id) {
		Optional<Cliente> op= cRepo.findById(id);
		if(op.isEmpty())
			throw new NoSuchElementException("Non ho trovato sul db il cliente che vuoi cancellare");

		Cliente cliente= op.get();
		Cliente strawman= cRepo.findById(1).get();

		strawman.getOrdini().addAll(cliente.getOrdini());
		cRepo.save(strawman);
		cRepo.delete(cliente);
	}

}
