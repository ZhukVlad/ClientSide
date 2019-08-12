package comarch.mediation.ClientSide.handlers;

import io.undertow.server.HttpHandler;

public class CustomHandlers {
	public static TimingHttpHandler timed(String name, HttpHandler next) {
		return new TimingHttpHandler(next, "routes." + name);
	}
}
