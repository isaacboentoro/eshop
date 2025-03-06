package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        this.paymentRepository = new PaymentRepository();
        this.payments = new ArrayList<>();
        Map<String, String> validVoucherData = new HashMap<String,String>();
        validVoucherData.put("voucherCode", "ESHOP1234ABC5678");
        Map<String, String> invalidVoucherData = new HashMap<String,String>();
        invalidVoucherData.put("voucherCode", "bwaaaaaaaaaaaa");
        Map<String, String> validBankData = new HashMap<String,String>();
        validBankData.put("bankName", "Bank Central Asia");
        validBankData.put("referenceCode", "bc50e0c2-84fc-41ba-836c-738cec99fade");

        Payment payment1 = new Payment("0d658088-9d8e-43ba-995e-7fd8557b89c9", PaymentMethod.BY_VOUCHER.getValue(), PaymentStatus.SUCCESS.getValue(), validVoucherData);
        Payment payment2 = new Payment("4ca686dd-56cd-44d3-a30b-950d66b7e71f", PaymentMethod.BY_VOUCHER.getValue(), PaymentStatus.REJECTED.getValue(), invalidVoucherData);
        Payment payment3 = new Payment("e4884da6-b44c-414c-abf3-6d633efc4586", PaymentMethod.BY_TRANSFER.getValue(), PaymentStatus.SUCCESS.getValue(), validBankData);
        payments.add(payment1);
        payments.add(payment2);
        payments.add(payment3);
    }

    @Test
    void testSaveFromCreate() {
        Payment payment = payments.getFirst();
        Payment result = paymentRepository.save(payment);

        Payment paymentResult = paymentRepository.findById(payments.getFirst().getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), paymentResult.getId());
        assertEquals(payment.getMethod(), paymentResult.getMethod());
        assertEquals(payment.getStatus(), paymentResult.getStatus());
        assertEquals(payment.getPaymentData(), paymentResult.getPaymentData());
    }

    @Test
    void testSaveFromEdit() {
        Payment payment = payments.get(1);
        paymentRepository.save(payment);
        Payment modifiedPayment = new Payment(payment.getId(), payment.getMethod(), PaymentStatus.SUCCESS.getValue(), payment.getPaymentData());
        Payment result = paymentRepository.save(modifiedPayment);

        Payment paymentResult = paymentRepository.findById(payments.getFirst().getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), paymentResult.getId());
        assertEquals(payment.getMethod(), paymentResult.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), paymentResult.getStatus());
        assertEquals(payment.getPaymentData(), paymentResult.getPaymentData());
    }

    @Test
    void testFindByValidId() {
        for (Payment payment : payments){
            paymentRepository.save(payment);
        }

        Payment paymentResult = paymentRepository.findById(payments.get(2).getId());
        assertEquals(payments.get(2).getId(), paymentResult.getId());
        assertEquals(payments.get(2).getMethod(), paymentResult.getMethod());
        assertEquals(payments.get(2).getStatus(), paymentResult.getStatus());
        assertEquals(payments.get(2).getPaymentData(), paymentResult.getPaymentData());
    }

    @Test
    void testFindByNonexistentID() {
        for (Payment payment : payments){
            paymentRepository.save(payment);
        }

        Payment paymentResult = paymentRepository.findById("bweeeeeeeh");
        assertNull(paymentResult);
    }

    @Test
    void testFindAll() {
        for (Payment payment : payments){
            paymentRepository.save(payment);
        }

        List<Payment> paymentList = paymentRepository.findAll();
        assertEquals(paymentList.size(), payments.size());
    }
}