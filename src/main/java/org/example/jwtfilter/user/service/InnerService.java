package org.example.jwtfilter.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class InnerService {

    @Transactional(propagation = Propagation.REQUIRED)
    public void innerRequired() {
        logTransaction("innerRequired");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void innerRequiresNew() {
        logTransaction("innerRequiresNew");
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void innerSupports() {
        logTransaction("innerSupports");
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void innerNotSupported() {
        logTransaction("innerNotSupported");
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void innerMandatory() {
        logTransaction("innerMandatory");
    }

    @Transactional(propagation = Propagation.NEVER)
    public void innerNever() {
        logTransaction("innerNever");
    }

    @Transactional(propagation = Propagation.NESTED)
    public void innerNested() {
        logTransaction("innerNested");
    }

    private void logTransaction(String methodName) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            System.out.printf("[%s] 트랜잭션 활성화됨. 이름: %s%n",
                methodName,
                TransactionSynchronizationManager.getCurrentTransactionName());
        } else {
            System.out.printf("[%s] 트랜잭션 없음.%n", methodName);
        }
    }
}