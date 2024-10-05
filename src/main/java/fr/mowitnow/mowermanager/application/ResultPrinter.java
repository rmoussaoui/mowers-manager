package fr.mowitnow.mowermanager.application;

import fr.mowitnow.mowermanager.application.dtos.MowerDto;

import java.util.List;

public class ResultPrinter {

    private ResultPrinter() {
        // Prohibit instantiation
    }

    public static void printMowersPositionToConsole(List<MowerDto> mowers) {
        mowers.stream().forEach(mower ->
            System.out.println("" + mower.position().x()
                    + " "
                    + mower.position().y()
                    + " "
                    + mower.position().orientation())
        );
    }
}
