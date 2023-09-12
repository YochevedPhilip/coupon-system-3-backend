package app.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	boolean existsByEmailAndIdNot(String email, int id);

	boolean existsByName(String name);

	boolean existsByEmail(String email);

	boolean existsByEmailAndPassword(String email, String password);

	Optional<Company> findByEmail(String email);
	
	Optional<Company> findByEmailAndPassword(String email, String password);


}
