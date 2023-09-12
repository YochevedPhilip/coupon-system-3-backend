package app.core.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import app.core.exceptions.CouponSystemException;
import app.core.repositories.CompanyRepository;
import app.core.repositories.CouponRepository;
import app.core.repositories.CustomerRepository;
import app.core.security.LoginResponse;
import app.core.security.TokenManager;
import jakarta.transaction.Transactional;

@Service
@Transactional
@Component
public abstract class ClientFacade {

	@Autowired
	protected CompanyRepository companyRepository;
	@Autowired
	protected CouponRepository couponRepository;
	@Autowired
	protected CustomerRepository customerRepository;
	@Autowired
	protected TokenManager tokenManager;

	public abstract LoginResponse login(String email, String password) throws CouponSystemException;

}
