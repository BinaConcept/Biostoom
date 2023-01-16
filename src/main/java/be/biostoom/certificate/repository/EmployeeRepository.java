package be.biostoom.certificate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import be.biostoom.certificate.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByEmailIgnoreCase(String email);

	@Query("from Employee e where e.company_id = ?1 order by e.firstName")
	List<Employee> finAllByCompanyId(Long id);
}
