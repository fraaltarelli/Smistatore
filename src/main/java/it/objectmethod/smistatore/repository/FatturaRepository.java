package it.objectmethod.smistatore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.smistatore.model.Fattura;


@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Integer>{

}
