package app.core.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.core.entities.Category;
import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.facades.CustomerFacade;

@RestController
@RequestMapping("api/customer/")
@CrossOrigin(origins = "*")
public class CustomerController {

	@Autowired
	private CustomerFacade customerFacade;

	@PostMapping("parchase")
	@ResponseStatus(HttpStatus.CREATED)
	public void parchaseCoupon(@RequestHeader("Authorization") UUID token, @RequestBody Coupon coupon)
			throws CouponSystemException {

		customerFacade.parchaseCoupon(token, coupon);

	}

	@GetMapping("coupons")
	public List<Coupon> getCustomerCoupons(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
		return customerFacade.getCustomerCoupons(token);
	}

	@GetMapping("coupons/category")
	public List<Coupon> getCustomerCoupons(@RequestHeader("Authorization") UUID token, @RequestParam Category category)
			throws CouponSystemException {
		return customerFacade.getCustomerCouponsByCategory(token, category);
	}

	@GetMapping("coupons/{maxPrice}")
	public List<Coupon> getCustomerCoupons(@RequestHeader("Authorization") UUID token, @PathVariable double maxPrice)
			throws CouponSystemException {
		return customerFacade.getCustomerCouponsByPrice(token, maxPrice);
	}

	@GetMapping("details")
	public Customer getCustomerDetails(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
		return customerFacade.getCustomerDetails(token);

	}
	
	@GetMapping("all-coupons")
	public List<Coupon> getAllCoupon() throws CouponSystemException {
		return customerFacade.getAllCoupons();

	}
	
}
