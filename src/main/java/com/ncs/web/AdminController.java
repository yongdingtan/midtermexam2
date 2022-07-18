package com.ncs.web;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.model.MySecuredUsers;
import com.ncs.service.AppUserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AppUserService appUserService;

	public AdminController() {

	}

	// Get all users
	@GetMapping("/users")
	public ResponseEntity<List<MySecuredUsers>> getAllUsers() {
		List<MySecuredUsers> allUsers = appUserService.getAllUsers();
		if (allUsers.isEmpty())
			throw new NoResultException("No users found");
		else {
			return new ResponseEntity<List<MySecuredUsers>>(allUsers, HttpStatus.OK);
		}
	}

	// Get user by pincode
	@GetMapping("/users/{pincode}")
	public ResponseEntity<List<MySecuredUsers>> getUserByPincode(@PathVariable long pincode) {
		List<MySecuredUsers> allUsersPincode = appUserService.getUsersByPincode(pincode);
		if (allUsersPincode.isEmpty())
			throw new NoResultException("No users found");
		else {
			return new ResponseEntity<List<MySecuredUsers>>(allUsersPincode, HttpStatus.OK);
		}
	}

}
