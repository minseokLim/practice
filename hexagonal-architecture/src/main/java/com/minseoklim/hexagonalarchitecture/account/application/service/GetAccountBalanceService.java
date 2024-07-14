package com.minseoklim.hexagonalarchitecture.account.application.service;

import com.minseoklim.hexagonalarchitecture.account.application.port.in.AccountBalanceResource;
import com.minseoklim.hexagonalarchitecture.account.application.port.in.GetAccountBalanceQuery;
import com.minseoklim.hexagonalarchitecture.account.application.port.out.LoadAccountPort;
import com.minseoklim.hexagonalarchitecture.account.domain.Account.AccountId;
import com.minseoklim.hexagonalarchitecture.account.domain.Money;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
class GetAccountBalanceService implements GetAccountBalanceQuery {
    private final LoadAccountPort loadAccountPort;

    @Override
    public AccountBalanceResource getAccountBalance(AccountId accountId) {
        final Money money = loadAccountPort.loadAccount(accountId, LocalDateTime.now()).calculateBalance();
        return AccountBalanceResource.of(money);
    }
}
