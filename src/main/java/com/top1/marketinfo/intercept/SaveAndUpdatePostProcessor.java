package com.top1.marketinfo.intercept;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.support.RepositoryProxyPostProcessor;

/*
* Author GQ
* Date:2018/1/10
* Time:上午10:44
*/
public class SaveAndUpdatePostProcessor implements RepositoryProxyPostProcessor {
    @Override
    public void postProcess(ProxyFactory factory, RepositoryInformation repositoryInformation) {
        factory.addAdvice(SaveUpdateAdvice.instance);
    }

    static enum SaveUpdateAdvice implements MethodInterceptor {
        instance;
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            return SaveAndUpdateIntercepter.handle(invocation);
        }
    }
}
