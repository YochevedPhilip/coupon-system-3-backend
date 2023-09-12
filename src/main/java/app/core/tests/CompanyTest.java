//package app.core.tests;
//
//import java.sql.Date;
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
//import app.core.exceptions.CouponSystemException;
//import app.core.facades.CompanyFacade;
//
//@Component
//@Order(2)
//public class CompanyTest implements CommandLineRunner {
//
//	private CompanyFacade companyFacade = null;
//
//	@Autowired
//	private LoginManager loginManager;
//
//	@Override
//	public void run(String... args) throws Exception {
//		companyLoginTest();
//		addCouponTest();
//		updateCouponTest();
//		deleteCouponTest();
//		getCompanyCouponsTest();
//		getCompanyCouponsByCategoryTest();
//		getCompanyCouponsByPriceTest();
//		getCompanyDetailsTest();
//	}
//
//	public void companyLoginTest() {
//		System.out.println("Company login test:");
//		try {
//			System.out.println("Company login test is running");
//			companyFacade = (CompanyFacade) loginManager.login("amazon2@gmail.com", "1234", ClientType.COMPANY);
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void addCouponTest() {
//		Company company = Company.builder().id(1).name("Amazon").email("amazon2@gmail.com").password("1234").build();
//
//		System.out.println("Add coupon test:");
//		Coupon couponToAdd1 = Coupon.builder().company(company).category(Category.FOOD).title("10% discount")
//				.description("10% discount for purchases").amount(100).price(17.5).startDate(Date.valueOf("2023-01-01"))
//				.endDate(Date.valueOf("2024-01-28")).image("image1").build();
//
//		Coupon couponToAdd2 = Coupon.builder().company(company).category(Category.FOOD).title("20% discount")
//				.description("0% discount for purchases").amount(60).price(30).startDate(Date.valueOf("2023-01-01"))
//				.endDate(Date.valueOf("2024-02-28")).image("image2").build();
//
//		Coupon couponToAdd3 = Coupon.builder().company(company).category(Category.ELECTRICITY).title("1+1")
//				.description("The second product is free").amount(30).price(49.9).startDate(Date.valueOf("2023-02-01"))
//				.endDate(Date.valueOf("2023-03-27")).image("image3").build();
//
//		Coupon couponToAdd4 = Coupon.builder().company(company).category(Category.ELECTRICITY).title("Expired coupob")
//				.description("The second product is free").amount(30).price(49.9).startDate(Date.valueOf("2023-02-01"))
//				.endDate(Date.valueOf("2023-03-27")).image("image3").build();
//
//		try {
//			System.out.println("Add coupon test is running");
//			companyFacade.addCoupon(1, couponToAdd1);
//			companyFacade.addCoupon(1, couponToAdd2);
//			companyFacade.addCoupon(1, couponToAdd3);
//			companyFacade.addCoupon(1, couponToAdd4);
//
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void updateCouponTest() {
//		System.out.println("Update coupon test:");
//		Coupon couponToUpdate = Coupon.builder().id(1).category(Category.FOOD).title("10% discount")
//				.description("10% discount for purchases").amount(100).price(15).startDate(Date.valueOf("2023-01-01"))
//				.endDate(Date.valueOf("2024-01-28")).image("image1").build();
//		try {
//			System.out.println("Update coupon test is running");
//			companyFacade.updateCoupon(1, couponToUpdate);
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void deleteCouponTest() {
//		System.out.println("Delete coupon test:");
//		try {
//			System.out.println("Delete coupon test is running");
//			companyFacade.deleteCoupon(1, 3);
//
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getCompanyCouponsTest() {
//		System.out.println("Get company coupons test:");
//		try {
//			System.out.println("Get company coupons test is running");
//			for (Coupon coupon : companyFacade.getCompanyCoupons(1)) {
//				System.out.println(coupon);
//			}
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getCompanyCouponsByCategoryTest() {
//		System.out.println("Get company coupons by category test:");
//		try {
//			System.out.println("Get company coupons by category test is running");
//			for (Coupon coupon : companyFacade.getCompanyCouponsByCategory(1, Category.FOOD)) {
//				System.out.println(coupon);
//			}
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getCompanyCouponsByPriceTest() {
//		System.out.println("Get company coupons by price test:");
//		try {
//			System.out.println("Get company coupons by price test is running");
//			for (Coupon coupon : companyFacade.getCompanyCouponsByPrice(1, 30)) {
//				System.out.println(coupon);
//			}
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getCompanyDetailsTest() {
//		System.out.println("Get company details test:");
//		try {
//			System.out.println("Get company details test is running");
//			System.out.println(companyFacade.getCompanyDetails(1));
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//}
