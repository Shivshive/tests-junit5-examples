package runners;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Junit Suite Runner")
@SelectPackages("tests")
public class JunitSuiteRunner {

}
