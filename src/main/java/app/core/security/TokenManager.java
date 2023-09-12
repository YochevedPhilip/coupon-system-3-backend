package app.core.security;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import app.core.client.ClientType;
import app.core.exceptions.CouponSystemException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenManager {

	private final Map<UUID, Information> tokens;

	public UUID addToken(Information information) {
		deleteToken(information.getId());
		UUID token = UUID.randomUUID();
		while (tokens.containsKey(token)) {
			token = UUID.randomUUID();
		}
		tokens.put(token, information);
		return token;
	}

	private void deleteToken(int id) {
		tokens.entrySet().removeIf(token -> token.getValue().getId() == id);
	}

	private void checkToken(UUID token, ClientType clientType) throws CouponSystemException {
		Information information = tokens.get(token);
		if (information == null) {
			throw new CouponSystemException("Token is not valid");
		}
		if (information.getClientType() != clientType) {
			throw new CouponSystemException("unaothorized action");
		}
	}

	public int getCustomerId(UUID token) throws CouponSystemException {
		return getUserId(token, ClientType.CUSTOMER);
	}

	public int getCompanyId(UUID token) throws CouponSystemException {
		return getUserId(token, ClientType.COMPANY);
	}

	public int getAdminId(UUID token) throws CouponSystemException {
		return getUserId(token, ClientType.ADMINISTRATOR);
	}

	private int getUserId(UUID token, ClientType clientType) throws CouponSystemException {
		checkToken(token, clientType);
		return tokens.get(token).getId();
	}
	
	@Scheduled(timeUnit = TimeUnit.SECONDS, fixedRate = 60)
	public void deleteExpiredToken() {
		tokens.entrySet().removeIf(token -> token.getValue().getExpiration().isBefore(LocalDateTime.now()));
//	    for (Map.Entry<UUID, Information> entry : tokens.entrySet()) {
//	        if (entry.getValue().getExpiration().isBefore(LocalDateTime.now())) {
//	        	tokens.remove(entry.getKey());
//	        }
//	    }
	}
}
