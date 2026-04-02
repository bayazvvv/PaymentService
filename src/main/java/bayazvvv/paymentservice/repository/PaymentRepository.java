package bayazvvv.paymentservice.repository;

import bayazvvv.paymentservice.model.Payment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByClientIdOrderByPaymentIdAsc(Long clientId);
}
