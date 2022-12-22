package be.biostoom.certificate.model.dto;

import be.biostoom.certificate.enumerated.PermitStatus;
import be.biostoom.certificate.model.*;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

@Data
public class PermitOverviewDTO implements Serializable {

    private long id;

    private PermitStatus status;

    private String company;

    List<Date> startDates = new ArrayList<>();
    List<Date> endDates = new ArrayList<>();

    public PermitOverviewDTO(Permit permit) {
        this.id = permit.getId();
        this.status = permit.getStatus();
        this.company = permit.getCompany().getName();
        this.perpareDates(permit);

    }

    private void perpareDates(Permit permit) {
        permit.getStartPermits().forEach(per->{
            this.startDates.add(per.getStartDate());
        });

        permit.getStopPermits().forEach(per->{
            this.endDates.add(per.getEndDate());
        });
    }


}
