package fr.mowitnow.mowermanager.domain.model.entities;

import fr.mowitnow.mowermanager.domain.model.type.Instruction;
import fr.mowitnow.mowermanager.domain.model.type.Orientation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Mower {

    private static final Logger LOGGER = LoggerFactory.getLogger(Mower.class);

    private final int id;

    private MowerPosition position;

    public Mower(int id, MowerPosition position) {
        this.id = id;
        this.position = position;
    }

    public int getId() {
        return this.id;
    }

    public MowerPosition getPosition() {
        return position;
    }

    /**
     * Execution d'une serie d'instructions
     * @param instructions  les instructions que doit effectuer la tondeuse
     * @param pelouse   la pelouse à tondre
     */
    public void executerInstructions(List<Instruction> instructions, Lawn pelouse) {

        for (Instruction instruction: instructions) {
            switch (instruction){
                case A :
                    avancer(pelouse);
                    break;
                case D :
                    pivoterDroite();
                    break;
                case G :
                    pivoterGauche();
                    break;
            }
        }
    }

    private void avancer(Lawn pelouse) {
        int x = position.coordinates().x();
        int y = position.coordinates().y();
        Coordinates nextCoordinates = null;
        switch (position.orientation()) {
            case N:
                nextCoordinates = new Coordinates(x, y + 1);
                break;
            case E:
                nextCoordinates = new Coordinates(x + 1, y);
                break;
            case S:
                nextCoordinates = new Coordinates(x, y - 1);
                break;
            case W:
                nextCoordinates = new Coordinates(x - 1, y);
                break;
        }

        if (!nextCoordinates.isOutsideLawn(pelouse)) {
            position = new MowerPosition(nextCoordinates, position.orientation());
        }
    }

    private void pivoterDroite() {
        Orientation nextOrientation = null;
        switch (position.orientation()){
            case N :
                nextOrientation =  Orientation.E;
                break;
            case E :
                nextOrientation =  Orientation.S;
                break;
            case S :
                nextOrientation =  Orientation.W;
                break;
            case W :
                nextOrientation =  Orientation.N;
                break;
        }
        position = new MowerPosition(position.coordinates(), nextOrientation);
    }

    private void pivoterGauche() {
        Orientation nextOrientation = null ;
        switch (position.orientation()){
            case N :
                nextOrientation =  Orientation.W;
                break;
            case E :
                nextOrientation =  Orientation.N;
                break;
            case S :
                nextOrientation =  Orientation.E;
                break;
            case W :
                nextOrientation =  Orientation.S;
                break;
        }

        position = new MowerPosition(position.coordinates(), nextOrientation);
    }
}
