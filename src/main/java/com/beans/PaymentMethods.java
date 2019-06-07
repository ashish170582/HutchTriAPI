/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

/**
 *
 * @author Rajat.kumar
 */
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root")
public class PaymentMethods extends Root {
	private List<PaymentMethod> paymentMethods;

	public PaymentMethods() {
		super(0, "Success");
	}

	public PaymentMethods(List<PaymentMethod> paymentMethods) {
		super(0, "Success");
		this.paymentMethods = paymentMethods;
	}

	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

}
