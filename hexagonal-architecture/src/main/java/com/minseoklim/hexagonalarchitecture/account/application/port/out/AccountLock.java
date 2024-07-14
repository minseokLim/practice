package com.minseoklim.hexagonalarchitecture.account.application.port.out;

import com.minseoklim.hexagonalarchitecture.account.domain.Account.AccountId;

public interface AccountLock {
    void lockAccount(AccountId accountId);

    void releaseAccount(AccountId accountId);
}
