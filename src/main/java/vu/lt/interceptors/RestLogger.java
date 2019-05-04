package vu.lt.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@RestLogInvocation
public class RestLogger {

    @AroundInvoke
    public Object collectBasicLoggingInformation(InvocationContext context) throws Exception{

        System.out.println("> [REST] [interceptor] > > API Call Received: " + context.getMethod().getName());
        return context.proceed();

    }
}