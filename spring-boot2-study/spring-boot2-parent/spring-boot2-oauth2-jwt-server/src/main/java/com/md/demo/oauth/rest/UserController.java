package com.md.demo.oauth.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

	//http://localhost:9000/users/me?access_token=xxxxxx
	@GetMapping("/me")
	public ResponseEntity<Principal> get(final Principal principal) {
		return ResponseEntity.ok(principal);
	}

	/*
	 * 通过以下接口Post请求获得access_token：
	 * http://localhost:9000/oauth/token?grant_type=password&username=user&password=pass
	 * 
	 * 注意：Authorization中使用BasicAuth，Username和Password分别为：clientId，secret
	 */
}
