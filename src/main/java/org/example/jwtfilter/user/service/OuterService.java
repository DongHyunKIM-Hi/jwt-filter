package org.example.jwtfilter.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@RequiredArgsConstructor
public class OuterService {

    private final InnerService innerService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void outerRequired() {
        logTransaction("outerRequired");
        innerService.innerRequired();  // REQUIRED
        innerService.innerRequiresNew();  // REQUIRES_NEW
        innerService.innerSupports();  // SUPPORTS
        innerService.innerNotSupported();  // NOT_SUPPORTED
        innerService.innerMandatory();  // MANDATORY
        innerService.innerNever();  // NEVER
        innerService.innerNested();  // NESTED
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
