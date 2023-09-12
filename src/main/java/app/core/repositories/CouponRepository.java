package app.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import app.core.entities.Category;
import app.core.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	boolean existsByTitleAndCompanyId(String email, int companyId);
	boolean existsByIdAndCompanyId(int id, int companyId);
	
	
	@Query(value = " SELECT case when EXISTS (SELECT * FROM customers_coupons where customer_id = ? and coupons_id = ?) then 1 else 0 end", nativeQuery = true)
	int isCouponPurchasedBefore(int customerId, int couponId);

	@Transactional
	@Modifying
	@Query(value = "insert into customers_coupons values (?, ?)", nativeQuery = true)
	void addCouponPurchase(int couponId, int customerId);

	//driven queries
	List<Coupon> findAllByCompanyId(int companyId);

	List<Coupon> findByCompanyIdAndCategory(int companyId, Category category);

	@Transactional
	@Modifying
	@Query(value = "select * from coupons where company_id=? and price<=?", nativeQuery = true)
	List<Coupon> findByCompanyIdAndPrice(int companyId, double price);

	@Query(value = "select * from `coupons` join `customers_coupons` on coupons.id = customers_coupons.coupons_id where customer_id = ?;", nativeQuery = true)
	List<Coupon> findAllByCustomerId(int customerId);

	@Query(value = "select * from `coupons` join `customers_coupons` on coupons.id = customers_coupons.coupons_id where customer_id = ? and category = ?;", nativeQuery = true)
	List<Coupon> findAllByCustomerIdAndCategory(int customerId, String category);

	@Query(value = "select * from `coupons` join `customers_coupons` on coupons.id = customers_coupons.coupons_id where customer_id = ? and price <= ?;", nativeQuery = true)
	List<Coupon> findAllByCustomerIdAndPrice(int customerId, double price);

	@Transactional
	@Modifying
	@Query(value = "delete from customers_coupons where coupons_id in (select id from coupons where end_date < CURDATE())", nativeQuery = true)
	void deletePurchasedExpiredCoupons();

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM customers_coupons WHERE coupons_id IN (SELECT id FROM coupons WHERE company_id = ?)", nativeQuery = true)
	void deleteCouponPurchaseByCompanyId(int companyId);

	@Transactional
	@Modifying
	@Query(value = "delete from customers_coupons WHERE coupons_id=?", nativeQuery = true)
	void deletePurchaseByCouponId(int couponId);

	@Transactional
	@Modifying
	@Query(value = "delete from coupons where end_date < curdate()", nativeQuery = true)
	void deleteExpiredCoupons();

}
