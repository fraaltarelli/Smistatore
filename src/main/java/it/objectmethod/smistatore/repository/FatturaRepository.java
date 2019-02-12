package it.objectmethod.smistatore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.objectmethod.smistatore.model.Cliente;
import it.objectmethod.smistatore.model.Fattura;


@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Integer>{
	
	@Query("select f from Fattura f where f.stato= :status")
	public List<Fattura> findByStatoFattura(@Param("status") Enum status);
	
	@Query("select f from Fattura f where f.stato= :status AND f.cliente = :cliente")
	public List<Fattura> findByStatoFatturaCliente(@Param("status") Enum status, @Param("cliente") Cliente cliente);

	public Fattura findBynomeFile(String name);

	@Query("select f from Fattura f where f.cliente = :cliente")
	public List<Fattura> findBycliente(@Param("cliente") Cliente cliente);
}
