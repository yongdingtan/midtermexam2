package com.ncs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ncs.model.MySecuredUsers;

@Repository
public interface AppUsersRepository extends JpaRepository<MySecuredUsers, Integer> {
	@Query("from MySecuredUsers where username = :username")
	public MySecuredUsers getUsersByUsername(String username);

	@Query("from MySecuredUsers where pincode = :pincode")
	public List<MySecuredUsers> getUsersByPincode(long pincode);
}
