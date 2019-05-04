package vu.lt.async;

        import javax.enterprise.context.ApplicationScoped;
        import javax.inject.Inject;
        import javax.inject.Named;
        import java.io.Serializable;
        import java.util.concurrent.ExecutionException;
        import java.util.concurrent.Future;

@Named
@ApplicationScoped
public class LongTaskContainer implements Serializable {

    @Inject
    private LongTaskService longTask;

    private Future<String> resultInFuture = null;

    public String callAsyncMethod() throws ExecutionException, InterruptedException {
        if (resultInFuture == null) {
            resultInFuture = longTask.asyncMethod();
            return "I just have called [LongTaskService].Is result ready? " + resultInFuture.isDone();
        } else {
            if (resultInFuture.isDone()) {
                String result = resultInFuture.get();
                resultInFuture = null;
                return "Async result is ready: " + result;
            } else {
                return "Async result is not yet ready...";
            }
        }
    }

}
