package listners;

import java.util.logging.Logger;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

@TestInstance(Lifecycle.PER_CLASS)
public class Hooks implements BeforeAllCallback, BeforeEachCallback, BeforeTestExecutionCallback,
		AfterTestExecutionCallback, AfterEachCallback, AfterAllCallback {

	static final Logger LOG = Logger.getLogger(Hooks.class.getName());

	public void afterAll(ExtensionContext context) throws Exception {
		LOG.info("afterAll Callback is called....");
	}

	public void afterEach(ExtensionContext context) throws Exception {
		LOG.info("afterEach Callback is called....");

	}

	public void afterTestExecution(ExtensionContext context) throws Exception {
		LOG.info("afterTestExecution Callback is called....");

	}

	public void beforeTestExecution(ExtensionContext context) throws Exception {
		LOG.info("beforeTestExecution Callback is called....");

	}

	public void beforeEach(ExtensionContext context) throws Exception {
		LOG.info("beforeEach Callback is called....");
	}

	public void beforeAll(ExtensionContext context) throws Exception {
		LOG.info("beforeAll Callback is called....");
	}

}
