package com.epam.qr.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties("com.epam.qr-size")
@Component(QRCodeSizeConfiguration.BEAN_NAME)
@Data
public class QRCodeSizeConfiguration {

	public static final String BEAN_NAME = "qrSizeConfig";

	private enum Size { SMALL, MEDIUM, LARGE }

	private int small;
	private int medium;
	private int large;
	private Size defaultSize;

	public int readSize() {
		return switch (defaultSize) {
			case LARGE -> large;
			case MEDIUM -> medium;
			case SMALL -> small;
		};
	}

}
