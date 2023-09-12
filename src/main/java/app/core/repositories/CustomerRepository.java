package app.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import app.core.entities.Company;
import app.core.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	boolean existsByEmail(String email);

	boolean existsByEmailAndPassword(String email, String password);

	boolean existsByEmailAndIdNot(String email, int id);

	Optional<Customer> findByEmail(String email);
	
	Optional<Customer> findByEmailAndPassword(String email, String password);


	@Transactional
	@Modifying
	@Query(value = "DELETE FROM customers_coupons WHERE customer_id=?", nativeQuery = true)
	void deleteCouponPurchaseByCustomerId(int customerId);

}
