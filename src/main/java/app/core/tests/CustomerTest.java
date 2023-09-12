//package app.core.tests;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import app.core.client.ClientType;
//import app.core.client.LoginManager;
//import app.core.entityBeans.Category;
//import app.core.entityBeans.Coupon;
//import app.core.exceptions.CouponSystemException;
//import app.core.facades.CustomerFacade;
//import app.core.repositories.CouponRepository;
//
//@Component
//@Order(3)
//public class CustomerTest implements CommandLineRunner {
//
//	private CustomerFacade customerFacade = null;
//
//	@Autowired
//	private CouponRepository couponRepository;
//
//	@Autowired
//	private LoginManager loginManager;
//
//	@Override
//	public void run(String... args) throws Exception {
//		customerLoginTest();
//		parchaseCouponTest();
//		getCustomerCouponsTest();
//		getCustomerCouponsByCategoryTest();
//		getCustomerCouponsByPriceTest();
//		getCustomerDetailsTest();
//
//	}
//
//	public void customerLoginTest() {
//		System.out.println("Customer login test:");
//		try {
//			System.out.println("Customer login test is running");
//			customerFacade = (CustomerFacade) loginManager.login("moshe@gmail.com", "1212", ClientType.CUSTOMER);
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void parchaseCouponTest() {
//		System.out.println("Parchase coupon testL:");
//		Coupon coupon = couponRepository.findById(1).get();
//		try {
//			System.out.println("Parchase coupon test is running");
//			customerFacade.parchaseCoupon(1, coupon);
//		} catch (CouponSystemException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void getCustomerCouponsTest() {
//		System.out.println("Get customer coupons test:");
//		try {
//			System.out.println("Get customer coupons test is running");
//			for (Coupon coupon : customerFacade.getCustomerCoupons(1)) {
//				System.out.println(coupon);
//			}
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getCustomerCouponsByCategoryTest() {
//		System.out.println("Get customer coupons by category test:");
//		try {
//			System.out.println("Get customer coupons by category test is running");
//			for (Coupon coupon : customerFacade.getCustomerCouponsByCategory(1, Category.FOOD)) {
//				System.out.println(coupon);
//			}
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getCustomerCouponsByPriceTest() {
//		System.out.println("Get customer coupons by price test:");
//		try {
//			System.out.println("Get customer coupons by price test is running");
//			for (Coupon coupon : customerFacade.getCustomerCouponsByPrice(1, 30)) {
//				System.out.println(coupon);
//			}
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getCustomerDetailsTest() {
//		System.out.println("Get customer details test:");
//		try {
//			System.out.println("Get customer details test is running");
//			System.out.println(customerFacade.getCustomerDetails(1));
//		} catch (CouponSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//}
