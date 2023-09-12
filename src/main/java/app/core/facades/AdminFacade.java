package app.core.facades;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import app.core.client.ClientType;
import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.security.Information;
import app.core.security.LoginResponse;
import app.core.security.TokenManager;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminFacade extends ClientFacade {

	@Override
	public LoginResponse login(String email, String password) throws CouponSystemException {
		if (!(email.equalsIgnoreCase("admin@admin.com") && password.equals("admin"))) {
			throw new CouponSystemException("Email or password is wrong");
		}
		Information information = new Information(-1, email, LocalDateTime.now().plusHours(1),
				ClientType.ADMINISTRATOR);
		UUID token = tokenManager.addToken(information);
		return new LoginResponse(token, -1, email, "Admin", ClientType.ADMINISTRATOR);

	}

	public Company addCompany(UUID token, Company company) throws CouponSystemException {
		tokenManager.getAdminId(token);
		if (companyRepository.existsByName(company.getName())) {
			throw new CouponSystemException("Company name already exists");
		}
		if (companyRepository.existsByEmail(company.getEmail())) {
			throw new CouponSystemException("Company email already exists");
		}

		return companyRepository.save(company);
	}

	public Company updateCompany(UUID token, int companyId, Company company) throws CouponSystemException {
		tokenManager.getAdminId(token);
		Company companyFromDb = companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException("Company to update not exists"));

		if (companyFromDb.getId() == 0) {
			throw new CouponSystemException("Company to update not exists");
		}
		if (!companyFromDb.getName().equalsIgnoreCase(company.getName())) {
			throw new CouponSystemException("Can not update company name");
		}
		if (companyRepository.existsByEmailAndIdNot(company.getEmail(), companyId)) {
			throw new CouponSystemException("Can not update email because it already exsits");

		}
		companyFromDb.setEmail(company.getEmail());
		companyFromDb.setPassword(company.getPassword());
		return companyRepository.saveAndFlush(companyFromDb);
	}

	public void deleteCompany(UUID token, int companyId) throws CouponSystemException {
		tokenManager.getAdminId(token);
		if (!companyRepository.existsById(companyId)) {
			throw new CouponSystemException("Company to delete not exists");
		}
		couponRepository.deleteCouponPurchaseByCompanyId(companyId);
		companyRepository.deleteById(companyId);
	}

	public List<Company> getAllCompanies(UUID token) throws CouponSystemException {
		tokenManager.getAdminId(token);
		return companyRepository.findAll();
	}

	public Company getCompany(UUID token, int companyID) throws CouponSystemException {
		tokenManager.getAdminId(token);
		if (!companyRepository.existsById(companyID)) {
			throw new CouponSystemException("Company not exists");
		}
		return companyRepository.findById(companyID).orElseThrow();
	}

	public Customer addCustomer(UUID token, Customer customer) throws CouponSystemException {
		tokenManager.getAdminId(token);
		if (customerRepository.existsByEmail(customer.getEmail())) {
			throw new CouponSystemException("Customer email already exists");
		}
		return customerRepository.save(customer);
	}

	public Customer updateCustomer(UUID token, int custometId, Customer customer) throws CouponSystemException {
		tokenManager.getAdminId(token);
		Customer customerFromDb = customerRepository.findById(custometId)
				.orElseThrow(() -> new CouponSystemException("Customer to update not exists"));
		if (customerRepository.existsByEmailAndIdNot(customer.getEmail(), custometId)) {
			throw new CouponSystemException("Can not update email because it already exsits");
		}

		customerFromDb.setFirstName(customer.getFirstName());
		customerFromDb.setLastName(customer.getLastName());
		customerFromDb.setEmail(customer.getEmail());
		customerFromDb.setPassword(customer.getPassword());
		return customerRepository.saveAndFlush(customerFromDb);
	}

	public void deleteCustomer(UUID token, int customerId) throws CouponSystemException {
		tokenManager.getAdminId(token);
		if (!customerRepository.existsById(customerId)) {
			throw new CouponSystemException("Customer to delete not exists");
		}
		customerRepository.deleteCouponPurchaseByCustomerId(customerId);
		customerRepository.deleteById(customerId);
	}

	public List<Customer> getAllCustomers(UUID token) throws CouponSystemException {
		tokenManager.getAdminId(token);
		return customerRepository.findAll();
	}

	public Customer getCustomer(UUID token, int customerId) throws CouponSystemException {
		tokenManager.getAdminId(token);
		if (!customerRepository.existsById(customerId)) {
			throw new CouponSystemException("Customer not exists");
		}
		return customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException("Get customer failed - not exists"));
	}
}
