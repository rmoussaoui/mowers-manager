package fr.mowitnow.mowermanager.domain.model.entities;

public record Coordinates(int x, int y) {

    public boolean isOutsideLawn(Lawn lawn) {

        Coordinates cornerMin = lawn.cornerMin();
        Coordinates cornerMax = lawn.cornerMax();

        return x < cornerMin.x
                || y < cornerMin.y
                || x > cornerMax.x
                || y > cornerMax.y;
    }

}
