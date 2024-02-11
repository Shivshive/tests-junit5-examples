package runners;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;
import static org.junit.platform.launcher.EngineFilter.includeEngines;
import static org.junit.platform.launcher.TagFilter.includeTags;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.LauncherSession;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

public class CustomJunitSuiteRunner {

	public void runTests() {

		LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request().selectors(selectPackage("tests"))
				.filters(includeEngines("junit-jupiter"),
						// excludeEngines("junit-vintage"),
						includeTags("enum_source")
				// excludeTags("slow"),
//		        includeClassNamePatterns(".*Test[s]?")
				// includeClassNamePatterns("org\.example\.tests.*")
				)
//		     .configurationParameter("key1", "value1")
//		     .configurationParameters(configParameterMap)
				.build();

		SummaryGeneratingListener summaryGeneratingListener = new SummaryGeneratingListener();

		LauncherFactory.create().execute(request, summaryGeneratingListener);

		System.out.println("Test Succecceded Count " + summaryGeneratingListener.getSummary().getTestsSucceededCount());
		System.out.println("Test Failed Count " + summaryGeneratingListener.getSummary().getTotalFailureCount());
		System.out.println("Test Skipped Count " + summaryGeneratingListener.getSummary().getTestsSkippedCount());

		summaryGeneratingListener.getSummary().getFailures().forEach(failure -> {
			System.out.println("Test Display Name => " + failure.getTestIdentifier().getDisplayName());

			System.out.println("Test Failure Reason => " + failure.getException().getMessage());
		});

	}

	public void runTestsViaLauncherDiscoveryTestPlan() {

		LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
				.selectors(selectPackage("tests"))
				.filters(includeTags("HooksTest"))
				.build();
		
		SummaryGeneratingListener summaryGeneratingListener = new SummaryGeneratingListener();

		try (LauncherSession session = LauncherFactory.openSession()) {

			Launcher launcher = session.getLauncher();
			TestPlan testPlan = launcher.discover(request);
			launcher.execute(testPlan, new TestExecutionListener[] { summaryGeneratingListener });
		}
		
		
		TestExecutionSummary summary = summaryGeneratingListener.getSummary();
		
		printDataToTable(Map.of("total found",summary.getTestsFoundCount(),
				"total passed",summary.getTestsSucceededCount(),
				"total failure",summary.getTotalFailureCount(),
				"total skiopped",summary.getTestsSkippedCount(),
				"total aborted",summary.getTestsAbortedCount(),
				"total time taken", 
				Instant.ofEpochMilli(summary.getTimeStarted())
				.until(Instant.ofEpochMilli(summary.getTimeFinished()), ChronoUnit.SECONDS)));

	}

	public static void main(String[] args) {
		new CustomJunitSuiteRunner().runTestsViaLauncherDiscoveryTestPlan();
	}
	
	public static void printDataToTable(Map<String, Long> data) {
			
		System.out.println("-----------------------------------------------");
		System.out.printf("|%-20s|%-20s|%n","Total Summary","Total Count");
		System.out.println("-----------------------------------------------");
		data.forEach((k,v)->{
			System.out.printf("|%-20s|%20d|%n", k,  v.longValue());
		});
		System.out.println("-----------------------------------------------");
		
		
		
		
	}

}
