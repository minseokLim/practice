package com.minseoklim.hexagonalarchitecture.account.application.service;

import com.minseoklim.hexagonalarchitecture.account.application.port.in.SendMoneyCommand;
import com.minseoklim.hexagonalarchitecture.account.application.port.in.SendMoneyUseCase;
import com.minseoklim.hexagonalarchitecture.account.application.port.out.AccountLock;
import com.minseoklim.hexagonalarchitecture.account.application.port.out.LoadAccountPort;
import com.minseoklim.hexagonalarchitecture.account.application.port.out.UpdateAccountStatePort;
import com.minseoklim.hexagonalarchitecture.account.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Transactional
class SendMoneyService implements SendMoneyUseCase {
    private final LoadAccountPort loadAccountPort;
    private final AccountLock accountLock;
    private final UpdateAccountStatePort updateAccountStatePort;

    @Override
    public boolean sendMoney(SendMoneyCommand command) {
        final LocalDateTime baselineDate = LocalDateTime.now().minusDays(10);

        final Account sourceAccount = loadAccountPort.loadAccount(command.getSourceAccountId(), baselineDate);
        final Account targetAccount = loadAccountPort.loadAccount(command.getTargetAccountId(), baselineDate);

        accountLock.lockAccount(sourceAccount.getId());
        if (!sourceAccount.withdraw(command.getMoney(), targetAccount.getId())) {
            accountLock.releaseAccount(sourceAccount.getId());
            return false;
        }

        accountLock.lockAccount(targetAccount.getId());
        if (!targetAccount.deposit(command.getMoney(), sourceAccount.getId())) {
            accountLock.releaseAccount(sourceAccount.getId());
            accountLock.releaseAccount(targetAccount.getId());
            return false;
        }

        updateAccountStatePort.updateActivities(sourceAccount);
        updateAccountStatePort.updateActivities(targetAccount);

        accountLock.releaseAccount(sourceAccount.getId());
        accountLock.releaseAccount(targetAccount.getId());
        return true;
    }
}
