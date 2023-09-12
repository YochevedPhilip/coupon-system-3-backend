package app.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CouponSystemPhase3Application {

	public static void main(String[] args) {
		SpringApplication.run(CouponSystemPhase3Application.class, args);
		System.out.println("Coupon System is running");
	}

}
