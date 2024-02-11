package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("HooksTest")
public class TestLifeCycleHooks_Test {
	
	@BeforeAll
	public static void beforeAllTest() {
		System.out.println("@BeforeAll Executed");
	}
	
	@AfterAll
	public static void afterAllTest() {
		System.out.println("@AfterAll Executed");
	}

	@BeforeEach
	public void beforeEachTest() {
		System.out.println("@BeforeEach Executed");
	}
	
	@AfterEach
	public void afterEachTest() {
		System.out.println("@AfterEach Executed");
	}
	
	@Test
	public void method1_Test() {
		System.out.println("method1_Test executed");
	}
	
	@Test
	public void method2_Test() {
		System.out.println("method2_Test executed");
	}
	
	@Test
	public void method3_Test() {
		System.out.println("method3_Test executed");
	}
}
