package be.biostoom.certificate.repository;

import be.biostoom.certificate.dao.PagingSpecificationExecutor;
import be.biostoom.certificate.dao.ReferenceDao;
import be.biostoom.certificate.model.dto.ListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import be.biostoom.certificate.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository  extends PagingAndSortingRepository<Employee, Long>, PagingSpecificationExecutor<Employee, Long>, ReferenceDao<Employee, Long> {

	Employee findByEmailIgnoreCase(String email);

	@Query("select new be.biostoom.certificate.model.dto.ListItem(e.id, e.firstName)from Employee e where e.company.id = ?1 order by e.firstName")
	List<ListItem> finAllByCompanyId(Long id);
}
