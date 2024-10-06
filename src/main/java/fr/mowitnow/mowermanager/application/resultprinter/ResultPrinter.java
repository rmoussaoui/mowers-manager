package fr.mowitnow.mowermanager.application.resultprinter;

import fr.mowitnow.mowermanager.application.dtos.MowerDto;

import java.util.List;;

public interface ResultPrinter {

    /**
     *
     * @param mower
     */
    void printResult(MowerDto mower);
}
