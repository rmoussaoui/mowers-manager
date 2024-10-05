package fr.mowitnow.mowermanager.infrastructure;

import fr.mowitnow.mowermanager.domain.model.entities.Coordinates;
import fr.mowitnow.mowermanager.domain.model.entities.Lawn;
import fr.mowitnow.mowermanager.domain.model.entities.MowerPosition;
import fr.mowitnow.mowermanager.domain.model.type.Instruction;
import fr.mowitnow.mowermanager.domain.model.type.Orientation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LineParserTest {

    @Test
    void shouldReturnRightLawn() {
        Lawn lawn = LineParser.parseLawnInfos("6 8");

        Coordinates cornerMin = lawn.cornerMin();
        Coordinates cornerMax = lawn.cornerMax();

        assertThat(cornerMin.x()).isEqualTo(0);
        assertThat(cornerMin.y()).isEqualTo(0);
        assertThat(cornerMax.x()).isEqualTo(6);
        assertThat(cornerMax.y()).isEqualTo(8);
    }

    @Test
    void shouldReturnRightMowerPosition() {
        MowerPosition mowerPosition = LineParser.parseMowerInitialPositionInfos("2 6 E");
        assertThat(mowerPosition.orientation()).isEqualTo(Orientation.E);

        Coordinates coord = mowerPosition.coordinates();
        assertThat(coord.x()).isEqualTo(2);
        assertThat(coord.y()).isEqualTo(6);
    }

    @Test
    void shouldReturnRightInstructions() {
        List<Instruction> instructions = LineParser.parseMowerInstructionsLine("AGADGGA");
        assertThat(instructions).hasSize(7);
        assertThat(instructions).containsExactly(Instruction.A, Instruction.G, Instruction.A, Instruction.D,
                Instruction.G, Instruction.G, Instruction.A);
    }
}
