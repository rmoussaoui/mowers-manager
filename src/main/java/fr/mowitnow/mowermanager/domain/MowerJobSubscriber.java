package fr.mowitnow.mowermanager.domain;

import fr.mowitnow.mowermanager.domain.model.MowerJobOnLawn;
import fr.mowitnow.mowermanager.domain.model.entities.Mower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * Abonné qui reçoit une tondeuse et son job et s'occupe de le lui faire executer
 */
public class MowerJobSubscriber extends SubmissionPublisher<Mower> implements Flow.Processor<MowerJobOnLawn, Mower> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MowerJobSubscriber.class);

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(MowerJobOnLawn item) {
        item.mowerJob().mower().executerInstructions(item.mowerJob().instructions(), item.lawn());
        submit(item.mowerJob().mower());
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        LOGGER.error("Une erreur est survenue");
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        close();
    }

}
