package be.biostoom.certificate.repository;

import be.biostoom.certificate.dao.PagingSpecificationExecutor;
import be.biostoom.certificate.dao.ReferenceDao;
import be.biostoom.certificate.model.Permit;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermitRepository extends PagingAndSortingRepository<Permit, Long>, PagingSpecificationExecutor<Permit, Long>, ReferenceDao<Permit, Long> {
    @EntityGraph(attributePaths = {"startPermits"})
    @Query("from Permit p where p.id = ?1 order by p.id")
    Optional<Permit> findByIdWithAllRelationships(long id);

}
