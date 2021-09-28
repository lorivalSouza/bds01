package com.devsuperior.bds01.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.bds01.dto.DepartmentDTO;
import com.devsuperior.bds01.dto.EmployeeDTO;
import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.entities.Employee;
import com.devsuperior.bds01.repositories.DepartmentRepository;
import com.devsuperior.bds01.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private DepartmentRepository departmentRepository;

	public Page<EmployeeDTO> findAllPaged(Pageable pageable) {
		
		Page<Employee> page = repository.findAll(pageable);

		return page.map(x -> new EmployeeDTO(x));
	}

	public EmployeeDTO insert(EmployeeDTO dto) {
		Employee entity = new Employee();
		copyDtoToEntity(dto, entity);		
		entity = repository.save(entity);
		
		return new EmployeeDTO(entity);
	}

	private void copyDtoToEntity(EmployeeDTO dto, Employee entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		
		DepartmentDTO depDTO = new DepartmentDTO();
		depDTO.setId(dto.getDepartmentId());
		
		Department department = departmentRepository.getOne(depDTO.getId());
		entity.setDepartment(department);
		
	}

}
