package com.minseoklim.hexagonalarchitecture.account.application.service;

import com.minseoklim.hexagonalarchitecture.account.application.port.out.AccountLock;
import com.minseoklim.hexagonalarchitecture.account.domain.Account.AccountId;
import org.springframework.stereotype.Component;

@Component
class NoOpAccountLock implements AccountLock {
    @Override
    public void lockAccount(AccountId accountId) {
        // do nothing
    }

    @Override
    public void releaseAccount(AccountId accountId) {
        // do nothing
    }
}
