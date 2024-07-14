package com.minseoklim.hexagonalarchitecture;

import com.minseoklim.hexagonalarchitecture.account.application.port.out.LoadAccountPort;
import com.minseoklim.hexagonalarchitecture.account.domain.Account;
import com.minseoklim.hexagonalarchitecture.account.domain.Account.AccountId;
import com.minseoklim.hexagonalarchitecture.account.domain.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SendMoneySystemTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private LoadAccountPort loadAccountPort;

    @Test
    @Sql("SendMoneySystemTest.sql")
    void sendMoney() {
        final Money initialSourceBalance = sourceAccount().calculateBalance();
        final Money initialTargetBalance = targetAccount().calculateBalance();

        final ResponseEntity<Object> response = whenSendMoney(
            sourceAccountId(),
            targetAccountId(),
            transferredAmount()
        );

        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(sourceAccount().calculateBalance()).isEqualTo(initialSourceBalance.minus(transferredAmount()));
        then(targetAccount().calculateBalance()).isEqualTo(initialTargetBalance.add(transferredAmount()));
    }

    private Account sourceAccount() {
        return loadAccount(sourceAccountId());
    }

    private Account targetAccount() {
        return loadAccount(targetAccountId());
    }

    private Account loadAccount(AccountId accountId) {
        return loadAccountPort.loadAccount(accountId, LocalDateTime.now());
    }

    private AccountId sourceAccountId() {
        return new AccountId(1L);
    }

    private AccountId targetAccountId() {
        return new AccountId(2L);
    }

    private ResponseEntity<Object> whenSendMoney(AccountId sourceAccountId, AccountId targetAccountId, Money amount) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final Map<String, Object> requestBody = Map.of("sourceAccountId", sourceAccountId.value(), "targetAccountId", targetAccountId.value(), "amount", amount.amount().longValue());
        final HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        return restTemplate.exchange(
            "/accounts/send-money",
            HttpMethod.POST,
            request,
            Object.class
        );
    }

    private Money transferredAmount() {
        return Money.of(500L);
    }
}
