package fr.mowitnow.mowermanager.domain;

import fr.mowitnow.mowermanager.domain.model.entities.Coordinates;
import fr.mowitnow.mowermanager.domain.model.entities.Lawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoordinatesTest {

    private Lawn lawn;

    @BeforeEach
    void beforeEach() {
        lawn = new Lawn(new Coordinates(5, 5));
    }

    @Test
    void shouldBeOutsideLawnOnTheRight() {
        Coordinates coordinates = new Coordinates(6,2);
        assertTrue(coordinates.isOutsideLawn(lawn));
    }

    @Test
    void shouldBeOutsideLawnOnTheLeft() {
        Coordinates coordinates = new Coordinates(-1,2);
        assertTrue(coordinates.isOutsideLawn(lawn));
    }

    @Test
    void shouldBeOutsideLawnAbove() {
        Coordinates coordinates = new Coordinates(2,6);
        assertTrue(coordinates.isOutsideLawn(lawn));
    }

    @Test
    void shouldBeOutsideLawnBelow() {
        Coordinates coordinates = new Coordinates(2,-1);
        assertTrue(coordinates.isOutsideLawn(lawn));
    }

    @Test
    void shouldBeInsideLawn() {
        Coordinates coordinates = new Coordinates(2,2);
        assertFalse(coordinates.isOutsideLawn(lawn));

    }

}
