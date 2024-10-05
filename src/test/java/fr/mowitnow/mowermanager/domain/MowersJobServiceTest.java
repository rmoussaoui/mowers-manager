package fr.mowitnow.mowermanager.domain;

import fr.mowitnow.mowermanager.domain.exceptions.TechnicalException;
import fr.mowitnow.mowermanager.domain.model.JobInfos;
import fr.mowitnow.mowermanager.domain.model.MowerJob;
import fr.mowitnow.mowermanager.domain.model.entities.Coordinates;
import fr.mowitnow.mowermanager.domain.model.entities.Lawn;
import fr.mowitnow.mowermanager.domain.model.entities.Mower;
import fr.mowitnow.mowermanager.domain.model.entities.MowerPosition;
import fr.mowitnow.mowermanager.domain.model.type.Instruction;
import fr.mowitnow.mowermanager.domain.model.type.Orientation;
import fr.mowitnow.mowermanager.domain.ports.MowersJobFetcherPort;
import fr.mowitnow.mowermanager.domain.service.MowersJobService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class MowersJobServiceTest {

    @Mock
    private MowersJobFetcherPort mowersJobFetcherPort;

    @InjectMocks
    private MowersJobService mowersJobService;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnEmptyCollection() {
        when(mowersJobFetcherPort.getJobInfos()).thenThrow(TechnicalException.class);
        assert(mowersJobService.launchMowersJob()).isEmpty();
    }

    @Test
    void shouldReturnListWithSameItems() {
        Lawn lawn = new Lawn(new Coordinates(5, 5));
        Mower mower = new Mower(3, new MowerPosition(new Coordinates(5, 5), Orientation.E));
        MowerJob mowerJob = new MowerJob(mower, List.of(Instruction.A));
        JobInfos jobInfos = new JobInfos(lawn, List.of(mowerJob));

        when(mowersJobFetcherPort.getJobInfos()).thenReturn(jobInfos);

        List<Mower> mowers = mowersJobService.launchMowersJob();

        assertThat(mowers).hasSize(1);
        assertThat(mowers.get(0)).isNotNull();
        assertThat(mowers.get(0).getId()).isEqualTo(3);


    }
}
