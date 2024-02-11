package tests;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import data.ArgumentConverter_Test;
import data.ArgumentProviderTest;
import data.CustomAggregator_WithEnumSource;
import data.CustomArgumentAggregator;
import data.NumberData;
import data.Number_Test;
import data.numbers_;
import listners.Hooks;
import listners.TestException_ListnerTest;
import utility.CheckNumbers;

@Tag("parametrized_tests")
@ExtendWith({Hooks.class,TestException_ListnerTest.class})
public class ParametrizedTest {
	

	@DisplayName("Eaxmple of valueSource Annotation")
	@ParameterizedTest
	@Tag("value_source")
	//@Disabled
	@ValueSource(ints = { 1, 2, 3, 4, 5, 6 }) // simple taking values from valueSource Example
	void checkNumber_test(int num) throws InterruptedException {
		CheckNumbers checkNum = new CheckNumbers();
		Assertions.assertTrue(checkNum.checkNumber(num), String.format("%s is not number", num));
	}

	@DisplayName("Eaxmple of EnumSource Annotation")
	@ParameterizedTest
	@Tag("enum_source")
	@EnumSource(numbers_.class)  // Taking values from ENUM via ENUM Source
	//@Disabled
	void checkNumber_enumSource_test(numbers_ num) {
		CheckNumbers checkNum = new CheckNumbers();
		Assertions.assertTrue(checkNum.checkNumber(num.getDefaultValue()),
				String.format("enum %s with value as %s is not number", num, num.getDefaultValue()));
	}

	@DisplayName("Eaxmple of CsvSource Annotation")
	@Tag("csv_source")
	@ParameterizedTest
	@CsvSource({ "2,1.0", "3.0,test" })  // Example of CSV Source
	//@Disabled
	void checkNumber_csvSource_test(String num1, String num2) {
		CheckNumbers checkNum = new CheckNumbers();
		Assertions.assertTrue(checkNum.checkNumber(num1), String.format("%s is not number", num1));
		Assertions.assertTrue(checkNum.checkNumber(num2), String.format("%s is not number", num2));

	}

	@DisplayName("Eaxmple of MethodSource Annotation with Stream<Arguments>")
	@Tags({
		@Tag("method_source"),
		@Tag("argument stream")
	})
	@ParameterizedTest
	//@Disabled
	@MethodSource("method_supplier_arguments")  // Taking arguments from the supplier method returning stream of Arguments method_supplier_arguments
	void checkNumber_methodSource_test(Object n) {
		CheckNumbers checkNum = new CheckNumbers();
		Assertions.assertTrue(checkNum.checkNumber(n), String.format("%s is not number", n));
	}

	public static Stream<Arguments> method_supplier_arguments() {
		return Stream.of(Arguments.of(1), Arguments.of(2.6), Arguments.of("0.5"), Arguments.of("Test"));
		// we can return multiple arguments as well like Arguments.of("x',true,1); and receive these in the target method with different parameters for each argument type;
	}
	
	
	@DisplayName("Eaxmple of MethodSource Annotation with Stream<String>")
	@ParameterizedTest
	@Tags({
		@Tag("method_source"),
		@Tag("string stream")
	})
//	//@Disabled
	@MethodSource("method_supplier_strings") // Taking arguments from the supplier method returning string of stream method_supplier_strings
	void checkNumber_methodSource_withstring_test(String n) {
		CheckNumbers checkNum = new CheckNumbers();
		Assertions.assertTrue(checkNum.checkNumber(n), String.format("%s is not number", n));
	}
	
	public static Stream<String> method_supplier_strings(){
		return Stream.of("1","2.0","0.55","test");
	}
	
	@DisplayName("Eaxmple of ArgumentsSource Annotation")
	@ParameterizedTest
	@Tag("argument_provider")
	//@Disabled
	@ArgumentsSource(ArgumentProviderTest.class) // providing arguments from the custom class implementing ArgumentsProvider interface
	void checkNumber_customArgumentsProvider_test(String n) {
		CheckNumbers checkNum = new CheckNumbers();
		Assertions.assertTrue(checkNum.checkNumber(n), String.format("%s is not number", n));
	}
	
	
	@DisplayName("Eaxmple of Implicit Argument Converter")
	@ParameterizedTest
	@Tags({
		@Tag("implicit_conversion"),
		@Tag("csv_source"),
		@Tag("enum_conversion")
	})
	//@Disabled
	@CsvSource({"INTEGER_VALUE","DECIMAL_VALUE","STRING_VALUE"})  //Implicit convert the string to the required ENUM define the project
	void checkNumber_implicitconversion_test(numbers_ n) {
		CheckNumbers checkNum = new CheckNumbers();
		Assertions.assertTrue(checkNum.checkNumber(n.getDefaultValue()), String.format("%s is not number", n.getDefaultValue()));
	}
	
	
	@DisplayName("Eaxmple of Explicit Argument Converter")
	@ParameterizedTest
	@Tags({
		@Tag("explicit_conversion"),
		@Tag("csv_source")		
	})
	//@Disabled
	@CsvSource({"1,2.0","0.35,2.0","2.5,hellodasisy"})  //Explicit convert the string to the required define type in the project
	void checkNumber_explicitconversion_test(numbers_ n) {
		CheckNumbers checkNum = new CheckNumbers();
		Assertions.assertTrue(checkNum.checkNumber(n.getDefaultValue()), String.format("%s is not number", n.getDefaultValue()));
	}
	
	
	@DisplayName("Eaxmple of Explicit Argument Converter with custom class extending ArgumentConverter Interface")
	@ParameterizedTest
	@Tags({
		@Tag("argument_converter"),
		@Tag("csv_source")
	})
	//@Disabled
	@CsvSource({"2018/12/25,2018", "2019/02/11,2019"}) // Example of custom argument explicit conversion
	void getYear_ShouldWorkAsExpected(
	  @ConvertWith(ArgumentConverter_Test.class) LocalDate date, int expected) {
	   Assertions.assertEquals(expected, date.getYear());
	}
	
	@DisplayName("Eaxmple of Argument Aggregator with Custom Class and Csv Source")
	@ParameterizedTest
	@Tags({
		@Tag("argument_aggregator"),
		@Tag("csv_source")
	})
	//@Disabled
	@CsvSource({"1,20","30,0.55","10.25,0.21","0.12,test"})  // Custom Argument Aggregator Example
	void checkNumber_argumentaggregator_test(@AggregateWith(CustomArgumentAggregator.class) NumberData n) {
		CheckNumbers checkNum = new CheckNumbers();
		Assertions.assertTrue(checkNum.checkNumber(n.getI()), String.format("%s is not number", n.getI()));
		Assertions.assertTrue(checkNum.checkNumber(n.getJ()), String.format("%s is not number", n.getJ()));
	}
	
	@DisplayName("Eaxmple of Argument Aggregator with Custom Class and Enum Source")
	@ParameterizedTest
	@Tags({
		@Tag("argument_aggregator"),
		@Tag("custom"),
		@Tag("enum_source")
	})
	//@Disabled
	@EnumSource(numbers_.class) // Argument Aggregator with ENUM Source
	void checkNumber_argumentaggregator_withvalueSource_test(@AggregateWith(CustomAggregator_WithEnumSource.class) Number_Test n) {
		CheckNumbers checkNum = new CheckNumbers();
		Assertions.assertTrue(checkNum.checkNumber(n.getNum()), String.format("%s is not number", n.getNum()));
	}
	 

}
