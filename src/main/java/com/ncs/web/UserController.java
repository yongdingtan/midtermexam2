package com.ncs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.exception.InvalidPincodeException;
import com.ncs.exception.ResourceNotFoundException;
import com.ncs.model.MySecuredUsers;
import com.ncs.service.AppUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private AppUserService appUserService;

	public UserController() {

	}

	// View profile

	@GetMapping("/viewprofile")
	public ResponseEntity<MySecuredUsers> viewProfile(@RequestParam int id) {
		MySecuredUsers user = appUserService.getUserById(id);
		if (user == null) {
			throw new ResourceNotFoundException("User with ID: " + id + " not found", null);
		} else
			return new ResponseEntity<MySecuredUsers>(user, HttpStatus.OK);
	}

	// Update Pincode

	@PutMapping("/updatepincode")
	public ResponseEntity<MySecuredUsers> updatePincode(@RequestParam int id, @RequestBody MySecuredUsers userBody)
			throws InvalidPincodeException {

		if (String.valueOf(userBody.getPincode()).matches("[0-9]+")
				&& String.valueOf(userBody.getPincode()).length() == 8) {
			MySecuredUsers user = appUserService.getUserById(id);
			MySecuredUsers responseUser = appUserService.editPincode(user, userBody.getPincode());

			return new ResponseEntity<MySecuredUsers>(responseUser, HttpStatus.OK);
		} else {
			throw new InvalidPincodeException("Pincode is invalid. It should contain only numerics and be 8 digits max",
					String.valueOf(userBody.getPincode()), 0);
		}
	}

}
