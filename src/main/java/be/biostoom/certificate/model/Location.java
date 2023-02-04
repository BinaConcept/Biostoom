package be.biostoom.certificate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Location implements Serializable {

    @Id
    @Column(name="location_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company", orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    Set<Permit> permits = new HashSet<>();

    @PrePersist
    private void setOnSave() {
        this.permits.forEach(permit -> {
            permit.setLocation(this);
        });
    }

}
