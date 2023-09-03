package com.Emp.demo.repository;

import com.Emp.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.firstName = :firstName")
    List<Employee> findByFirstName(@Param("firstName") String firstName);

    @Query("SELECT e FROM Employee e WHERE e.designation = :designation")
    List<Employee> findByDesignation(@Param("designation") String designation);

    @Query("SELECT e FROM Employee e WHERE e.department = :department")
    List<Employee> findByDepartment(@Param("department") String department);

    @Query(value = "SELECT * FROM employees e WHERE e.hire_date >= DATE_SUB(CURDATE(), INTERVAL :months MONTH)", nativeQuery = true)
    List<Employee> findEmployeesHiredWithinDefinedMonths(@Param("months") int months);

    @Query("SELECT e FROM Employee e WHERE e.salary > ?1")
    List<Employee> findEmployeesWithSalaryGreaterThan(Double salary);

}
