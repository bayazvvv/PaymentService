package bayazvvv.paymentservice.dto;

import bayazvvv.paymentservice.model.CurrencyCode;
import bayazvvv.paymentservice.model.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Полная информация о платеже")
public record PaymentDetailsResponse(
        @Schema(example = "1")
        Long paymentId,
        @Schema(example = "1000")
        Long amount,
        @Schema(example = "KZT")
        CurrencyCode currency,
        @Schema(example = "Оплата заказа №123")
        String description,
        @Schema(example = "12345")
        Long clientId,
        @Schema(example = "CONFIRMED")
        PaymentStatus status
) {
}
