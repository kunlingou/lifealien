package com.kunlinr.lifealien.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	
	@SuppressWarnings("unchecked")
	User save(User user);
	
	User findById(Long id);
	
	User findByUsername(String username);
}
