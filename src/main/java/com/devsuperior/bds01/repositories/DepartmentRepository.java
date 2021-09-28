package com.devsuperior.bds01.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.bds01.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
	
	@Query("SELECT obj FROM Department obj ORDER BY name") List<Department> findAll();
}
