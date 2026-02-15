package JTime.Persistence.Repositories;

import JTime.Model.Interfaces.IActivity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ActivityRepository extends GenericRepository<IActivity, Long> {

    @Inject
    public ActivityRepository() {
        super(IActivity.class);
    }
}
