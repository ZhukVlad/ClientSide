package comarch.mediation.ClientSide.util;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

public class Metrics {
	private static final MetricRegistry registry;

	static {
		registry = new MetricRegistry();
	}

	public static Timer timer(String first, String... keys) {
		return registry.timer(MetricRegistry.name(first, keys));
	}
}
