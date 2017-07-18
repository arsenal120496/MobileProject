package com.gst.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gst.domain.Relationship;

public interface RelationshipRepository extends CrudRepository<Relationship, Integer> {

	@Query("select u from Relationship u where Requestuserid = ?")
	List<Relationship> getListRelation(int requestuserid);
	
	@Query("select u from Relationship u where Requestuserid = ? and Responseuserid = ?")
	Relationship checkRelation(int requestuserid, int responseuserid);

}
