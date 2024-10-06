package fr.mowitnow.mowermanager.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ImperativeMowerManagerApplicationTests {

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@Test
	void checkJobResults() {
		System.setProperty("job.execution.type", "imperatif");
		MowerManagerApplication.main(new String[]{});
		assertThat(outputStreamCaptor.toString().trim()).contains("2 3 W", "5 5 E");
	}
}
