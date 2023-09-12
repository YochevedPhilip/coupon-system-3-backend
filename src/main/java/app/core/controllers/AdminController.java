package app.core.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.facades.AdminFacade;
import app.core.security.LoginResponse;


@RestController
@RequestMapping("api/admin/")
@CrossOrigin(origins = "*")
public class AdminController {
	
	@Autowired
	private AdminFacade adminFacade;

	
	@PostMapping("company")
	@ResponseStatus(HttpStatus.CREATED)
	public Company addCompany(@RequestHeader("Authorization") UUID token, @RequestBody Company company) throws CouponSystemException {
		return	adminFacade.addCompany(token, company);
	} 
	
	@PutMapping("company/{companyId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Company updateCompany(@RequestHeader("Authorization") UUID token, @PathVariable int companyId, @RequestBody Company company) throws CouponSystemException {
		return	adminFacade.updateCompany(token, companyId, company);
	}
	
	@DeleteMapping("company/{companyId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCompany(@RequestHeader("Authorization") UUID token, @PathVariable int companyId) throws CouponSystemException {
			adminFacade.deleteCompany(token, companyId);
	}
	
	@GetMapping("companies")
	public List<Company> getAllCompanies(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
		return adminFacade.getAllCompanies(token);
	}
	
	@GetMapping("company/{companyId}")
	public Company getOneCompany(@RequestHeader("Authorization") UUID token, @PathVariable int companyId) throws CouponSystemException {
		return adminFacade.getCompany(token, companyId);
	} 
	
	@PostMapping("customer")
	@ResponseStatus(HttpStatus.CREATED)
	public void addCustomer(@RequestHeader("Authorization") UUID token,  @RequestBody Customer customer) throws CouponSystemException {
	
			adminFacade.addCustomer(token, customer);
		
	} 
	
	@PutMapping("customer/{customerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Customer updateCustomer(@RequestHeader("Authorization") UUID token, @PathVariable int customerId, @RequestBody Customer customer) throws CouponSystemException {
		
		return	adminFacade.updateCustomer(token, customerId, customer);
		
	}
	
	@DeleteMapping("customer/{customerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@RequestHeader("Authorization") UUID token, @PathVariable int customerId) throws CouponSystemException {
			adminFacade.deleteCustomer(token, customerId);
	}
		
	
	@GetMapping("customers")
	public List<Customer> getAllCustomers(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
		return adminFacade.getAllCustomers(token);
	}
	
	@GetMapping("customer/{customerId}")
	public Customer getOneCustomer(@RequestHeader("Authorization") UUID token, @PathVariable int customerId) throws CouponSystemException {
		return adminFacade.getCustomer(token, customerId);
	}
	

}
