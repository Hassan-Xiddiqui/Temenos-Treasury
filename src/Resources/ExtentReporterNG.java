package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
    public static ExtentReports getReports() {
        String path = System.getProperty("user.dir") + "\\Reports\\index.html" ;
//        String path = "C:\\Users\\xcelliti2\\IdeaProjects\\Retail Ops\\Reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Retail Operation Automation Result");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Automation Engineer","Xcelliti");

        return extent;
    }
}
