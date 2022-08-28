package be.biostoom.certificate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.biostoom.certificate.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
