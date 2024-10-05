package fr.mowitnow.mowermanager.application;

import fr.mowitnow.mowermanager.application.mappers.MowerMapper;
import fr.mowitnow.mowermanager.application.resultprinter.ResultPrinter;
import fr.mowitnow.mowermanager.domain.ports.MowersJobLauncherPort;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("!test")
public class MowerManagerApplication implements CommandLineRunner {

	private final MowersJobLauncherPort mowersJobLauncherPort;
	private final ResultPrinter resultPrinter;

    public MowerManagerApplication(MowersJobLauncherPort mowersJobLauncherPort, ResultPrinter resultPrinter) {
        this.mowersJobLauncherPort = mowersJobLauncherPort;
        this.resultPrinter = resultPrinter;
    }

    public static void main(String[] args) {
		SpringApplication.run(MowerManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Execute un job de tondeuses et écrit dans la sortie standard
		// la positionnement de chaque tondeuse
		resultPrinter.printResult(
				mowersJobLauncherPort.lauchMowersJob().stream().map(MowerMapper::toMowerDto).toList()
		);
	}

}


