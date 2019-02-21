package it.objectmethod.smistatore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.objectmethod.smistatore.model.SoggettoCommerciale;


@Repository
public interface SoggettoCommercialeRepository extends JpaRepository<SoggettoCommerciale, Integer>{
	
	@Query("select s from SoggettoCommerciale s where s.idCodice = :partitaIvaSC")
	SoggettoCommerciale findOneBySearchedVatNumber(@Param("partitaIvaSC") String partitaIvaSC);
	
	@Query("select s from SoggettoCommerciale s where s.codiceFiscale = :codiceFiscaleSC")
	SoggettoCommerciale findOneBySearchedFiscalCode(@Param("codiceFiscaleSC") String codiceFiscaleSC);
	
	SoggettoCommerciale findByDenominazione(String name);
	
	@Query("select s from SoggettoCommerciale s where s.denominazione like CONCAT('%', :searchedName, '%')")
	List<SoggettoCommerciale> findBySearchedName(@Param("searchedName") String searchedName);


}
