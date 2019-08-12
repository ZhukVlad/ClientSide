package comarch.mediation.ClientSide.core;

import comarch.mediation.ClientSide.util.LoggerUtil;
import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import io.undertow.server.HttpHandler;

public class SimpleServer {
	private static final int DEFAULT_PORT = 8082;
	private static final String DEFAULT_HOST = "0.0.0.0";
	
	private final Undertow.Builder undertowBuilder;
	
	private SimpleServer(Undertow.Builder undertow) {
        this.undertowBuilder = undertow;
    }
	
	 public Undertow.Builder getUndertow() {
	        return undertowBuilder;
	 }
	 
	 public Undertow start() {
		 Undertow undertow = undertowBuilder.build();
		 undertow.start();
		 
		 undertow.getListenerInfo().stream().forEach(listenerInfo -> LoggerUtil.info(listenerInfo.toString()));
		 
		 return undertow;
	 }
	 
	 public static SimpleServer simpleServer(HttpHandler handler) {
		 Undertow.Builder undertow = Undertow.builder()
				 .setServerOption(UndertowOptions.RECORD_REQUEST_START_TIME, true)
				 .addHttpListener(DEFAULT_PORT, DEFAULT_HOST, handler);
		 
		 return new SimpleServer(undertow);
	 }
	 
	 public String getUrl() {
		 return "http://" + DEFAULT_HOST + ":" + DEFAULT_PORT;
	 }
}
