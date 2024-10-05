package fr.mowitnow.mowermanager.application.mappers;

import fr.mowitnow.mowermanager.application.dtos.MowerPositionDto;
import fr.mowitnow.mowermanager.domain.model.entities.MowerPosition;

public class MowerPositionMapper {

    private MowerPositionMapper() {
        //prohibit instantiation
    }

    public static MowerPositionDto toMowerPositionDto(MowerPosition mowerPosition) {

        return new MowerPositionDto(mowerPosition.coordinates().x(),
                mowerPosition.coordinates().y(),
                String.valueOf(mowerPosition.orientation()));
    }
}
