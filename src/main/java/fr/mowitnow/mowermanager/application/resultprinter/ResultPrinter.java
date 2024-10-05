package fr.mowitnow.mowermanager.application.resultprinter;

import fr.mowitnow.mowermanager.application.dtos.MowerDto;

import java.util.List;;

public interface ResultPrinter {

    /**
     * Print the final position of mowers
     * @param mowers
     */
    void printResult(List<MowerDto> mowers);
}
