package fr.mowitnow.mowermanager.application.resultprinter.impl;

import fr.mowitnow.mowermanager.application.dtos.MowerDto;
import fr.mowitnow.mowermanager.application.resultprinter.ResultPrinter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsoleResultPrinter implements ResultPrinter {

    @Override
    public void printResult(List<MowerDto> mowers) {
        mowers.stream().forEach(mower ->
                System.out.println("" + mower.position().x()
                        + " "
                        + mower.position().y()
                        + " "
                        + mower.position().orientation())
        );
    }
}
