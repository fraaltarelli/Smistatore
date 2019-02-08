package it.objectmethod.smistatore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.objectmethod.smistatore.model.Fattura;


@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Integer>{
	
	@Query("select f from Fattura f where f.stato= :status")
	public List<Fattura> findBySearchedStatus(@Param("status") Enum status);

	public Fattura findBynomeFile(String name);
	
	public List<Fattura> findByidCliente(Integer idCliente);
}
