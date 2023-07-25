package com.example.prog4.repository;

import com.example.prog4.entity.EmployeeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
  EmployeeEntity findFirstByOrderByEmployeeNumberDesc();


  @Query(nativeQuery = true, value = "SELECT e.* FROM employee_entity e WHERE e.firstname LIKE CONCAT('%', :firstname, '%')")
  List<EmployeeEntity> findByFirstnameContaining(String firstname);

  @Query(nativeQuery = true, value = "SELECT e.* FROM employee_entity e WHERE e.lastname LIKE CONCAT('%', :lastname), '%'")
  List<EmployeeEntity> findByLastnameContaining(String lastname);

  @Query(nativeQuery = true, value = "SELECT e.* FROM employee_entity e WHERE e.sex LIKE CONCAT('%', :sex)")
  List<EmployeeEntity> findBySexContaining(String sex);

  @Query(nativeQuery = true, value = "SELECT e.* FROM employee_entity e WHERE e.position LIKE CONCAT('%', :position)")
  List<EmployeeEntity> findByPositionContaining(String position);

  @Query(nativeQuery = true, value = "SELECT e.* FROM employee_entity e WHERE e.hire_date LIKE CONCAT('%', :hire_date)")
  List<EmployeeEntity> findByHireDateContaining(String hire_date);

  @Query(nativeQuery = true, value = "SELECT e.* FROM employee_entity e WHERE e.resignation_date LIKE CONCAT('%', :resignation_date)")
  List<EmployeeEntity> findByResignationDateContaining(String resignation_date);

}
