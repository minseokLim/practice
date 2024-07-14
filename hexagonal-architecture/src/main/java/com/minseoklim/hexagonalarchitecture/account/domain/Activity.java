package com.minseoklim.hexagonalarchitecture.account.domain;

import lombok.NonNull;

import java.time.LocalDateTime;

public record Activity(
    ActivityId id,
    @NonNull Account.AccountId ownerAccountId,
    @NonNull Account.AccountId sourceAccountId,
    @NonNull Account.AccountId targetAccountId,
    @NonNull LocalDateTime timestamp,
    @NonNull Money money
) {
    public Activity(
        @NonNull Account.AccountId ownerAccountId,
        @NonNull Account.AccountId sourceAccountId,
        @NonNull Account.AccountId targetAccountId,
        @NonNull LocalDateTime timestamp,
        @NonNull Money money
    ) {
        this(null, ownerAccountId, sourceAccountId, targetAccountId, timestamp, money);
    }

    public record ActivityId(@NonNull Long value) {
    }
}
