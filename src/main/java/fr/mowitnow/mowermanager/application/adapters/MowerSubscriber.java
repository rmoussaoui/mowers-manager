package fr.mowitnow.mowermanager.application.adapters;

import fr.mowitnow.mowermanager.application.mappers.MowerMapper;
import fr.mowitnow.mowermanager.application.resultprinter.ResultPrinter;
import fr.mowitnow.mowermanager.domain.model.entities.Mower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Flow;

/**
 * Abonné qui, lorsqu'il reçoit une tondeuse dont le job est terminé,
 * affiche la position finale
 */
public class MowerSubscriber implements Flow.Subscriber<Mower> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MowerSubscriber.class);

    private Flow.Subscription subscription;

    private final ResultPrinter resultPrinter;

    public MowerSubscriber(ResultPrinter resultPrinter) {
        this.resultPrinter = resultPrinter;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Mower item) {
        resultPrinter.printResult(MowerMapper.toMowerDto(item));
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        LOGGER.error("Une erreur est survenue: {}", throwable);
    }

    @Override
    public void onComplete() {
        LOGGER.debug("Une tondeuse a été affichée");
    }
}
