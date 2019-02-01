package it.objectmethod.smistatore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.objectmethod.smistatore.model.ApplicationConfig;

@Repository
public interface ApplicationConfigRepository extends JpaRepository<ApplicationConfig, Integer>{

	@Query("select a.value from ApplicationConfig a where a.key= :searchedKey")
	public String findValueBySearchedKey(@Param("searchedKey") String searchedKey);
}
