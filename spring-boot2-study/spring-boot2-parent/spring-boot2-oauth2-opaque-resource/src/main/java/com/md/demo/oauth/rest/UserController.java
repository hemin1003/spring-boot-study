package com.md.demo.oauth.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/me")
public class UserController {

	//http://localhost:9101/me?access_token=xxxxxx
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Principal> get(final Principal principal) {
        return ResponseEntity.ok(principal);
    }

    /*
	 * 通过以下接口Post请求获得access_token：
	 * http://localhost:9001/oauth/token?grant_type=password&username=user&password=pass
	 * 
	 * 注意：Authorization中使用BasicAuth，Username和Password分别为：clientId，secret
	 */
}
