package bayazvvv.paymentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;

@Schema(description = "Ошибка API")
public record ErrorResponse(
        @Schema(example = "2026-04-03T10:15:30Z")
        Instant timestamp,
        @Schema(example = "404")
        int status,
        @Schema(example = "Not Found")
        String error,
        @Schema(example = "Payment with id 1 was not found")
        String message,
        @Schema(example = "/payments/1")
        String path
) {
}
