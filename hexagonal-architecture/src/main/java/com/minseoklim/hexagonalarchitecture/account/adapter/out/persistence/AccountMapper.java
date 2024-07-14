package com.minseoklim.hexagonalarchitecture.account.adapter.out.persistence;

import com.minseoklim.hexagonalarchitecture.account.domain.Account;
import com.minseoklim.hexagonalarchitecture.account.domain.Account.AccountId;
import com.minseoklim.hexagonalarchitecture.account.domain.Activity;
import com.minseoklim.hexagonalarchitecture.account.domain.Activity.ActivityId;
import com.minseoklim.hexagonalarchitecture.account.domain.ActivityWindow;
import com.minseoklim.hexagonalarchitecture.account.domain.Money;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class AccountMapper {
    Account mapToDomainEntity(
        AccountJpaEntity account,
        List<ActivityJpaEntity> activities,
        Long withdrawalBalance,
        Long depositBalance
    ) {
        final Money baselineBalance = Money.of(depositBalance).minus(Money.of(withdrawalBalance));

        return Account.withId(
            new AccountId(account.getId()),
            baselineBalance,
            mapToActivityWindow(activities)
        );
    }

    ActivityWindow mapToActivityWindow(List<ActivityJpaEntity> activities) {
        final List<Activity> mappedActivities = new ArrayList<>();

        for (final ActivityJpaEntity activity : activities) {
            mappedActivities.add(
                new Activity(
                    new ActivityId(activity.getId()),
                    new AccountId(activity.getOwnerAccountId()),
                    new AccountId(activity.getSourceAccountId()),
                    new AccountId(activity.getTargetAccountId()),
                    activity.getTimestamp(),
                    Money.of(activity.getAmount())
                )
            );
        }

        return new ActivityWindow(mappedActivities);
    }

    ActivityJpaEntity mapToJpaEntity(Activity activity) {
        return new ActivityJpaEntity(
            activity.id() == null ? null : activity.id().value(),
            activity.timestamp(),
            activity.ownerAccountId().value(),
            activity.sourceAccountId().value(),
            activity.targetAccountId().value(),
            activity.money().amount().longValue()
        );
    }
}
