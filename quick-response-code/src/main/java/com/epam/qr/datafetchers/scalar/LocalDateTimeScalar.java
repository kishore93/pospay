package com.epam.qr.datafetchers.scalar;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;

import org.jetbrains.annotations.NotNull;

import com.netflix.graphql.dgs.DgsScalar;

import graphql.GraphQLContext;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

@DgsScalar(name = "LocalDateTime")
public class LocalDateTimeScalar implements Coercing<LocalDateTime, Long> {

	@Override
	public Long serialize(@NotNull Object dataFetcherResult, @NotNull GraphQLContext graphQLContext,
			@NotNull Locale locale) throws CoercingSerializeException {
		return ((LocalDateTime) dataFetcherResult)
				.atZone(ZoneId.systemDefault())
				.toInstant()
				.toEpochMilli();
	}

	@Override
	public LocalDateTime parseValue(@NotNull Object input, @NotNull GraphQLContext graphQLContext,
			@NotNull Locale locale) throws CoercingParseValueException {
		throw new CoercingParseValueException("Unsupported Operation");
	}
}
