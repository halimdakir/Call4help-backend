package com.solidbeans.call4help;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class Call4HelpApplicationTests {

	@Test
	void verify_java_version_support() {
		var textBlock = """
				This is a test
				to see if current environment
				support some new features 
				in Java 15""";
		int actualNrOfRows = (int)textBlock.lines().count();
		int expectNrOfRows = 4;
		Assertions.assertEquals(expectNrOfRows, actualNrOfRows);
	}

}
