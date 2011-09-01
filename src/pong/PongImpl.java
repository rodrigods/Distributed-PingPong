package pong;
import ping.Ping;
import br.edu.ufcg.lsd.commune.api.FailureNotification;
import br.edu.ufcg.lsd.commune.api.InvokeOnDeploy;
import br.edu.ufcg.lsd.commune.api.MonitoredBy;
import br.edu.ufcg.lsd.commune.api.RecoveryNotification;
import br.edu.ufcg.lsd.commune.container.logging.CommuneLogger;
import br.edu.ufcg.lsd.commune.container.servicemanager.ServiceManager;
import br.edu.ufcg.lsd.commune.identification.DeploymentID;


public class PongImpl implements Pong {
	
	private ServiceManager serviceManager;

	@InvokeOnDeploy
	public void init(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	@Override
	public void ping(@MonitoredBy("PONG") Ping ping, int number) {
		CommuneLogger logger = serviceManager.getLog();
		logger.debug(number + " - PING!");
		
		//answer the ping
		ping.pong(number);
	}
	
	@Override
	public void ping2(@MonitoredBy("PONG") Ping ping, int number) {
		CommuneLogger logger = serviceManager.getLog();
		logger.debug(number + " - PING!!");
	}
	
	@Override
	public void ping3(@MonitoredBy("PONG") Ping ping, int number) {
		CommuneLogger logger = serviceManager.getLog();
		logger.debug(number + " - PING!!!");
	}

	@RecoveryNotification
	public void pingRecovery(Ping ping, DeploymentID pingDeploymentID) {
		CommuneLogger logger = serviceManager.getLog();
		logger.debug("Ping recovery: " + pingDeploymentID);
	}
	
	@FailureNotification
	public void pingFailure(Ping ping, DeploymentID pingDeploymentID) {
		CommuneLogger logger = serviceManager.getLog();
		logger.debug("Ping failure: " + pingDeploymentID);
	}
}
