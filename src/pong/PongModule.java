package pong;
import br.edu.ufcg.lsd.commune.ServerModule;
import br.edu.ufcg.lsd.commune.context.ModuleContext;
import br.edu.ufcg.lsd.commune.network.xmpp.CommuneNetworkException;
import br.edu.ufcg.lsd.commune.processor.ProcessorStartException;


public class PongModule extends ServerModule {
	
	public PongModule(String containerName, ModuleContext context)
			throws CommuneNetworkException, ProcessorStartException {
		
		super(containerName, context);
	}
}
