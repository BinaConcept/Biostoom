package be.biostoom.certificate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.biostoom.certificate.model.SiteIntroduction;

@Repository
public interface SiteIntroductionRepository extends JpaRepository<SiteIntroduction, Long> {

}
