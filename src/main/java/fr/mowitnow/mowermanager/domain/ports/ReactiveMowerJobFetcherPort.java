package fr.mowitnow.mowermanager.domain.ports;

import fr.mowitnow.mowermanager.domain.MowerJobSubscriber;
import fr.mowitnow.mowermanager.domain.exceptions.TechnicalException;

public interface ReactiveMowerJobFetcherPort {

    /**
     * Lit un job de tondeuses de manière reactive
     * @param subscriber    l'abonné qui réagit à la lecture d'une tondeuse
     * @throws TechnicalException En cas d'erreur de lecture du job
     */
    void getJobInfos(MowerJobSubscriber subscriber) throws TechnicalException;

}
