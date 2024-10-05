package fr.mowitnow.mowermanager.application.adapters;

import fr.mowitnow.mowermanager.domain.model.entities.Mower;
import fr.mowitnow.mowermanager.domain.ports.MowersJobLauncherPort;
import fr.mowitnow.mowermanager.domain.service.MowersJobService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MowersJobLauncherAdapter implements MowersJobLauncherPort {

    private final MowersJobService mowersJobService;

    public MowersJobLauncherAdapter(MowersJobService mowersJobService) {
        this.mowersJobService = mowersJobService;
    }

    @Override
    public List<Mower> lauchMowersJob() {
        return mowersJobService.launchMowersJob();
    }
}
