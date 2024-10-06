package fr.mowitnow.mowermanager.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ReactiveMowerManagerApplicationTests {

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
		System.setProperty("job.execution.type", "reactif");
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@Test
	void checkJobResults() {
		MowerManagerApplication.main(new String[]{});
		assertThat(outputStreamCaptor.toString().trim()).contains("Execution du job en mode: reactif");
	}
}
