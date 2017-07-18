package com.gst.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gst.domain.Location;


public interface LocationRespository extends CrudRepository<Location, Integer>, PagingAndSortingRepository<Location, Integer>{
	@Query("select u from Location u where Userid = ?")
	List<Location> findByUsername(int userid);
	
	@Query("select u from Location u where Userid = ?")
    List<Location> getLastLocation(int userid, Pageable pageable);
}
