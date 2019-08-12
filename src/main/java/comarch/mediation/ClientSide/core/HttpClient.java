package comarch.mediation.ClientSide.core;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import comarch.mediation.ClientSide.util.LoggerUtil;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;


public class HttpClient {
	
	private static final HttpLoggingInterceptor loggingInterceptor =
	        new HttpLoggingInterceptor((msg) -> {
	            LoggerUtil.debug(msg);
	        });
	    static {
	    	loggingInterceptor.setLevel(Level.BODY);
	    }

	    public static HttpLoggingInterceptor getLoggingInterceptor() {
	        return loggingInterceptor;
	    }
	
	private static final OkHttpClient client;
    static {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequestsPerHost(15);

        client = new OkHttpClient().newBuilder()
            .connectTimeout(2, TimeUnit.SECONDS)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .dispatcher(dispatcher)
            .addNetworkInterceptor(loggingInterceptor)
            .build();
    }
	
	public static OkHttpClient globalClient() {
        return client;
    }
	
	public static RuntimeException unknownException(Response response) throws IOException {
        return new RuntimeException(String.format("code: %s, body: %s", response.code(), response.body().string()));
    }
}
