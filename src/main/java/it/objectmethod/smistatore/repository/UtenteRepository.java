package it.objectmethod.smistatore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.objectmethod.smistatore.model.Utente;


public interface UtenteRepository extends JpaRepository<Utente, Integer>{

}
