package bayazvvv.paymentservice.controller;

import bayazvvv.paymentservice.dto.CreatePaymentRequest;
import bayazvvv.paymentservice.dto.PaymentDetailsResponse;
import bayazvvv.paymentservice.dto.PaymentStatusResponse;
import bayazvvv.paymentservice.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Validated
@Tag(name = "Payments", description = "Операции с платежами")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать новый платеж")
    public PaymentStatusResponse createPayment(@Valid @RequestBody CreatePaymentRequest request) {
        return paymentService.createPayment(request);
    }

    @GetMapping("/{paymentId}")
    @Operation(summary = "Получить платеж по идентификатору")
    public PaymentDetailsResponse getPayment(@PathVariable @Positive Long paymentId) {
        return paymentService.getPayment(paymentId);
    }

    @PostMapping("/{paymentId}/confirm")
    @Operation(summary = "Подтвердить платеж")
    public PaymentStatusResponse confirmPayment(@PathVariable @Positive Long paymentId) {
        return paymentService.confirmPayment(paymentId);
    }

    @PostMapping("/{paymentId}/cancel")
    @Operation(summary = "Отменить платеж")
    public PaymentStatusResponse cancelPayment(@PathVariable @Positive Long paymentId) {
        return paymentService.cancelPayment(paymentId);
    }
}
