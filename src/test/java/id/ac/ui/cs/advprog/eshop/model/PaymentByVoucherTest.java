package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentByVoucherTest {
    Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<String, String>();
    }

    @Test
    void testEmptyPaymentData() {
        PaymentByVoucher payment = new PaymentByVoucher("f85a8175-b435-4c98-a23d-78c9b56cb42a", PaymentMethod.BY_VOUCHER.getValue(), this.paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setPaymentData(this.paymentData));
    }

    @Test
    void testSetValidPaymentData() {
        this.paymentData.put("voucherCode", "ESHOP9821RTY4367");
        PaymentByVoucher payment = new PaymentByVoucher("29e7b5c6-47d8-4cdc-9c11-5f42d8e8f73b", PaymentMethod.BY_VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetShortInvalidPaymentData() {
        this.paymentData.put("voucherCode", "ESHOP789ABC123");
        PaymentByVoucher payment = new PaymentByVoucher("b34c2197-a1e3-42e1-9203-d781436c2bf5", PaymentMethod.BY_VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testNoPrefixInvalidPaymentData() {
        this.paymentData.put("voucherCode", "87562JKL9");
        PaymentByVoucher payment = new PaymentByVoucher("7a8b4c2d-5e6f-4a3b-8c9d-1e2f3a4b5c6d", PaymentMethod.BY_VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testNoEightNumberPaymentData() {
        this.paymentData.put("voucherCode", "ESHOPXYZTRW123QA");
        PaymentByVoucher payment = new PaymentByVoucher("d1e2f3a4-b5c6-7d8e-9f0a-1b2c3d4e5f6a", PaymentMethod.BY_VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}