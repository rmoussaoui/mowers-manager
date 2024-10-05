package fr.mowitnow.mowermanager.domain.service;

import fr.mowitnow.mowermanager.domain.ddd.DomainService;
import fr.mowitnow.mowermanager.domain.exceptions.TechnicalException;
import fr.mowitnow.mowermanager.domain.model.JobInfos;
import fr.mowitnow.mowermanager.domain.model.MowerJob;
import fr.mowitnow.mowermanager.domain.model.entities.Lawn;
import fr.mowitnow.mowermanager.domain.model.entities.Mower;
import fr.mowitnow.mowermanager.domain.ports.MowersJobFetcherPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

@DomainService
public class MowersJobService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MowersJobService.class);

    private final MowersJobFetcherPort mowersJobfetcherPort;

    public MowersJobService(MowersJobFetcherPort fetchMowersJobPort) {
        this.mowersJobfetcherPort = fetchMowersJobPort;
    }

    /**
     * Execute un job que les tondeuses doivent effectuer
     * @return  la liste des tondeuses avec leur position et orientation finales
     * @throws TechnicalException si erreur lors de l'extraction du job
     */
    public List<Mower> launchMowersJob() {

        JobInfos job = null;
        try {
            job = mowersJobfetcherPort.getJobInfos();
        }
        catch(TechnicalException ex) {
            LOGGER.error("Erreur lors de la lecture du job", ex);
        }

        if (null == job) {
            return Collections.emptyList();
        }

        Lawn lawn = job.lawn();
        job.mowerJobs().parallelStream().forEach(mowerInfosEntry ->
                mowerInfosEntry.mower().executerInstructions(mowerInfosEntry.instructions(), lawn));

        return job.mowerJobs().stream().map(MowerJob::mower).toList();

    }
}
