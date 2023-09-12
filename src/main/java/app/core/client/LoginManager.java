package app.core.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.core.exceptions.CouponSystemException;
import app.core.facades.AdminFacade;
import app.core.facades.ClientFacade;
import app.core.facades.CompanyFacade;
import app.core.facades.CustomerFacade;
import app.core.security.LoginResponse;

@Component
public class LoginManager {

	@Autowired
	private AdminFacade adminFacade;
	@Autowired
	private CompanyFacade companyFacade;
	@Autowired
	private CustomerFacade customerFacade;

	public LoginResponse login(String email, String password, ClientType clientType) throws CouponSystemException {

		switch (clientType) {
		case ADMINISTRATOR: {
			return adminFacade.login(email, password);

		}

		case COMPANY: {
			return companyFacade.login(email, password);
		}

		case CUSTOMER: {
			return customerFacade.login(email, password);
		}
		default:
			throw new CouponSystemException("Invalid client type");
		}
	}
}
