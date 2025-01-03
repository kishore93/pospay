package com.epam.qr.model;

import static java.util.Arrays.stream;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PayeeInfo {

	public static final String KEY_VALUE_PAIR_REP = "%s=%s";
	public static final String KEY_VALUE_PAIR_JOINER = "&";

	@ToStringAlias("pa")
	private final String payeeVpa;

	@ToStringAlias("pn")
	private final String payeeName;

	@ToStringAlias("mc")
	private final String merchantCode;

	@ToStringAlias("cu")
	private final String currency;

	@Override
	public String toString() {
		return "UPI://pay?" + getAllSerializableFields()
				.entrySet()
				.stream()
				.map(entry -> KEY_VALUE_PAIR_REP.formatted(entry.getKey(), entry.getValue()))
				.collect(Collectors.joining(KEY_VALUE_PAIR_JOINER));
	}

	private Map<String, String> getAllSerializableFields() {
		return stream(PayeeInfo.class.getDeclaredFields())
				.filter(field -> field.isAnnotationPresent(ToStringAlias.class))
				.collect(Collectors.toMap(this::getFieldAlias, this::getFieldValue));
	}

	private String getFieldAlias(Field field) {
		var toStringAlias = field.getAnnotation(ToStringAlias.class);
		return toStringAlias.value();
	}

	private String getFieldValue(Field field) {
		try {
			field.setAccessible(true);
			return field.get(this).toString();
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
