package comarch.mediation.ClientSide.core.interfaces;

import java.nio.ByteBuffer;

import comarch.mediation.ClientSide.core.Json;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public interface JsonSender {
    default void sendJson(HttpServerExchange exchange, Object obj) {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.getResponseSender().send(ByteBuffer.wrap(Json.serializer().toByteArray(obj)));
    }
}
