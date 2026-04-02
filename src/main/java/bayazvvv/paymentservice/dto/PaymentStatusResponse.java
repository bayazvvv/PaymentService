package bayazvvv.paymentservice.dto;

import bayazvvv.paymentservice.model.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Краткий ответ по статусу платежа")
public record PaymentStatusResponse(
        @Schema(example = "1")
        Long paymentId,
        @Schema(example = "PENDING")
        PaymentStatus status
) {
}
