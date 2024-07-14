package com.minseoklim.hexagonalarchitecture.account.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minseoklim.hexagonalarchitecture.account.application.port.in.SendMoneyUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SendMoneyController.class)
class SendMoneyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SendMoneyUseCase sendMoneyUseCase;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSendMoney() throws Exception {
        final SendMoneyRequest request = new SendMoneyRequest(41L, 42L, 500L);

        mockMvc.perform(
            MockMvcRequestBuilders.post("/accounts/send-money")
                .header("Content-Type", "application/json")
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isOk());

        then(sendMoneyUseCase).should().sendMoney(eq(request.toCommand()));
    }
}
