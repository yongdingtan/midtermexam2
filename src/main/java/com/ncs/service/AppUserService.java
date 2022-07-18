package com.ncs.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ncs.model.MySecuredUsers;

@Service
public interface AppUserService extends UserDetailsService {

	public MySecuredUsers saveUsers(MySecuredUsers securedUsers);

	public MySecuredUsers getUsersByUsername(String username);

	public MySecuredUsers getUserById(int id);

	public MySecuredUsers editPincode(MySecuredUsers user, long pincode);

	public List<MySecuredUsers> getAllUsers();

	public List<MySecuredUsers> getUsersByPincode(long pincode);
}
