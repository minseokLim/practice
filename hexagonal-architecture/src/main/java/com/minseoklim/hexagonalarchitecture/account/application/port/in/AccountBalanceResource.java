package com.minseoklim.hexagonalarchitecture.account.application.port.in;

import com.minseoklim.hexagonalarchitecture.account.domain.Money;
import lombok.NonNull;

import java.math.BigInteger;

public record AccountBalanceResource(@NonNull BigInteger amount) {
    public static AccountBalanceResource of(Money money) {
        return new AccountBalanceResource(money.amount());
    }
}
