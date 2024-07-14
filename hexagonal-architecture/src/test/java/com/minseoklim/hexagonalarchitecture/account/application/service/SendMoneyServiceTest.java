package com.minseoklim.hexagonalarchitecture.account.application.service;

import com.minseoklim.hexagonalarchitecture.account.application.port.in.SendMoneyCommand;
import com.minseoklim.hexagonalarchitecture.account.application.port.out.AccountLock;
import com.minseoklim.hexagonalarchitecture.account.application.port.out.LoadAccountPort;
import com.minseoklim.hexagonalarchitecture.account.application.port.out.UpdateAccountStatePort;
import com.minseoklim.hexagonalarchitecture.account.domain.Account;
import com.minseoklim.hexagonalarchitecture.account.domain.Account.AccountId;
import com.minseoklim.hexagonalarchitecture.account.domain.Money;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

class SendMoneyServiceTest {
    private final LoadAccountPort loadAccountPort = Mockito.mock(LoadAccountPort.class);
    private final AccountLock accountLock = Mockito.mock(AccountLock.class);
    private final UpdateAccountStatePort updateAccountStatePort = Mockito.mock(UpdateAccountStatePort.class);
    private final SendMoneyService sendMoneyService = new SendMoneyService(loadAccountPort, accountLock, updateAccountStatePort);

    @Test
    void transactionSucceeds() {
        final Account sourceAccount = givenSourceAccount();
        final Account targetAccount = givenTargetAccount();

        givenWithdrawalWillSucceed(sourceAccount);
        givenDepositWillSucceed(targetAccount);

        final Money money = Money.of(500L);
        final SendMoneyCommand command = new SendMoneyCommand(sourceAccount.getId(), targetAccount.getId(), money);

        final boolean success = sendMoneyService.sendMoney(command);

        assertThat(success).isTrue();

        final AccountId sourceAccountId = sourceAccount.getId();
        final AccountId targetAccountId = targetAccount.getId();

        then(accountLock).should().lockAccount(eq(sourceAccountId));
        then(sourceAccount).should().withdraw(eq(money), eq(targetAccountId));
        then(accountLock).should().releaseAccount(eq(sourceAccountId));

        then(accountLock).should().lockAccount(eq(targetAccountId));
        then(targetAccount).should().deposit(eq(money), eq(sourceAccountId));
        then(accountLock).should().releaseAccount(eq(targetAccountId));

        thenAccountsHaveBeenUpdated(sourceAccountId, targetAccountId);
    }

    private Account givenSourceAccount() {
        return givenAnAccountWithId(new AccountId(41L));
    }

    private Account givenTargetAccount() {
        return givenAnAccountWithId(new AccountId(42L));
    }

    private void givenWithdrawalWillSucceed(Account account) {
        given(account.withdraw(any(Money.class), any(AccountId.class))).willReturn(true);
    }

    private void givenDepositWillSucceed(Account account) {
        given(account.deposit(any(Money.class), any(AccountId.class))).willReturn(true);
    }

    private void thenAccountsHaveBeenUpdated(AccountId... accountIds) {
        final ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        then(updateAccountStatePort).should(times(accountIds.length)).updateActivities(accountCaptor.capture());

        final List<AccountId> updatedAccountIds = accountCaptor.getAllValues()
            .stream()
            .map(Account::getId)
            .toList();

        for (final AccountId accountId : accountIds) {
            assertThat(updatedAccountIds).contains(accountId);
        }
    }

    private Account givenAnAccountWithId(AccountId id) {
        final Account account = Mockito.mock(Account.class);
        given(account.getId()).willReturn(id);
        given(loadAccountPort.loadAccount(eq(account.getId()), any(LocalDateTime.class))).willReturn(account);
        return account;
    }
}
