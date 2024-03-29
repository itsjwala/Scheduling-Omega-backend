package com.wissen.SmartInterviewProcess.jwt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class JwtAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserDetailsService userDetailsService;

	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		System.out.println(userDetails);
		final String token = jwtTokenUtil.generateToken(userDetails);
//		final String token = "hwere";
		return ResponseEntity.ok(new JwtResponse(token));
	}

	public JwtResponse createAuthenticationTokenForRegister(JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		System.out.println(userDetails);
		final String token = jwtTokenUtil.generateToken(userDetails);
//		final String token = "hwere";
		return new JwtResponse(token);
	}
	
	
	private void authenticate(String username, String password) throws Exception {
		try {
//			System.out.println("BEFORE");
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
//			System.out.println("DISABLED");
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
//			System.out.println("INVALID");
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
