package fr.mowitnow.mowermanager.domain.model;


import fr.mowitnow.mowermanager.domain.model.entities.Lawn;

import java.util.List;

/**
 * Représente un job que doit effectuer un ensemble de tondeuses
 * @param lawn  la pelouse à tondre
 * @param mowerJobs les job que doit effectuer chaque tondeuse
 */
public record JobInfos(Lawn lawn, List<MowerJob> mowerJobs) {}
