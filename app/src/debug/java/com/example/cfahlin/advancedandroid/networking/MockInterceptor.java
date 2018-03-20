package com.example.cfahlin.advancedandroid.networking;


import android.support.annotation.NonNull;

import com.example.cfahlin.advancedandroid.settings.DebugPreferences;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MockInterceptor implements Interceptor {


	private final MockResponseFactory mockResponseFactory;
	private final DebugPreferences debugPreferences;

	@Inject
	MockInterceptor(MockResponseFactory mockResponseFactory, DebugPreferences debugPreferences) {

		this.mockResponseFactory = mockResponseFactory;
		this.debugPreferences = debugPreferences;
	}

	@Override
	public Response intercept(@NonNull Chain chain) throws IOException {
		if(debugPreferences.useMockResponses()) {
			String mockResponses = mockResponseFactory.getMockResponse(chain.request());
			if(mockResponses != null) {
				return new Response.Builder()
						.message("")
						.protocol(Protocol.HTTP_1_1)
						.request(chain.request())
						.code(200)
						.body(ResponseBody.create(MediaType.parse("text/json"), mockResponses))
						.build();
			}
		}
		return chain.proceed(chain.request());
	}
}
