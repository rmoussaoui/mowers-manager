package fr.mowitnow.mowermanager.domain.model.entities;

/**
 * Pelouse à tondre
 */
public record Lawn(Coordinates cornerMin, Coordinates cornerMax) {

    private static final int MIN_X = 0;
    private static final int MIN_Y = 0;

    public Lawn(Coordinates cornerMax) {
        this(new Coordinates(MIN_X, MIN_Y), cornerMax);
    }
}
