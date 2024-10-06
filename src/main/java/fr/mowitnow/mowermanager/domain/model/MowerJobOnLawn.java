package fr.mowitnow.mowermanager.domain.model;

import fr.mowitnow.mowermanager.domain.model.entities.Lawn;

public record MowerJobOnLawn(MowerJob mowerJob,
                             Lawn lawn) {}
