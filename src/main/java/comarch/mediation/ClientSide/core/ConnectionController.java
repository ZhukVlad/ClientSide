package comarch.mediation.ClientSide.core;

import static comarch.mediation.ClientSide.util.ConfigUtil.isConnected;

import java.util.TimerTask;

import comarch.mediation.ClientSide.main.RestClient;
import comarch.mediation.ClientSide.util.LoggerUtil;

public class ConnectionController extends TimerTask {
	
	private RestClient restClient;
	
	public ConnectionController(RestClient restClient) {
		this.restClient = restClient;
	} 

	@Override 
	public void run() {
		try {
			if (restClient.checkStatus() == "OK" && Boolean.FALSE.equals(isConnected)) {
				isConnected = Boolean.TRUE;
				restClient.subscribe();
			} else if (restClient.checkStatus() == "OK" && Boolean.TRUE.equals(isConnected)) {
				isConnected = Boolean.TRUE;
			} else {
				isConnected = Boolean.FALSE;
			}
		} catch (RuntimeException e) {
			isConnected = Boolean.FALSE;
			LoggerUtil.error("Check status failed", e);
		}
	}
}
