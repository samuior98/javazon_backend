package com.generation.javazon.library;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;


	/**
	 * INPUT: nulla
	 * OUTPUT: lista di stringhe contenente gli errori
	 * @return
	 */
	public abstract List<String> getErrors();

	/**
	 * INPUT: nulla
	 * OUTPUT: true se getErrors().size() == 0 (non ci sono errori),
	 * false altrimenti
	 * @return
	 */
	public boolean isValid() {
		return getErrors().size() == 0;
	}

}