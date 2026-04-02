package bayazvvv.paymentservice.service;

import bayazvvv.paymentservice.dto.CreatePaymentRequest;
import bayazvvv.paymentservice.dto.PaymentDetailsResponse;
import bayazvvv.paymentservice.dto.PaymentStatusResponse;
import bayazvvv.paymentservice.dto.PaymentSummaryResponse;
import java.util.List;

public interface PaymentService {

    PaymentStatusResponse createPayment(CreatePaymentRequest request);

    PaymentDetailsResponse getPayment(Long paymentId);

    PaymentStatusResponse confirmPayment(Long paymentId);

    PaymentStatusResponse cancelPayment(Long paymentId);

    List<PaymentSummaryResponse> getPaymentsByClientId(Long clientId);
}
