package bayazvvv.paymentservice.dto;

import bayazvvv.paymentservice.model.CurrencyCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Запрос на создание платежа")
public record CreatePaymentRequest(
        @NotNull
        @Positive
        @Schema(description = "Сумма платежа", example = "1000")
        Long amount,

        @NotNull
        @Schema(description = "Валюта", example = "KZT", allowableValues = {"KZT", "USD", "EUR", "RUB", "CNY"})
        CurrencyCode currency,

        @NotBlank
        @Schema(description = "Описание платежа", example = "Оплата заказа №123")
        String description,

        @NotNull
        @Positive
        @Schema(description = "Идентификатор клиента", example = "12345")
        Long clientId
) {
}
