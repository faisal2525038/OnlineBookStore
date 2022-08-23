package com.onlinebookstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebookstore.dao.PaymentRepository;
import com.onlinebookstore.dto.Order;
import com.onlinebookstore.dto.Payment;
import com.onlinebookstore.exception.OrderException;
import com.onlinebookstore.exception.PaymentException;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	OrderService orderService;

	@Override
	public Payment createPayment(Payment payment) throws PaymentException, OrderException {
		Integer orderId = payment.getOrder().getOrderId();
		Order order = orderService.getOrderById(orderId);
		if (order.getOrderStatus().equalsIgnoreCase("Order placed Successfully")) {

			throw new PaymentException("Payment has already done for the given order");

		}
		Payment newPayment = paymentRepository.save(payment);
		order.setPayment(payment);
		order.setOrderStatus("Order placed Successfully");
		newPayment.setOrder(order);
		orderService.updateOrder(order);
		return newPayment;
	}

	@Override
	public Payment getPaymentById(Integer paymentId) throws PaymentException {
		Optional<Payment> foundPayment = paymentRepository.findById(paymentId);

		if (foundPayment.isEmpty()) {
			throw new PaymentException("Payment doesn't exists for Id : " + paymentId);

		}

		return foundPayment.get();
	}
}
