package app.core.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.exceptions.CouponSystemException;
import app.core.facades.CompanyFacade;

@RestController
@RequestMapping("api/company/")
@CrossOrigin(origins = "*")
public class CompanyController {

	@Autowired
	private CompanyFacade companyFacade;

	@PostMapping("coupon")
	@ResponseStatus(HttpStatus.CREATED)
	public void addCoupn(@RequestHeader("Authorization") UUID token, @RequestBody Coupon coupon)
			throws CouponSystemException {

		companyFacade.addCoupon(token, coupon);

	}

	@PutMapping("coupon")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateCoupon(@RequestHeader("Authorization") UUID token, @RequestBody Coupon coupon)
			throws CouponSystemException {

		companyFacade.updateCoupon(token, coupon);

	}

	@DeleteMapping("coupon/{couponId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCoupon(@RequestHeader("Authorization") UUID token, @PathVariable int couponId)
			throws CouponSystemException {
		companyFacade.deleteCoupon(token, couponId);
	}

	@GetMapping("coupons")
	public List<Coupon> getCompanyCoupons(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
		return companyFacade.getCompanyCoupons(token);
	}

	@GetMapping("coupons/category")
	public List<Coupon> getCompanyCoupons(@RequestHeader("Authorization") UUID token, @RequestParam Category category)
			throws CouponSystemException {
		return companyFacade.getCompanyCouponsByCategory(token, category);
	}

	@GetMapping("coupons/{maxPrice}")
	public List<Coupon> getCompanyCoupons(@RequestHeader("Authorization") UUID token, @PathVariable double maxPrice)
			throws CouponSystemException {
		return companyFacade.getCompanyCouponsByPrice(token, maxPrice);
	}

	@GetMapping("details")
	public Company getCompanyDetails(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
		return companyFacade.getCompanyDetails(token);

	}
}
