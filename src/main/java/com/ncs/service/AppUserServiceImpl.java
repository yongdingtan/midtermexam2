package com.ncs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ncs.model.MySecuredUsers;
import com.ncs.model.MyUserDetails;
import com.ncs.repository.AppUsersRepository;

@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private AppUsersRepository userRepo;

	@Override // from UserDetailsService
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		MySecuredUsers user = userRepo.getUsersByUsername(username);
		System.out.println(" ");
		System.out.println("--------Inside App User Service IMP ---------- ");
		System.out.println(" Arg :- " + username);
		System.out.println(" From Database " + user);

		return new MyUserDetails(user);

	}

	@Override
	public MySecuredUsers saveUsers(MySecuredUsers appUsers) {
		return userRepo.save(appUsers);
	}

	@Override
	public MySecuredUsers getUsersByUsername(String username) {
		return userRepo.getUsersByUsername(username);
	}

	@Override
	public MySecuredUsers getUserById(int id) {
		// TODO Auto-generated method stub
		return userRepo.getReferenceById(id);
	}

	@Override
	public MySecuredUsers editPincode(MySecuredUsers user, long pincode) {
		user.setPincode(pincode);
		userRepo.save(user);

		return user;
	}

	@Override
	public List<MySecuredUsers> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public List<MySecuredUsers> getUsersByPincode(long pincode) {
		return userRepo.getUsersByPincode(pincode);
	}
}
