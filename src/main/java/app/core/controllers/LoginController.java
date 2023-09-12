package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.core.client.LoginManager;
import app.core.exceptions.CouponSystemException;
import app.core.security.LoginRequest;
import app.core.security.LoginResponse;


@RestController
@RequestMapping("api/login")
@CrossOrigin(origins = "*")
public  class LoginController {
	
	@Autowired
	private LoginManager loginManager;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public LoginResponse login(@RequestBody LoginRequest loginRequest) throws CouponSystemException {
		return loginManager.login(loginRequest.getEmail(), loginRequest.getPassword(), loginRequest.getClientType());
	}

}
