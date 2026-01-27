package Services;

import Entities.Interfaces.IActivity;
import Entities.Interfaces.IProject;
import Entities.Interfaces.IReport;

import java.util.List;

public class ReportService {
    private IReport report;

    /** Constructor of the HandlerReport class.
     * @param report the report to be handled
     */
    public ReportService(IReport report) {
        this.report = report;
    }

    /** Gets the report being handled.
     * @return the report
     */
    public List<? extends IActivity> getActivities() {
        return report.getListOfActivities();
    }

    /** Gets the list of projects in the report.
     * @return the list of projects
     */
    public List<IProject<? extends IActivity>> getListOfProjects(){
        return this.report.getListOfProjects();
    }
}
