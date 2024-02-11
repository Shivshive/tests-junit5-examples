package listners;

import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class TestException_ListnerTest implements TestExecutionExceptionHandler {
	
	static final Logger logger = Logger.getLogger(TestException_ListnerTest.class.getName());

	@Override
	public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
		logger.info(()-> String.format(
				"~~~~~~~ xxx [Exception occured -> %s ; error occured -> %s ] xxx ~~~~~~~~", 
				context.getTestMethod().get().getName(), 
				throwable.getMessage()));
		// Have to explicitly fail it... 
		Assertions.fail();
	}

}
