package com.epam.qr.model;

import lombok.Getter;

@Getter
public class PaymentInfo extends PayeeInfo {

	@ToStringAlias("tid")
	private final String transactionId;

	@ToStringAlias("tr")
	private final String transactionRefNumber;

	@ToStringAlias("tn")
	private final String note;

	@ToStringAlias("am")
	private final double amount;

	public PaymentInfo(String payeeVpa, String payeeName, String merchantCode, String currency, String transactionId,
			String transactionRefNumber, String note, double amount) {
		super(payeeVpa, payeeName, merchantCode, currency);
		this.transactionId = transactionId;
		this.transactionRefNumber = transactionRefNumber;
		this.note = note;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}