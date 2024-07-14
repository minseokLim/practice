package com.minseoklim.hexagonalarchitecture.account.domain;

import lombok.NonNull;

import java.math.BigInteger;

public record Money(@NonNull BigInteger amount) {
    public static Money ZERO = Money.of(0L);

    public static Money of(long value) {
        return new Money(BigInteger.valueOf(value));
    }

    public Money add(Money money){
        return new Money(this.amount.add(money.amount));
    }

    public Money minus(Money money) {
        return new Money(this.amount.add(money.amount.negate()));
    }

    public boolean isPositiveOrZero(){
        return this.amount.compareTo(BigInteger.ZERO) >= 0;
    }
}
