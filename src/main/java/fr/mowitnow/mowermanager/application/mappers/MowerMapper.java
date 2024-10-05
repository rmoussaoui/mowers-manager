package fr.mowitnow.mowermanager.application.mappers;


import fr.mowitnow.mowermanager.application.dtos.MowerDto;
import fr.mowitnow.mowermanager.domain.model.entities.Mower;

public class MowerMapper {

    private MowerMapper() {
        //Prohibit instantiation
    }

    public static MowerDto toMowerDto(Mower mower) {
        return new MowerDto(mower.getId(),
                MowerPositionMapper.toMowerPositionDto(mower.getPosition()));
    }

}
