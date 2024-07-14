package com.minseoklim.hexagonalarchitecture.account.adapter.in.web;

import com.minseoklim.hexagonalarchitecture.account.application.port.in.SendMoneyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class SendMoneyController {
    private final SendMoneyUseCase sendMoneyUseCase;

    @PostMapping("/accounts/send-money")
    public void sendMoney(@RequestBody SendMoneyRequest request) {
        sendMoneyUseCase.sendMoney(request.toCommand());
    }
}
