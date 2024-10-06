package fr.mowitnow.mowermanager.application.adapters;

import fr.mowitnow.mowermanager.application.resultprinter.ResultPrinter;
import fr.mowitnow.mowermanager.domain.ports.ReactiveMowerJobLauncherPort;
import fr.mowitnow.mowermanager.domain.service.ReactiveMowerJobService;
import org.springframework.stereotype.Component;

@Component
public class ReactiveMowerJobLauncherAdapter implements ReactiveMowerJobLauncherPort {

    private final ReactiveMowerJobService reactiveMowerJobService;

    private final ResultPrinter resultPrinter;

    public ReactiveMowerJobLauncherAdapter(ReactiveMowerJobService reactiveMowerJobService, ResultPrinter resultPrinter) {
        this.reactiveMowerJobService = reactiveMowerJobService;
        this.resultPrinter = resultPrinter;
    }

    @Override
    public void lauchMowersJob() {
        MowerSubscriber subscriber = new MowerSubscriber(resultPrinter);
        reactiveMowerJobService.launchMowersJob(subscriber);
    }
}
