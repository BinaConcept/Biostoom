package be.biostoom.certificate.repository;

import be.biostoom.certificate.model.dto.ListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import be.biostoom.certificate.model.Company;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	Company findByEmailIgnoreCase(String email);

    @Query("select new be.biostoom.certificate.model.dto.ListItem(c.id, c.name, c.gsm)from Company c  order by c.name")
    List<ListItem> getCompaniesListItems();
}
