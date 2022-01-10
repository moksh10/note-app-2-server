package com.project.noteappmain.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.noteappmain.dto.AuthenticationRequest;
import com.project.noteappmain.dto.SuccessMessage;
import com.project.noteappmain.handlers.AuthorizationException;
import com.project.noteappmain.services.UserDetailsService;
import com.project.noteappmain.util.JwtUtil;

@RestController
@CrossOrigin(origins = "https://note-dusk.web.app/",allowCredentials = "true")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	

	@PostMapping("/auth")
	public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response ) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect email or password");
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		Cookie cookie = new Cookie("jwt", jwt);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setMaxAge(60*60*6);
		response.addCookie(cookie);
		return ResponseEntity.ok("Success");
	}
	
	@RequestMapping("/authFailure")
	public void authFailure() {

		throw new AuthorizationException();
	}
	
	@GetMapping("/isAuth")
	public ResponseEntity<?> isAuth() {

		return ResponseEntity.ok(new SuccessMessage("Authorized", true));
	}
	
	@PostMapping("/logoutUser")
	public ResponseEntity<?> logout(HttpServletResponse response) {

		Cookie cookie = new Cookie("jwt", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return ResponseEntity.ok(new SuccessMessage("Logged Out", true));
	}
}
