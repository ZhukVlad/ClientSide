package comarch.mediation.ClientSide.main;

import static comarch.mediation.ClientSide.handlers.CustomHandlers.timed;
import static comarch.mediation.ClientSide.util.ConfigUtil.CONNECTION_CHECK;

import java.util.List;
import java.util.Map;
import java.util.Timer;

import org.jooq.lambda.Unchecked;

import com.fasterxml.jackson.core.type.TypeReference;

import comarch.mediation.ClientSide.core.ConnectionController;
import comarch.mediation.ClientSide.core.HttpClient;
import comarch.mediation.ClientSide.core.Json;
import comarch.mediation.ClientSide.core.MiddlewareBuilder;
import comarch.mediation.ClientSide.core.SimpleServer;
import comarch.mediation.ClientSide.model.Alarm;
import comarch.mediation.ClientSide.service.AlarmRoutes;
import comarch.mediation.ClientSide.util.ConfigUtil;
import comarch.mediation.ClientSide.util.LoggerUtil;
import io.undertow.server.HttpHandler;
import io.undertow.server.RoutingHandler;
import io.undertow.server.handlers.BlockingHandler;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestClient {
	private final String host;
	private final OkHttpClient client;
	private static SimpleServer server;
	
	public RestClient(String host, OkHttpClient client) {
        super();
        this.host = host;
        this.client = client;
    }
	
	public List<Alarm> listAlarms() {
        HttpUrl route = HttpUrl.parse(host + "/alarms");
        Request request = new Request.Builder().url(route).get().build();
        return Unchecked.supplier(() -> {
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    List<Alarm> alarms = Json.serializer().fromInputStream(response.body().byteStream(), Alarm.listTypeRef());
                    return alarms;
                }
                throw HttpClient.unknownException(response);
            }
        }).get();
    }
	
	public String subscribe() {
		HttpUrl route = HttpUrl.parse(host + "/subscription");
		Request request = new Request.Builder()
				.url(route)
				.post(RequestBody.create(MediaType.parse("application/json"), Json.serializer().toString(server.getUrl())))
				.build();
		return Unchecked.supplier(() -> {
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    String result = Json.serializer().fromInputStream(response.body().byteStream(), new TypeReference<String>() {
					});
                    return result;
                }
                throw HttpClient.unknownException(response);
            }
            
        }).get();
	}
	
	public String checkStatus() {
		HttpUrl route = HttpUrl.parse(host + "/status");
		Request request = new Request.Builder().url(route).get().build();
		return Unchecked.supplier(() -> {
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    return "OK";
                }
                throw HttpClient.unknownException(response);
            }
            
        }).get();
	}
	
	public static final HttpHandler ROUTES = new RoutingHandler()
			.post("/alarm", timed("logAlarm", AlarmRoutes::logAlarm));

    private static HttpHandler wrapWithMiddleware(HttpHandler handler) {
        return MiddlewareBuilder.begin(BlockingHandler::new)
                                .complete(handler);
    }

	public static void main(String [] args) {
		server = SimpleServer.simpleServer(wrapWithMiddleware(ROUTES));
		server.start();
		LoggerUtil.info("Client server started.");
		
		RestClient client = new RestClient("http://localhost:8081", HttpClient.globalClient());
		
        LoggerUtil.debug("**** Listing Users ****");
        List<Alarm> alarms = client.listAlarms();
        LoggerUtil.debug(Json.serializer().toString(alarms));
        
        LoggerUtil.debug("**** Subscribe ****");
        client.subscribe();
        
		Map<String, Object> config = new ConfigUtil().getConfig(ConfigUtil.MEDIATION_FILE);
		Timer timer = new Timer(true);
		timer.schedule(new ConnectionController(client), 0, Long.parseLong(config.get(CONNECTION_CHECK).toString()));
	}
}
