package be.biostoom.certificate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.biostoom.certificate.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	Company findByEmailIgnoreCase(String email);

}
