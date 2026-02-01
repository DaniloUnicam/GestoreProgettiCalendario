package it.unicam.cs.mpgc.jtime119685.Model.DateUtilities;

import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import jakarta.persistence.*;

@Entity
public record ScheduledActivity (

        @Embedded
        ScheduledDay scheduledDay,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "activity_id")
        IActivity activity,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id

) {}
