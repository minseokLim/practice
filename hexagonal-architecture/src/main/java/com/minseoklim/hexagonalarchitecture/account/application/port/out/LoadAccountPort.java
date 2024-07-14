package com.minseoklim.hexagonalarchitecture.account.application.port.out;

import com.minseoklim.hexagonalarchitecture.account.domain.Account;
import com.minseoklim.hexagonalarchitecture.account.domain.Account.AccountId;

import java.time.LocalDateTime;

public interface LoadAccountPort {
    Account loadAccount(AccountId accountId, LocalDateTime baselineDate);
}
