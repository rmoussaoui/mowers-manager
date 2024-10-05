package fr.mowitnow.mowermanager.domain.model;


import fr.mowitnow.mowermanager.domain.model.entities.Mower;
import fr.mowitnow.mowermanager.domain.model.type.Instruction;

import java.util.List;

/**
 * Le job a faire executer par une tondeuse
 * @param mower la tondeuse
 * @param instructions  les instructions que doit effectuer la tondeuse.
 */
public record MowerJob(Mower mower, List<Instruction> instructions) {}
