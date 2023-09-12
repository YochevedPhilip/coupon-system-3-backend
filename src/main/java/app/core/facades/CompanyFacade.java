package app.core.facades;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import app.core.client.ClientType;
import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.exceptions.CouponSystemException;
import app.core.security.Information;
import app.core.security.LoginResponse;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CompanyFacade extends ClientFacade {

	public LoginResponse login(String email, String password) throws CouponSystemException {

		Company company = companyRepository.findByEmailAndPassword(email, password)
				.orElseThrow(() -> new CouponSystemException("Email or password is wrong"));

		Information information = new Information(company.getId(), email, LocalDateTime.now().plusHours(1),
				ClientType.COMPANY);
		UUID token = tokenManager.addToken(information);

		return new LoginResponse(token, company.getId(), email, company.getName(), ClientType.COMPANY);

	}

	public void addCoupon(UUID token, Coupon coupon) throws CouponSystemException {
		int companyId = tokenManager.getCompanyId(token);
		if (couponRepository.existsByTitleAndCompanyId(coupon.getTitle(), companyId)) {
			throw new CouponSystemException("Add coupon failed - coupon title already exists");
		}
		if (coupon.getAmount() <= 0) {
			throw new CouponSystemException("Add coupon failed - coupon amount is 0");
		}

		if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
			throw new CouponSystemException("Add coupon failed - the coupon date is expired");
		}
		if (coupon.getEndDate().before(coupon.getStartDate())) {
			throw new CouponSystemException("Add coupon failed - the coupon end date is before start date");
		}
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new CouponSystemException("Find company by id failed - not found"));
		coupon.setCompany(company);
		couponRepository.save(coupon);
	}

	public void updateCoupon(UUID token, Coupon coupon) throws CouponSystemException {
		int companyId = tokenManager.getCompanyId(token);
		Coupon couponFromDb = couponRepository.findById(coupon.getId())
				.orElseThrow(() -> new CouponSystemException("Find coupon failed - not exists"));

		if (couponFromDb.getCompany().getId() != companyId) {
			throw new CouponSystemException("Can not update company id");
		}

		if (coupon.getAmount() <= 0) {
			throw new CouponSystemException("Can not update - coupon amount is 0");
		}

		if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
			throw new CouponSystemException("Can not update - coupon date is expired");
		}
		if (coupon.getEndDate().before(coupon.getStartDate())) {
			throw new CouponSystemException("Can not update - coupon end date is before start date");
		}
		couponFromDb.setAmount(coupon.getAmount());
		couponFromDb.setCategory(coupon.getCategory());
		couponFromDb.setDescription(coupon.getDescription());
		couponFromDb.setEndDate(coupon.getEndDate());
		couponFromDb.setImage(coupon.getImage());
		couponFromDb.setPrice(coupon.getPrice());
		couponFromDb.setStartDate(coupon.getStartDate());
		couponFromDb.setTitle(coupon.getTitle());
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new CouponSystemException("Find company by id failed - not found"));
		coupon.setCompany(company);
		couponRepository.saveAndFlush(couponFromDb);
	}

	public void deleteCoupon(UUID token, int couponId) throws CouponSystemException {
		int companyId = tokenManager.getCompanyId(token);
		if (!couponRepository.existsByIdAndCompanyId(couponId, companyId)) {
			throw new CouponSystemException("Coupon to delete not exists");
		}
		couponRepository.deletePurchaseByCouponId(couponId);
		couponRepository.deleteById(couponId);
	}

	public List<Coupon> getCompanyCoupons(UUID token) throws CouponSystemException {
		int companyId = tokenManager.getCompanyId(token);
		return couponRepository.findAllByCompanyId(companyId);
	}

	public List<Coupon> getCompanyCouponsByCategory(UUID token, Category categoty) throws CouponSystemException {
		int companyId = tokenManager.getCompanyId(token);
		return couponRepository.findByCompanyIdAndCategory(companyId, categoty);
	}

	public List<Coupon> getCompanyCouponsByPrice(UUID token, double maxPrice) throws CouponSystemException {
		int companyId = tokenManager.getCompanyId(token);
		return couponRepository.findByCompanyIdAndPrice(companyId, maxPrice);
	}

	public Company getCompanyDetails(UUID token) throws CouponSystemException {
		int companyId = tokenManager.getCompanyId(token);
		Company companyFromDb = companyRepository.findById(companyId)
				.orElseThrow(() -> new CouponSystemException("Find company failed - not exists"));
		return companyFromDb;
	}

}
