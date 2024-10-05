package fr.mowitnow.mowermanager.domain.model.entities;


import fr.mowitnow.mowermanager.domain.model.type.Orientation;

/**
 * Positionnement d'une tondeuse
 */
public record MowerPosition(Coordinates coordinates, Orientation orientation) {}
