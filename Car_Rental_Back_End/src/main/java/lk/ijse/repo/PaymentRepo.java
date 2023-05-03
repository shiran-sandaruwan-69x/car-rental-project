package lk.ijse.repo;

import lk.ijse.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepo extends JpaRepository<Payment,String> {
    @Query(value = "SELECT paymentID FROM payment ORDER BY paymentID DESC LIMIT 1", nativeQuery = true)
    String getLastID();
}
