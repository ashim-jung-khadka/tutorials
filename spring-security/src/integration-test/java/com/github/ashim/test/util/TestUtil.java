package com.github.ashim.test.util;

import java.nio.charset.Charset;

import org.springframework.http.MediaType;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestUtil {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	private TestUtil() {
	}

	private static FieldNamingPolicy FIELD_NAMING_POLICY = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;

	public static Gson GSON = new GsonBuilder().setPrettyPrinting()
			.setFieldNamingPolicy(FIELD_NAMING_POLICY).create();

	public static final void setFIELD_NAMING_POLICY(
			FieldNamingPolicy FIELD_NAMING_POLICY) {
		GSON = new GsonBuilder().setPrettyPrinting()
				.setFieldNamingPolicy(FIELD_NAMING_POLICY).create();
	}

	public static <T> String toJSON(T t) {
		return GSON.toJson(t);
	}

	public static <T> T fromJSON(String responseString, Class<T> clazz) {
		T t = null;
		if (clazz.isAssignableFrom(responseString.getClass())) {
			t = clazz.cast(responseString);
		} else {
			t = GSON.fromJson(responseString, clazz);
		}
		return t;
	}
}
