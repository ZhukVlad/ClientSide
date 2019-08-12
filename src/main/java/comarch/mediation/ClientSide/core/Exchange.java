package comarch.mediation.ClientSide.core;

import comarch.mediation.ClientSide.core.interfaces.ContentTypeSenders;
import comarch.mediation.ClientSide.core.interfaces.JsonParser;
import comarch.mediation.ClientSide.core.interfaces.JsonSender;

public class Exchange {
	public static interface BodyImpl extends ContentTypeSenders, JsonSender, JsonParser {
	};

	private static final BodyImpl BODY = new BodyImpl() {
	};

	public static BodyImpl body() {
		return BODY;
	}
}
