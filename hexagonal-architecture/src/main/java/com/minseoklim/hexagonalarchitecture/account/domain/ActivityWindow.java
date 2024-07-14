package com.minseoklim.hexagonalarchitecture.account.domain;

import com.minseoklim.hexagonalarchitecture.account.domain.Account.AccountId;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ActivityWindow {
    private final List<Activity> activities;

    public ActivityWindow(@NonNull List<Activity> activities) {
        this.activities = activities;
    }

    public ActivityWindow(@NonNull Activity... activities) {
        this.activities = new ArrayList<>(Arrays.asList(activities));
    }

    public Money calculateBalance(AccountId accountId) {
        final Money depositBalance = activities.stream()
            .filter(a -> a.targetAccountId().equals(accountId))
            .map(Activity::money)
            .reduce(Money.ZERO, Money::add);

        final Money withdrawalBalance = activities.stream()
            .filter(a -> a.sourceAccountId().equals(accountId))
            .map(Activity::money)
            .reduce(Money.ZERO, Money::add);

        return depositBalance.minus(withdrawalBalance);
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    public List<Activity> getActivities() {
        return Collections.unmodifiableList(this.activities);
    }
}
