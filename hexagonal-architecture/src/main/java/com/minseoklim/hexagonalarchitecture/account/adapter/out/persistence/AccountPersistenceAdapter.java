package com.minseoklim.hexagonalarchitecture.account.adapter.out.persistence;

import com.minseoklim.hexagonalarchitecture.account.application.port.out.LoadAccountPort;
import com.minseoklim.hexagonalarchitecture.account.application.port.out.UpdateAccountStatePort;
import com.minseoklim.hexagonalarchitecture.account.domain.Account;
import com.minseoklim.hexagonalarchitecture.account.domain.Account.AccountId;
import com.minseoklim.hexagonalarchitecture.account.domain.Activity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
class AccountPersistenceAdapter implements LoadAccountPort, UpdateAccountStatePort {
    private final AccountRepository accountRepository;
    private final ActivityRepository activityRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account loadAccount(AccountId accountId, LocalDateTime baselineDate) {
        final AccountJpaEntity account = accountRepository.findById(accountId.value())
            .orElseThrow(EntityNotFoundException::new);
        final List<ActivityJpaEntity> activities = activityRepository.findByOwnerSince(accountId.value(), baselineDate);
        final Long withdrawalBalance = orZero(activityRepository.getWithdrawalBalanceUntil(accountId.value(), baselineDate));
        final Long depositBalance = orZero(activityRepository.getDepositBalanceUntil(accountId.value(), baselineDate));

        return accountMapper.mapToDomainEntity(
            account,
            activities,
            withdrawalBalance,
            depositBalance
        );
    }

    @Override
    public void updateActivities(Account account) {
        final List<Activity> activities = account.getActivityWindow().getActivities();
        for (final Activity activity : activities) {
            if (activity.id() == null) {
                activityRepository.save(accountMapper.mapToJpaEntity(activity));
            }
        }
    }

    private Long orZero(Long value) {
        return value == null ? 0L : value;
    }
}
