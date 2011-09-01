package ping;
import pong.Pong;
import br.edu.ufcg.lsd.commune.api.FailureNotification;
import br.edu.ufcg.lsd.commune.api.InvokeOnDeploy;
import br.edu.ufcg.lsd.commune.api.RecoveryNotification;
import br.edu.ufcg.lsd.commune.container.logging.CommuneLogger;
import br.edu.ufcg.lsd.commune.container.servicemanager.ServiceManager;
import br.edu.ufcg.lsd.commune.identification.DeploymentID;


public class PingImpl implements Ping {
	
	private ServiceManager serviceManager;
	private final int HITS = 2000;
	

	@InvokeOnDeploy
	public void init(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	@Override
	public void pong(int number) {
		CommuneLogger logger = serviceManager.getLog();
		logger.debug(number + " - PONG!");
		
		if (number < HITS) {
			Pong pong = PingDAO.getInstance().getPong();
			
			pong.ping(this, ++number);
			pong.ping2(this, ++number);
			pong.ping3(this, ++number);
		}
	}


	@RecoveryNotification
	public void pongRecovery(Pong pong, DeploymentID pongDeploymentID) {
		CommuneLogger logger = serviceManager.getLog();
		logger.debug("Pong recovery: " + pongDeploymentID);
		
		PingDAO.getInstance().setPong(pong);
		pong.ping(this, 0);
	}
	
	@FailureNotification
	public void pongFailure(Pong pong, DeploymentID pongDeploymentID) {
		CommuneLogger logger = serviceManager.getLog();
		logger.debug("Pong failure: " + pongDeploymentID);
	}
}
