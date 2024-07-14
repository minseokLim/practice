package com.minseoklim.hexagonalarchitecture.account.application.port.in;

import com.minseoklim.hexagonalarchitecture.account.domain.Account.AccountId;
import com.minseoklim.hexagonalarchitecture.account.domain.Money;
import com.minseoklim.hexagonalarchitecture.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class SendMoneyCommand extends SelfValidating<SendMoneyCommand> {
    AccountId sourceAccountId;
    AccountId targetAccountId;
    Money money;

    public SendMoneyCommand(
        @NonNull AccountId sourceAccountId,
        @NonNull AccountId targetAccountId,
        @NonNull Money money
    ) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.money = money;
        this.validateSelf();
    }
}
