package com.minseoklim.hexagonalarchitecture.account.domain;

import com.minseoklim.hexagonalarchitecture.account.domain.Account.AccountId;
import org.junit.jupiter.api.Test;

import static com.minseoklim.hexagonalarchitecture.common.AccountTestData.defaultAccount;
import static com.minseoklim.hexagonalarchitecture.common.ActivityTestData.defaultActivity;
import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    @Test
    void withdrawalSucceeds() {
        final AccountId accountId = new AccountId(1L);
        final Account account = defaultAccount()
            .withAccountId(accountId)
            .withBaselineBalance(Money.of(555L))
            .withActivityWindow(
                new ActivityWindow(
                    defaultActivity()
                        .withTargetAccount(accountId)
                        .withMoney(Money.of(999L))
                        .build(),
                    defaultActivity()
                        .withTargetAccount(accountId)
                        .withMoney(Money.of(1L))
                        .build()
                )
            ).build();

        final boolean success = account.withdraw(Money.of(555L), new AccountId(99L));

        assertThat(success).isTrue();
        assertThat(account.getActivityWindow().getActivities()).hasSize(3);
        assertThat(account.calculateBalance()).isEqualTo(Money.of(1000L));
    }
}
