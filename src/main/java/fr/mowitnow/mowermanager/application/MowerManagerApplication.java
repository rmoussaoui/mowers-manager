package fr.mowitnow.mowermanager.application;

import fr.mowitnow.mowermanager.application.mappers.MowerMapper;
import fr.mowitnow.mowermanager.application.resultprinter.ResultPrinter;
import fr.mowitnow.mowermanager.domain.ports.MowersJobLauncherPort;
import fr.mowitnow.mowermanager.domain.ports.ReactiveMowerJobLauncherPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import java.util.Objects;

@SpringBootApplication
@Profile("!test")
public class MowerManagerApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(MowerManagerApplication.class);
	private static final String MODE_REACTIF = "reactif";

	private final MowersJobLauncherPort mowersJobLauncherPort;
	private final ReactiveMowerJobLauncherPort reactiveMowerJobLauncherPort;
	private final ResultPrinter resultPrinter;
	private final String executionType;

    public MowerManagerApplication(@Value("${job.execution.type:imperatif}") String executionType,
								   MowersJobLauncherPort mowersJobLauncherPort,
								   ReactiveMowerJobLauncherPort reactiveMowerJobLauncherPort,
								   ResultPrinter resultPrinter) {
        this.mowersJobLauncherPort = mowersJobLauncherPort;
        this.reactiveMowerJobLauncherPort = reactiveMowerJobLauncherPort;
        this.resultPrinter = resultPrinter;
        this.executionType = executionType;
    }

    public static void main(String[] args) {
		SpringApplication.run(MowerManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Execute un job de tondeuses et écrit
		// la positionnement de chaque tondeuse
		if (MODE_REACTIF.equals(executionType)) {
			LOGGER.info("Execution du programme en mode reactif");
			reactiveMowerJobLauncherPort.lauchMowersJob();
		}
		else {
			LOGGER.info("Execution du programme en mode impératif");
			mowersJobLauncherPort.lauchMowersJob().stream()
					.map(MowerMapper::toMowerDto)
					.forEach(m -> resultPrinter.printResult(m));
		}
	}
}


