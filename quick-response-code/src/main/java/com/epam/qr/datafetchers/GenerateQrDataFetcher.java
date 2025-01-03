package com.epam.qr.datafetchers;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.Base64;

import javax.imageio.ImageIO;

import com.epam.qr.graphql.types.DynamicQuickResponse;
import com.epam.qr.graphql.types.PayeeInfoInput;
import com.epam.qr.model.PayeeInfo;
import com.epam.qr.service.QRGeneratorService;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@DgsComponent
@RequiredArgsConstructor
public class GenerateQrDataFetcher {

	public static final String FORMAT_NAME = "PNG";

	private final QRGeneratorService qrGeneratorService;

	@SneakyThrows
	@DgsQuery
	public DynamicQuickResponse generateQr(@InputArgument PayeeInfoInput payeeInfo, DataFetchingEnvironment dfe) {
		final var bitMatrix = qrGeneratorService.generateQuickResponseCode(from(payeeInfo));
		final var bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
		final var outputStream = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, FORMAT_NAME, outputStream);
		final var encodedData = Base64.getEncoder().encode(outputStream.toByteArray());
		final var expiresAt = LocalDateTime.now().plusMinutes(3);
		return DynamicQuickResponse.newBuilder()
				.data(new String(encodedData))
				.expiresAt(expiresAt)
				.build();
	}

	public PayeeInfo from(PayeeInfoInput payeeInfoInput) {
		return new PayeeInfo(
				payeeInfoInput.getPa(),
				payeeInfoInput.getPa(),
				payeeInfoInput.getMc(),
				payeeInfoInput.getCu()
		);
	}

}
