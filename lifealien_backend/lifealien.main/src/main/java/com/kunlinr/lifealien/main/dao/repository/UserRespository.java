package com.kunlinr.lifealien.main.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.kunlinr.lifealien.main.entity.User;

@Repository
public interface UserRespository extends JpaRepository<User, Integer>{
	
	@SuppressWarnings("unchecked")
	public User save(User user);
	
	public User findById(Long id);
	
	@Query(value = "SELECT u FROM User u WHERE name=:name")
	public User findName(@Param("name") String name);
}
