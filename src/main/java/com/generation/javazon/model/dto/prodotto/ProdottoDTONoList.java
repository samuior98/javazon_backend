package com.generation.javazon.model.dto.prodotto;

import com.generation.javazon.model.entity.Prodotto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdottoDTONoList
{
	protected int id;
	protected boolean attivo;
	protected String nome,tipologia,descrizione,url_foto;
	protected Double prezzo;

	public ProdottoDTONoList(Prodotto p)
	{
		this.id=p.getId();
		this.nome = p.getNome();
		this.tipologia = p.getTipologia();
		this.attivo = p.isAttivo();
		this.descrizione = p.getDescrizione();
		this.prezzo  = p.getPrezzo();
		this.url_foto = p.getUrl_foto();
	}

	public Prodotto convertToProdotto()
	{
		Prodotto p = new Prodotto();
		p.setAttivo(attivo);
		p.setDescrizione(descrizione);
		p.setId(id);
		p.setPrezzo(prezzo);
		p.setUrl_foto(url_foto);
		p.setTipologia(tipologia);
		p.setNome(nome);
		return p;
	}
}
