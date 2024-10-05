package fr.mowitnow.mowermanager.domain.ports;


import fr.mowitnow.mowermanager.domain.exceptions.TechnicalException;
import fr.mowitnow.mowermanager.domain.model.JobInfos;

public interface MowersJobFetcherPort {

    /**
     * Extraction d'un job de tondeuses avec la pelouse à tondre et la position
     * initiale de chaque tondeuse
     * @return les informations sur le job
     * @throws TechnicalException si une erreur survient lors de la lecture du
     * fichier ou si les informations sont erronées.
     */
    JobInfos getJobInfos() throws TechnicalException;

}
