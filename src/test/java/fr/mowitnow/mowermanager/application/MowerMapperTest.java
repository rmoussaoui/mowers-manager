package fr.mowitnow.mowermanager.application;

import fr.mowitnow.mowermanager.application.dtos.MowerDto;
import fr.mowitnow.mowermanager.application.dtos.MowerPositionDto;
import fr.mowitnow.mowermanager.application.mappers.MowerMapper;
import fr.mowitnow.mowermanager.domain.model.entities.Coordinates;
import fr.mowitnow.mowermanager.domain.model.entities.Mower;
import fr.mowitnow.mowermanager.domain.model.entities.MowerPosition;
import fr.mowitnow.mowermanager.domain.model.type.Orientation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MowerMapperTest {

    @Test
    void shouldMapMower() {
        MowerPosition mowerPosition = new MowerPosition(new Coordinates(3, 5), Orientation.N);
        Mower mower = new Mower(6, mowerPosition);
        MowerDto mowerDto = MowerMapper.toMowerDto(mower);

        assertThat(mowerDto.id()).isEqualTo(6);
        MowerPositionDto mowerPositionDto = mowerDto.position();
        assertThat(mowerPositionDto.x()).isEqualTo(3);
        assertThat(mowerPositionDto.y()).isEqualTo(5);
    }
}
