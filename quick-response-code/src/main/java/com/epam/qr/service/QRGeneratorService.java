package com.epam.qr.service;

import static com.google.zxing.BarcodeFormat.QR_CODE;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.epam.qr.model.PayeeInfo;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QRGeneratorService {

	@Value("#{qrSizeConfig.readSize()}")
	private int size;

	public BitMatrix generateQuickResponseCode(PayeeInfo payeeInfo) {
		try {
			final var qrContents = payeeInfo.toString();
			return new QRCodeWriter().encode(qrContents, QR_CODE, size, size);
		} catch (WriterException e) {
			throw new RuntimeException(e);
		}
	}

}
