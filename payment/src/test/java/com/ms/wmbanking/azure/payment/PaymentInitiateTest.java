package com.ms.wmbanking.azure.payment;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.ms.wmbanking.azure.common.jackson.JsonHelper;
import com.ms.wmbanking.azure.common.model.Account;
import com.ms.wmbanking.azure.common.model.Payment;
import com.ms.wmbanking.azure.testutils.DummyExecutionContext;
import com.ms.wmbanking.azure.testutils.SimpleHttpRequestMessage;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class PaymentInitiateTest implements JsonHelper {

    private final ExecutionContext context = DummyExecutionContext.createFrom(PaymentInitiate.class,
                                                                              "run", HttpRequestMessage.class,
                                                                              ExecutionContext.class);

    private SimpleHttpRequestMessage<Payment> request = new SimpleHttpRequestMessage<>();

    @Test
    @SneakyThrows
    public void testSuccess() {
        val fct = new PaymentInitiate();

        val payment = new Payment(12.45D,
                                new Account("Mom's Account", "123-456"),
                                new Account("Son's Account", "987-654"));

        request = request.withBody(payment);

        val event = fct.run(request, context);

        assertNotNull(event.getPaymentId());
        assertSame(event.getPayment(), payment);
        assertNotNull(event.getEntryTimestamp());
    }
}