package com.minseoklim.hexagonalarchitecture.account.adapter.in.web;

import com.minseoklim.hexagonalarchitecture.account.application.port.in.SendMoneyCommand;
import com.minseoklim.hexagonalarchitecture.account.domain.Account.AccountId;
import com.minseoklim.hexagonalarchitecture.account.domain.Money;

record SendMoneyRequest(
    Long sourceAccountId,
    Long targetAccountId,
    Long amount
) {
    public SendMoneyCommand toCommand() {
        return new SendMoneyCommand(
            new AccountId(sourceAccountId),
            new AccountId(targetAccountId),
            Money.of(amount)
        );
    }
}
