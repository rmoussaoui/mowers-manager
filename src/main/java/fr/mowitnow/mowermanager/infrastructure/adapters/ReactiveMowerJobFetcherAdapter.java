package fr.mowitnow.mowermanager.infrastructure.adapters;

import fr.mowitnow.mowermanager.domain.MowerJobSubscriber;
import fr.mowitnow.mowermanager.domain.exceptions.BusinessException;
import fr.mowitnow.mowermanager.domain.exceptions.TechnicalException;
import fr.mowitnow.mowermanager.domain.model.MowerJob;
import fr.mowitnow.mowermanager.domain.model.MowerJobOnLawn;
import fr.mowitnow.mowermanager.domain.model.entities.Lawn;
import fr.mowitnow.mowermanager.domain.model.entities.Mower;
import fr.mowitnow.mowermanager.domain.model.type.Instruction;
import fr.mowitnow.mowermanager.domain.ports.ReactiveMowerJobFetcherPort;
import fr.mowitnow.mowermanager.infrastructure.LineParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.SubmissionPublisher;

@Component
public class ReactiveMowerJobFetcherAdapter implements ReactiveMowerJobFetcherPort {

    private final static Logger LOGGER = LoggerFactory.getLogger(MowersJobFetcherAdapter.class);

    private final String jobFilePath;

    SubmissionPublisher<MowerJobOnLawn> publisher;

    public ReactiveMowerJobFetcherAdapter(@Value("${job.file.path}") String jobFilePath) {
        this.jobFilePath = jobFilePath;
        this.publisher = new SubmissionPublisher<MowerJobOnLawn>();
    }

    public void getJobInfos(MowerJobSubscriber subscriber) throws TechnicalException {

        publisher.subscribe(subscriber);

        try (BufferedReader reader = new BufferedReader(new FileReader(jobFilePath))) {

            Lawn lawn = processLawnLine(reader);

            int currentMowerId = 1;

            //Read until mower is not found
            while (true) {
                Optional<MowerJob> optMowerInfosEntry = readNextMowerInfos(currentMowerId, reader);
                if(optMowerInfosEntry.isEmpty()) {
                    break;
                }
                MowerJobOnLawn job = new MowerJobOnLawn(optMowerInfosEntry.get(), lawn);
                LOGGER.debug("Un job a été trouvé: {}", job);
                publisher.submit(job);
            }

        } catch (FileNotFoundException e) {
            LOGGER.error("Fichier {} introuvable", jobFilePath);
            throw new TechnicalException("Fichier [" + jobFilePath + "] introuvable", e);
        } catch (IOException e) {
            LOGGER.error("Fichier {} introuvable", jobFilePath);
            throw new TechnicalException("Erreur de lecture du fichier [" + jobFilePath + "]", e);
        }
    }


    private  Lawn processLawnLine(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (null == line) {
            throw new BusinessException("Informations pelouse introuvables");
        }
        return LineParser.parseLawnInfos(line);
    }

    private Optional<MowerJob> readNextMowerInfos(int currentMowerId, BufferedReader reader) throws IOException {

        String initialPositionMower = reader.readLine();
        if (null != initialPositionMower) {
            Mower mower = new Mower(currentMowerId, LineParser.parseMowerInitialPositionInfos(initialPositionMower));
            String instructionsLine = reader.readLine();
            List<Instruction> instructions = null != instructionsLine ?
                    LineParser.parseMowerInstructionsLine(instructionsLine) : new ArrayList<>();
            return Optional.of(new MowerJob(mower, instructions));
        }
        return Optional.empty();
    }


}
