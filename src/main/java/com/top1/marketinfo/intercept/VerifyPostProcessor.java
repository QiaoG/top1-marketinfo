package com.top1.marketinfo.intercept;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.support.RepositoryProxyPostProcessor;
import org.springframework.stereotype.Component;

/*
*
* Author GQ
* Date:2018/1/10
* Time:上午10:44
*/
@Component
public class VerifyPostProcessor implements RepositoryProxyPostProcessor {

    @Autowired
    private VerifyIntercepter intercepter;

    @Override
    public void postProcess(ProxyFactory factory, RepositoryInformation repositoryInformation) {
        factory.addAdvice(new SaveUpdateAdvice());
    }

    class SaveUpdateAdvice implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            return intercepter.handle(invocation);
        }
    }
}
