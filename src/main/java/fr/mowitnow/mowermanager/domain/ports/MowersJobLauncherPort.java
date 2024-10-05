package fr.mowitnow.mowermanager.domain.ports;


import fr.mowitnow.mowermanager.domain.model.entities.Mower;

import java.util.List;

public interface MowersJobLauncherPort {

    /**
     * Execute un job de tondeuses
     * @return la liste des tondeuses avec leur positionnement final
     */
    List<Mower> lauchMowersJob();

}
