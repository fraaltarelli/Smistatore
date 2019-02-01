package it.objectmethod.smistatore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.objectmethod.smistatore.model.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	@Query("select c from Cliente c where c.partitaIva = :partitaIvaCC")
	Cliente findOneBySearchedVatNumber(@Param("partitaIvaCC") String partitaIvaCC);
	
	@Query("select c from Cliente c where c.codiceFiscale = :codiceFiscaleCC")
	Cliente findOneBySearchedFiscalCode(@Param("codiceFiscaleCC") String codiceFiscaleCC);

}
