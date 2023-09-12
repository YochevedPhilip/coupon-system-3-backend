//package app.core.tests;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.management.loading.PrivateClassLoader;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import app.core.client.ClientType;
//import app.core.client.LoginManager;
//import app.core.entityBeans.Category;
//import app.core.entityBeans.Company;
//import app.core.entityBeans.Coupon;
//import app.core.entityBeans.Customer;
//import app.core.exceptions.CouponSystemException;
//import app.core.facades.AdminFacade;
//import app.core.facades.CompanyFacade;
//import app.core.repositories.CompanyRepository;
//import app.core.repositories.CustomerRepository;
//import lombok.Builder;
//
//@Component
//@Order(1)
//public class AdminTest implements CommandLineRunner {
//
//	private AdminFacade adminFacade = null;
//
//	@Autowired
//	private LoginManager loginManager;
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		adminLoginTest();
//		addCompanyTest();
//		updateCompanyTest();
//		deleteCompanyTest();
//		getAllCompaniesTest();
//		getCompanyTest();
//		addCustomerTest();
//		updateCustomerTest();
//		deleteCustomerTest();
//		getAllCustomersTest();
//		getCustomerTest();
//	}
//
//	public void adminLoginTest() {
//		System.out.println("Admin login test:");
//		try {
//			System.out.println("Admin login test is running");
//			adminFacade = (AdminFacade) loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void addCompanyTest() {
//		System.out.println("Add company test:");
//		Company companyToAdd1 = Company.builder().name("Amazon").email("amazon@gmail.com").password("1234").build();
//		Company companyToAdd2 = Company.builder().name("Ebay").email("ebay@gmail.com").password("1212").build();
//		Company companyToAdd3 = Company.builder().name("Facebook").email("facebook@gmail.com").password("4321").build();
//
//		try {
//			System.out.println("Add company test is running");
//			adminFacade.addCompany(companyToAdd1);
//			adminFacade.addCompany(companyToAdd2);
//			adminFacade.addCompany(companyToAdd3);
//
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//
//	}
//
//	public void updateCompanyTest() {
//		System.out.println("Update company test:");
//		Company companyToUpdate1 = Company.builder().id(1).name("Amazon").email("amazon2@gmail.com").password("1234")
//				.build();
//		try {
//			System.out.println("Update company test is running");
//			adminFacade.updateCompany(companyToUpdate1);
//
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void deleteCompanyTest() {
//		System.out.println("Delete company test:");
//		try {
//			System.out.println("Delete company test is running");
//			adminFacade.deleteCompany(2);
//
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getAllCompaniesTest() {
//		System.out.println("Get all companies test:");
//		try {
//			System.out.println("Get all companies test is running");
//			for (Company company : adminFacade.getAllCompanies()) {
//				System.out.println(company);
//			}
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getCompanyTest() {
//		System.out.println("Get one company test:");
//		try {
//			System.out.println("Get one company testis running");
//			System.out.println(adminFacade.getCompany(3));
//
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void addCustomerTest() {
//		System.out.println("Add customer test:");
//		Customer customerToAdd1 = Customer.builder().firstName("Moshe").lastName("Koen").email("moshe@gmail.com")
//				.password("1212").build();
//		Customer customerToAdd2 = Customer.builder().firstName("Yoav").lastName("Levi").email("yoav@gmail.com")
//				.password("1111").build();
//		Customer customerToAdd3 = Customer.builder().firstName("Tal").lastName("Golan").email("tal@gmail.com")
//				.password("9876").build();
//
//		try {
//			System.out.println("Add customer test is running");
//			adminFacade.addCustomer(customerToAdd1);
//			adminFacade.addCustomer(customerToAdd2);
//			adminFacade.addCustomer(customerToAdd3);
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void updateCustomerTest() {
//		System.out.println("Update customer test:");
//		Customer customerToUpdate1 = Customer.builder().id(1).firstName("Moshe").lastName("Maman")
//				.email("moshe@gmail.com").password("1212").build();
//		try {
//			System.out.println("Update customer test is running");
//			adminFacade.updateCustomer(customerToUpdate1);
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void deleteCustomerTest() {
//		System.out.println("Delete customer test:");
//		try {
//			System.out.println("Delete customer test is running");
//			adminFacade.deleteCustomer(3);
//
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getAllCustomersTest() {
//		System.out.println("Get all customers test:");
//		try {
//			System.out.println("Get all customers test is running");
//			for (Customer customer : adminFacade.getAllCustomers()) {
//				System.out.println(customer);
//			}
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getCustomerTest() {
//		System.out.println("Get one customer test:");
//		try {
//			System.out.println("Get one customer test is running");
//			System.out.println(adminFacade.getCustomer(2));
//
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//}
