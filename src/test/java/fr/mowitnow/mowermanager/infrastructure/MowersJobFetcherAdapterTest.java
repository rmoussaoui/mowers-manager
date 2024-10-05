package fr.mowitnow.mowermanager.infrastructure;

import fr.mowitnow.mowermanager.domain.exceptions.BusinessException;
import fr.mowitnow.mowermanager.domain.exceptions.TechnicalException;
import fr.mowitnow.mowermanager.domain.model.JobInfos;
import fr.mowitnow.mowermanager.domain.model.MowerJob;
import fr.mowitnow.mowermanager.domain.model.entities.Lawn;
import fr.mowitnow.mowermanager.infrastructure.adapters.MowersJobFetcherAdapter;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MowersJobFetcherAdapterTest {

    private final String CHEMIN_FICHIER = "./src/test/resources/testFiles/";

    @Test
    void shouldThrowExceptionFileNotFound() {
        MowersJobFetcherAdapter jobFetcher = new MowersJobFetcherAdapter(CHEMIN_FICHIER + "fichier_inexistant.txt");
        TechnicalException ex = assertThrows(TechnicalException.class, () -> jobFetcher.getJobInfos());
        assertTrue(ex.getMessage().contains("Fichier [" + CHEMIN_FICHIER + "fichier_inexistant.txt] introuvable"));
    }

    @Test
    void shouldThrowExceptionWhenLawnInfosNotFound() {
        MowersJobFetcherAdapter jobFetcher = new MowersJobFetcherAdapter(CHEMIN_FICHIER + "1_fichier_vide.txt");
        BusinessException ex = assertThrows(BusinessException.class, () -> jobFetcher.getJobInfos());
        assertTrue(ex.getMessage().contains("Informations pelouse introuvables"));
    }

    @Test
    void shouldThrowExceptionLawnInfosInvalid() {
        MowersJobFetcherAdapter jobFetcher = new MowersJobFetcherAdapter(CHEMIN_FICHIER + "2_fichier_job_ko_1.txt");
        BusinessException ex = assertThrows(BusinessException.class, () -> jobFetcher.getJobInfos());
        assertTrue(ex.getMessage().contains("Informations pelouse incorrectes"));
    }

    @Test
    void shouldThrowExceptionMowerInstructionInvalid() {
        MowersJobFetcherAdapter jobFetcher = new MowersJobFetcherAdapter(CHEMIN_FICHIER + "3_fichier_job_ko_2.txt");
        BusinessException ex = assertThrows(BusinessException.class, () -> jobFetcher.getJobInfos());
        assertTrue(ex.getMessage().contains("Instruction incorrecte:"));
    }

    @Test
    void shouldThrowExceptionMowerCoordinatesInvalid() {
        MowersJobFetcherAdapter jobFetcher = new MowersJobFetcherAdapter(CHEMIN_FICHIER + "5_fichier_job_ko_4.txt");
        BusinessException ex = assertThrows(BusinessException.class, () -> jobFetcher.getJobInfos());
        assertTrue(ex.getMessage().contains("Coordonnées tondeuse incorrectes"));
    }

    @Test
    void shouldThrowExceptionMowerOrientationInvalid() {
        MowersJobFetcherAdapter jobFetcher = new MowersJobFetcherAdapter(CHEMIN_FICHIER + "4_fichier_job_ko_3.txt");
        BusinessException ex = assertThrows(BusinessException.class, () -> jobFetcher.getJobInfos());
        assertTrue(ex.getMessage().contains("Orientation tondeuse incorrecte"));
    }

    @Test
        void shouldReturnOnlyLawn() throws IOException {
            MowersJobFetcherAdapter jobFetcher = new MowersJobFetcherAdapter(CHEMIN_FICHIER + "fichier_ligne_1.txt");
            JobInfos jobInfos = jobFetcher.getJobInfos();
            Lawn lawn = jobInfos.lawn();

            assertThat(lawn.cornerMin().x()).isEqualTo(0);
            assertThat(lawn.cornerMin().y()).isEqualTo(0);
            assertThat(lawn.cornerMax().x()).isEqualTo(5);
            assertThat(lawn.cornerMax().y()).isEqualTo(5);
            assertThat(jobInfos.mowerJobs()).isEmpty();
        }

        @Test
        void shouldReturnMowerWithoutInstructions() {
            MowersJobFetcherAdapter jobFetcher = new MowersJobFetcherAdapter(CHEMIN_FICHIER + "fichier_ligne_2.txt");
            JobInfos jobInfos = jobFetcher.getJobInfos();

            List<MowerJob> mowerJobs = jobInfos.mowerJobs();
            assertThat(mowerJobs).hasSize(1);
            assertThat(mowerJobs.get(0).instructions()).isEmpty();
        }



}
