package fr.mowitnow.mowermanager.infrastructure;


import fr.mowitnow.mowermanager.domain.exceptions.BusinessException;
import fr.mowitnow.mowermanager.domain.model.entities.Coordinates;
import fr.mowitnow.mowermanager.domain.model.entities.Lawn;
import fr.mowitnow.mowermanager.domain.model.entities.MowerPosition;
import fr.mowitnow.mowermanager.domain.model.type.Instruction;
import fr.mowitnow.mowermanager.domain.model.type.Orientation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LineParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(LineParser.class);

    private static final String SPACE_STR = " ";

    private LineParser() {
        // Prohibit instantiation
    }

    /**
     * Parse une ligne représentant une pelouse à tondre et retourne la pelouse representée
     * @param lawnLine  les informations sur la pelouse: les coordonnées du point supérieur droit
     * @return  la pelouse à tondre
     */
    public static Lawn parseLawnInfos(String lawnLine){
        String[] limites = lawnLine.split(SPACE_STR);
        try {
            return new Lawn(new Coordinates(Integer.valueOf(limites[0]), Integer.valueOf(limites[1])));
        }
        catch (NumberFormatException ex) {
            LOGGER.error("Informations pelouse incorrectes: {}", lawnLine);
            throw new BusinessException("Informations pelouse incorrectes: " + lawnLine, ex);
        }
    }

    /**
     * Parse une ligne représentant une tondeuse avec sa position et orientation initiales
     * @param mowerPositionLine: la ligne d'informations de la tondeuse
     * @return une tondeuse avec sa potion et orientation initiales
     */
    public static MowerPosition parseMowerInitialPositionInfos(String mowerPositionLine) {
        String[] posInit = mowerPositionLine.split(SPACE_STR);
        Coordinates coordonneesTondeuse;
        try {
            coordonneesTondeuse = new Coordinates(Integer.valueOf(posInit[0]), Integer.valueOf(posInit[1]));
        }
        catch (NumberFormatException ex) {
            LOGGER.error("Coordonnées tondeuse incorrectes: {}", mowerPositionLine);
            throw new BusinessException("Coordonnées tondeuse incorrectes: " + mowerPositionLine, ex);
        }

        Orientation orientationTondeuse;
        try {
            orientationTondeuse = Orientation.valueOf(posInit[2]);
        }
        catch (IllegalArgumentException ex) {
            LOGGER.error("Orientation tondeuse incorrecte {}", posInit[2]);
            throw new BusinessException("Orientation tondeuse incorrecte " + posInit[2], ex);
        }

        return new MowerPosition(coordonneesTondeuse, orientationTondeuse);
    }

    /**
     * Parse une ligne contenant les instructions que doit effectuer une tondeuse
     * @param instructionsLine  la ligne des instructions
     * @return  les instructions de la tondeuse
     */
    public static List<Instruction> parseMowerInstructionsLine(String instructionsLine) {
        List<Instruction> instructions = new ArrayList<>();
        for (String instructionStr: instructionsLine.split("")) {
            try {
                instructions.add(Instruction.valueOf(instructionStr));
            }
            catch (IllegalArgumentException ex) {
                LOGGER.error("Instruction incorrecte: {}", instructionStr);
                throw new BusinessException("Instruction incorrecte: " + instructionStr, ex);
            }
        }
        return instructions;
    }

}
