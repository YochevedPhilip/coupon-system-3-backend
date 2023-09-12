package app.core.job;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import app.core.repositories.CouponRepository;

@Component
public class CouponExpirationDailyJob {

	@Autowired
	private CouponRepository couponRepository;

	@Scheduled(timeUnit = TimeUnit.SECONDS, fixedRate = 60 * 60 * 24, initialDelay = 60)
	public void task() {

		couponRepository.deletePurchasedExpiredCoupons();
		couponRepository.deleteExpiredCoupons();
	}

}
