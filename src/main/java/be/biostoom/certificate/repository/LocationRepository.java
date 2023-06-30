package be.biostoom.certificate.repository;

import be.biostoom.certificate.model.Location;
import be.biostoom.certificate.model.dto.ListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query("select new be.biostoom.certificate.model.dto.ListItem(l.id, l.name, '')from Location l")
    List<ListItem> findAllLocations();
}
