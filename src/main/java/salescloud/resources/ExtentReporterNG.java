package salescloud.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Sales Cloud Automation Resutls");
		reporter.config().setDocumentTitle("Sales Cloud Test Results");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		String userName = System.getProperty("user.name");
		extent.setSystemInfo("Tester", userName);
		return extent;

	}

}
