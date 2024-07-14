package com.minseoklim.hexagonalarchitecture.account.application.port.out;

import com.minseoklim.hexagonalarchitecture.account.domain.Account;

public interface UpdateAccountStatePort {
    void updateActivities(Account account);
}
