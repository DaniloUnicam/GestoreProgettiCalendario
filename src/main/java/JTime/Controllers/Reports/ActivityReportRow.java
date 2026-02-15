package JTime.Controllers.Reports;

/**
 * DTO for report table rows. Kept simple and immutable.
 */
public record ActivityReportRow(String name, int duration, String projectName, String status) {
}

