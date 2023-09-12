package app.core.facades;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import app.core.client.ClientType;
import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.security.Information;
import app.core.security.LoginResponse;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerFacade extends ClientFacade {

	@Override
	public LoginResponse login(String email, String password) throws CouponSystemException {
		Customer customer = customerRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new CouponSystemException("Email or password is wrong"));

		Information information = new Information(customer.getId(), email, LocalDateTime.now().plusHours(1),
				ClientType.CUSTOMER);
		UUID token = tokenManager.addToken(information);

		return new LoginResponse(token, customer.getId(), email, customer.getFirstName() + " " + customer.getLastName(),
				ClientType.CUSTOMER);
	}

	public void parchaseCoupon(UUID token, Coupon coupon) throws CouponSystemException {
		int customerId = tokenManager.getCustomerId(token);
		Coupon couponFromDb = couponRepository.findById(coupon.getId()).orElseThrow(() -> new CouponSystemException("Parchase coupon failed - coupon not exists"));

		if (couponFromDb.getAmount() <= 0) {
			throw new CouponSystemException("Coupon amount is 0");
		}

		if (couponFromDb.getEndDate().before(Date.valueOf(LocalDate.now()))) {
			throw new CouponSystemException("The coupon date is expired");
		}

		if (couponRepository.isCouponPurchasedBefore(customerId, couponFromDb.getId()) == 1) {
			throw new CouponSystemException("The coupon purchased before");
		}
		couponFromDb.setAmount(couponFromDb.getAmount() - 1);
		couponRepository.saveAndFlush(couponFromDb);
//		System.err.println("customerId: " + customerId);
//		System.err.println("couponId: " + couponFromDb.getId());

		couponRepository.addCouponPurchase(couponFromDb.getId(), customerId);
	}

	public List<Coupon> getCustomerCoupons(UUID token) throws CouponSystemException {
		int customerId = tokenManager.getCustomerId(token);
		return couponRepository.findAllByCustomerId(customerId);
	}

	public List<Coupon> getCustomerCouponsByCategory(UUID token, Category category) throws CouponSystemException {
		int customerId = tokenManager.getCustomerId(token);
		return couponRepository.findAllByCustomerIdAndCategory(customerId, category.name());
	}

	public List<Coupon> getCustomerCouponsByPrice(UUID token, double price) throws CouponSystemException {
		int customerId = tokenManager.getCustomerId(token);
		return couponRepository.findAllByCustomerIdAndPrice(customerId, price);
	}

	public Customer getCustomerDetails(UUID token) throws CouponSystemException {
		int customerId = tokenManager.getCustomerId(token);
		Customer customerFromDb = customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException("Company not exists"));
		return customerFromDb;
	}
	
	public List<Coupon> getAllCoupons() {
		return couponRepository.findAll();
	}

}
