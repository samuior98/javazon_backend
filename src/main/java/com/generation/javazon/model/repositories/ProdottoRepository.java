package com.generation.javazon.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.generation.javazon.model.entity.Prodotto;

public interface ProdottoRepository extends JpaRepository<Prodotto, Integer>
{
	    @Query(nativeQuery = true, value = "SELECT prodotto.id AS id, prodotto.nome, prodotto.tipologia, prodotto.descrizione, prodotto.url_foto, prodotto.prezzo, prodotto.attivo, COUNT(prodotto_to_ordine.id_prodotto) AS quantita FROM prodotto JOIN prodotto_to_ordine ON prodotto.id = prodotto_to_ordine.id_prodotto WHERE prodotto_to_ordine.id_ordine = :id_ordine GROUP BY prodotto.id")
	    List<Object[]> getProdottiConQuantita(@Param("id_ordine") Integer id_ordine);

}


