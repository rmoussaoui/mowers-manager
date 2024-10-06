package fr.mowitnow.mowermanager.domain.service;


import fr.mowitnow.mowermanager.application.adapters.MowerSubscriber;
import fr.mowitnow.mowermanager.domain.MowerJobSubscriber;
import fr.mowitnow.mowermanager.domain.ddd.DomainService;
import fr.mowitnow.mowermanager.domain.model.entities.Mower;
import fr.mowitnow.mowermanager.domain.ports.ReactiveMowerJobFetcherPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Flow;

@DomainService
public class ReactiveMowerJobService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReactiveMowerJobService.class);

    private final ReactiveMowerJobFetcherPort reactiveMowerJobfetcherPort;

    public ReactiveMowerJobService(ReactiveMowerJobFetcherPort reactiveMowerJobfetcherPort) {
        this.reactiveMowerJobfetcherPort = reactiveMowerJobfetcherPort;
    }

    /**
     * Lancement des jobs de tondeuses dans un mode reactif
     * @param subscriber
     */
    public void launchMowersJob(Flow.Subscriber<Mower> subscriber) {
        MowerJobSubscriber mowerJobSubscriber = new MowerJobSubscriber();
        mowerJobSubscriber.subscribe(subscriber);
        reactiveMowerJobfetcherPort.getJobInfos(mowerJobSubscriber);
    }
}
