package comarch.mediation.ClientSide.core.interfaces;

import com.fasterxml.jackson.core.type.TypeReference;

import comarch.mediation.ClientSide.core.Json;
import io.undertow.server.HttpServerExchange;

public interface JsonParser {
	default <T> T parseJson(HttpServerExchange exchange, TypeReference<T> typeRef) {
        return Json.serializer().fromInputStream(exchange.getInputStream(), typeRef);
    }
}
