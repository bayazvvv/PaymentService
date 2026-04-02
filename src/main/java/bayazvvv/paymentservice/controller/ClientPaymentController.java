package bayazvvv.paymentservice.controller;

import bayazvvv.paymentservice.dto.PaymentSummaryResponse;
import bayazvvv.paymentservice.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Validated
@Tag(name = "Client Payments", description = "Операции со списком платежей клиента")
public class ClientPaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{clientId}/payments")
    @Operation(summary = "Получить список платежей клиента")
    public List<PaymentSummaryResponse> getClientPayments(@PathVariable @Positive Long clientId) {
        return paymentService.getPaymentsByClientId(clientId);
    }
}
