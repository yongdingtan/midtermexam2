package com.ncs.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.dto.AppUserRequestDTO;
import com.ncs.dto.JWTResponseDTO;
import com.ncs.exception.InvalidPincodeException;
import com.ncs.model.MySecuredUsers;
import com.ncs.service.AppUserServiceImpl;
import com.ncs.util.JWTUtil;

@RestController
@RequestMapping("/public")
public class PublicController {

	@Autowired
	private AppUserServiceImpl appUserServiceImpl;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	public PublicController() {

	}

	// Register
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody MySecuredUsers u) throws InvalidPincodeException {

		if (String.valueOf(u.getPincode()).matches("[0-9]+") && String.valueOf(u.getPincode()).length() == 8) {

			MySecuredUsers userEmailExists = appUserServiceImpl.getUsersByUsername(u.getUsername());

			if (userEmailExists != null) {
				return new ResponseEntity<>("There is already a user registered with the email provided",
						HttpStatus.OK);
			} else {
				MySecuredUsers user = appUserServiceImpl.saveUsers(u);
				return new ResponseEntity<>("User successfully saved in the database", HttpStatus.OK);
			}
		} else {
			throw new InvalidPincodeException("Pincode is invalid. It should contain only numerics and be 8 digits max",
					String.valueOf(u.getPincode()), 0);
		}
	}

	// Login

	@PostMapping("/login")
	public ResponseEntity<JWTResponseDTO> doLogin(@RequestBody AppUserRequestDTO userEntry) throws Exception {
		System.out.println("----->> inside public/login " + userEntry);

		try {

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userEntry.getUsername(), userEntry.getPassword()));
			System.out.println();

		} catch (Exception e) {
			throw new Exception("Bad credentials ");
		}

		UserDetails userDetails = appUserServiceImpl.loadUserByUsername(userEntry.getUsername());

		String token = jwtUtil.generateToken(userDetails);

		boolean isValid = token != null ? true : false;

		JWTResponseDTO jwtResponseDTO = new JWTResponseDTO(token, userEntry.getUsername(), isValid);

		return new ResponseEntity<JWTResponseDTO>(jwtResponseDTO, HttpStatus.OK);
	}

}
