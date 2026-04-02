package bayazvvv.paymentservice.dto;

import bayazvvv.paymentservice.model.CurrencyCode;
import bayazvvv.paymentservice.model.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Краткая информация о платеже клиента")
public record PaymentSummaryResponse(
        @Schema(example = "1")
        Long paymentId,
        @Schema(example = "1000")
        Long amount,
        @Schema(example = "KZT")
        CurrencyCode currency,
        @Schema(example = "CONFIRMED")
        PaymentStatus status
) {
}
