package it.unicam.cs.mpgc.jtime119685.Repositories;

import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ActivityRepository extends GenericRepository<IActivity, Long> {

    @Inject
    public ActivityRepository() {
        super(IActivity.class);
    }
}
