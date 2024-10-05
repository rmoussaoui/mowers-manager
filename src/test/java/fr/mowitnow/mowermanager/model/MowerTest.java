package fr.mowitnow.mowermanager.model;

import fr.mowitnow.mowermanager.domain.model.entities.Coordinates;
import fr.mowitnow.mowermanager.domain.model.entities.Lawn;
import fr.mowitnow.mowermanager.domain.model.entities.Mower;
import fr.mowitnow.mowermanager.domain.model.entities.MowerPosition;
import fr.mowitnow.mowermanager.domain.model.type.Instruction;
import fr.mowitnow.mowermanager.domain.model.type.Orientation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MowerTest {

    private Lawn lawn;
    private Mower mower;

    @BeforeEach
    void beforeEach() {
        lawn = new Lawn(new Coordinates(5, 5));
        mower = new Mower(1, new MowerPosition(new Coordinates(2,2), Orientation.E));
    }

    @Test
    void shouldMowerRotateRight() {
        //Given
        Coordinates initialCoord = mower.getPosition().coordinates();

        //When
        mower.executerInstructions(List.of(Instruction.D), lawn);

        //Then
        assertThat(mower.getPosition().orientation()).isEqualByComparingTo(Orientation.S);
        assertThat(mower.getPosition().coordinates()).isEqualTo(initialCoord);
    }

    @Test
    void shouldMake360DegRotationByRotatingLeft() {
        //Given
        Coordinates initialCoord = mower.getPosition().coordinates();
        Orientation initialOrient = mower.getPosition().orientation();

        //When
        mower.executerInstructions(List.of(Instruction.G, Instruction.G, Instruction.G, Instruction.G), lawn);

        //Then
        assertThat(mower.getPosition().orientation()).isEqualByComparingTo(initialOrient);
        assertThat(mower.getPosition().coordinates()).isEqualTo(initialCoord);
    }

    @Test
    void shouldMake360DegRotationByRotatingRight() {
        //Given
        Coordinates initialCoord = mower.getPosition().coordinates();
        Orientation initialOrient = mower.getPosition().orientation();

        //When
        mower.executerInstructions(List.of(Instruction.D, Instruction.D, Instruction.D, Instruction.D), lawn);

        //Then
        assertThat(mower.getPosition().orientation()).isEqualByComparingTo(initialOrient);
        assertThat(mower.getPosition().coordinates()).isEqualTo(initialCoord);
    }

    @Test
    void shouldMowerRotateLeft() {
        //Given
        Coordinates initialCoord = mower.getPosition().coordinates();

        //When
        mower.executerInstructions(List.of(Instruction.G), lawn);

        //Then
        MowerPosition mowerPosition = mower.getPosition();
        assertThat(mowerPosition.orientation()).isEqualByComparingTo(Orientation.N);
        assertThat(mowerPosition.coordinates()).isEqualTo(initialCoord);
    }

    @Test
    void shouldMowerGoForward() {
        //Given
        Orientation initialOrient = mower.getPosition().orientation();

        //When
        mower.executerInstructions(List.of(Instruction.A), lawn);

        //Then
        MowerPosition mowerPosition = mower.getPosition();
        assertThat(mowerPosition.orientation()).isEqualByComparingTo(initialOrient);
        assertThat(mowerPosition.coordinates().x()).isEqualTo(3);
        assertThat(mowerPosition.coordinates().y()).isEqualTo(2);
    }

    @Test
    void shouldNotMoveIfOutsideLawn() {
        //Given
        Mower mower = mower = new Mower(1, new MowerPosition(new Coordinates(5,2), Orientation.E));
        MowerPosition mowerInitialPosition = mower.getPosition();

        //When
        mower.executerInstructions(List.of(Instruction.A), lawn);

        //Then
        assertThat(mower.getPosition()).isEqualTo(mowerInitialPosition);
    }

    @Test
    void shouldMakeFullRotation() {

        //Given
        Coordinates initialCoord = mower.getPosition().coordinates();
        Orientation initialOrient = mower.getPosition().orientation();

        //When
        mower.executerInstructions(List.of(Instruction.A,
                Instruction.D,
                Instruction.A,
                Instruction.D,
                Instruction.A,
                Instruction.D,
                Instruction.A,
                Instruction.D), lawn);

        //Then
        assertThat(mower.getPosition().orientation()).isEqualByComparingTo(initialOrient);
        assertThat(mower.getPosition().coordinates()).isEqualTo(initialCoord);
    }

}
