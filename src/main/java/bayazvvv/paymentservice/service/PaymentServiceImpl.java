package bayazvvv.paymentservice.service;

import bayazvvv.paymentservice.dto.CreatePaymentRequest;
import bayazvvv.paymentservice.dto.PaymentDetailsResponse;
import bayazvvv.paymentservice.dto.PaymentStatusResponse;
import bayazvvv.paymentservice.dto.PaymentSummaryResponse;
import bayazvvv.paymentservice.exception.BadRequestException;
import bayazvvv.paymentservice.exception.NotFoundException;
import bayazvvv.paymentservice.model.Payment;
import bayazvvv.paymentservice.model.PaymentStatus;
import bayazvvv.paymentservice.repository.PaymentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public PaymentStatusResponse createPayment(CreatePaymentRequest request) {
        Payment payment = Payment.builder()
                .amount(request.amount())
                .currency(request.currency())
                .description(request.description())
                .clientId(request.clientId())
                .status(PaymentStatus.PENDING)
                .build();

        Payment savedPayment = paymentRepository.save(payment);
        return new PaymentStatusResponse(savedPayment.getPaymentId(), savedPayment.getStatus());
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentDetailsResponse getPayment(Long paymentId) {
        Payment payment = findPayment(paymentId);
        return mapToDetailsResponse(payment);
    }

    @Override
    @Transactional
    public PaymentStatusResponse confirmPayment(Long paymentId) {
        Payment payment = findPayment(paymentId);
        validateStatusTransition(payment, PaymentStatus.CONFIRMED);
        payment.setStatus(PaymentStatus.CONFIRMED);
        return new PaymentStatusResponse(payment.getPaymentId(), payment.getStatus());
    }

    @Override
    @Transactional
    public PaymentStatusResponse cancelPayment(Long paymentId) {
        Payment payment = findPayment(paymentId);
        validateStatusTransition(payment, PaymentStatus.CANCELED);
        payment.setStatus(PaymentStatus.CANCELED);
        return new PaymentStatusResponse(payment.getPaymentId(), payment.getStatus());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentSummaryResponse> getPaymentsByClientId(Long clientId) {
        List<Payment> payments = paymentRepository.findByClientIdOrderByPaymentIdAsc(clientId);
        if (payments.isEmpty()) {
            throw new NotFoundException("Client with id " + clientId + " has no payments");
        }

        return payments.stream()
                .map(payment -> new PaymentSummaryResponse(
                        payment.getPaymentId(),
                        payment.getAmount(),
                        payment.getCurrency(),
                        payment.getStatus()
                ))
                .toList();
    }

    private Payment findPayment(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new NotFoundException("Payment with id " + paymentId + " was not found"));
    }

    private PaymentDetailsResponse mapToDetailsResponse(Payment payment) {
        return new PaymentDetailsResponse(
                payment.getPaymentId(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getDescription(),
                payment.getClientId(),
                payment.getStatus()
        );
    }

    private void validateStatusTransition(Payment payment, PaymentStatus targetStatus) {
        if (payment.getStatus() == targetStatus) {
            throw new BadRequestException("Payment with id " + payment.getPaymentId()
                    + " already has status " + targetStatus);
        }

        if (payment.getStatus() != PaymentStatus.PENDING) {
            throw new BadRequestException("Payment with id " + payment.getPaymentId()
                    + " cannot be changed from status " + payment.getStatus());
        }
    }
}
