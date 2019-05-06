package vu.lt.async;

import vu.lt.CDI.RescueOrAsync;
import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.concurrent.Future;

@ApplicationScoped
public class LongTaskService implements Serializable {

    @Inject
    @RescueOrAsync
    private EntityManager em;

    @Futureable
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Future<String> asyncMethod() {
        String result;

        System.out.println("[LongTaskService] starts working on a big task...");
        try
        {
            result = em.createNamedQuery("Shop.count").getSingleResult().toString();
            Thread.sleep(5000);
        } catch (InterruptedException e)
        {
            result = "Error while doing a [LongTaskService]: " + e.toString();
        }

        System.out.println("[LongTaskService]: big task completed.");
        return new AsyncResult<>("[LongTaskService] is completed. Amount of shops registered: " + result);
    }
}
