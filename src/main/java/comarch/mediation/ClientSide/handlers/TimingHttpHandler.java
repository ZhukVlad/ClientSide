package comarch.mediation.ClientSide.handlers;

import com.codahale.metrics.Timer;
import com.codahale.metrics.Timer.Context;

import comarch.mediation.ClientSide.util.Metrics;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

public class TimingHttpHandler implements HttpHandler {
	private final HttpHandler handler;
	private final Timer timer;
	
	public TimingHttpHandler(HttpHandler handler, String name) {
        super();
        this.handler = handler;
        this.timer = Metrics.timer(name);
    }
	
	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		Context context = timer.time();
        try {
            handler.handleRequest(exchange);
        } finally {
            context.close();
        }
	}
}
