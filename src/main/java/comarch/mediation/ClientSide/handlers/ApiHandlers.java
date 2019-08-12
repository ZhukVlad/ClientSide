package comarch.mediation.ClientSide.handlers;

import comarch.mediation.ClientSide.core.Exchange;
import io.undertow.server.HttpServerExchange;

public class ApiHandlers {
    public static void badRequest(HttpServerExchange exchange, String message) {
        ApiError error = new ApiError(400, message);
        exchange.setStatusCode(error.getStatusCode());
        Exchange.body().sendJson(exchange, error);
    }
    
    private static class ApiError {
        private final int statusCode;
        private final String message;
        public ApiError(int statusCode, String message) {
            super();
            this.statusCode = statusCode;
            this.message = message;
        }
        public int getStatusCode() {
            return statusCode;
        }
        public String getMessage() {
            return message;
        }
    }
}
