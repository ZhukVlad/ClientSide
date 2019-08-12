package comarch.mediation.ClientSide.service;

import comarch.mediation.ClientSide.core.Exchange;
import comarch.mediation.ClientSide.handlers.ApiHandlers;
import comarch.mediation.ClientSide.model.Alarm;
import comarch.mediation.ClientSide.util.LoggerUtil;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.StatusCodes;

public class AlarmRoutes {
	
	public static void logAlarm(HttpServerExchange exchange) {
		Alarm alarm = Exchange.body().parseJson(exchange, Alarm.typeRef());
        if (null == alarm) {
            ApiHandlers.badRequest(exchange, "Bad alarm");
            return;
        }
        LoggerUtil.debug("Received new alarm: {}", alarm.toString());
        exchange.setStatusCode(StatusCodes.OK);
        Exchange.body().sendText(exchange, "OK");
	}	
}
