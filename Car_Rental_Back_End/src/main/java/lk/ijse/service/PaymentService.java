package lk.ijse.service;

import lk.ijse.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    void savePayment(PaymentDTO dto);
    void updatePayment(PaymentDTO dto);
    void deletePayment(String id);
    PaymentDTO searchPayment(String id);
    List<PaymentDTO> getAllPayments();
    String getLastID();
}
