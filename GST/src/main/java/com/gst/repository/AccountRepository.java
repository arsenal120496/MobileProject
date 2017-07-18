package com.gst.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gst.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Integer>{
	
	@Query("select u from Account u where Username = ? and Password = ?")
    Account login(String username, String password);
	
	@Query("select u from Account u where Username = ?")
    Account checkExistAccount(String username);
	
	@Query("select u from Account u where u.Email like '%' || :email || '%'  and u.Username <> :username")
    List<Account> findListAccountByWord(@Param("email")String email, @Param("username")String username);
	
	
	@Query("select u from Account u where Id = ?")
    Account checkExistAccountById(int userid);
}

