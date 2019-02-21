package it.objectmethod.smistatore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.objectmethod.smistatore.model.SoggettoCommerciale;
import it.objectmethod.smistatore.model.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer>{
	
	@Query("select u from Utente u where u.username = :username and u.password= :password")
	Utente login(@Param("username") String username, @Param("password") String password);
	
	@Query("select u.soggCommerciale from Utente u where u.id = :id")
	SoggettoCommerciale findSCFromId(@Param("id") Integer id);
	

}
