package com.minseoklim.hexagonalarchitecture.account.application.port.in;

import com.minseoklim.hexagonalarchitecture.account.domain.Account.AccountId;

public interface GetAccountBalanceQuery {
    AccountBalanceResource getAccountBalance(AccountId accountId);
}
