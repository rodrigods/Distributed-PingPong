package ping;
import br.edu.ufcg.lsd.commune.ServerModule;
import br.edu.ufcg.lsd.commune.context.ModuleContext;
import br.edu.ufcg.lsd.commune.network.xmpp.CommuneNetworkException;
import br.edu.ufcg.lsd.commune.processor.ProcessorStartException;


public class PingModule extends ServerModule {
	
	public PingModule(String containerName, ModuleContext context)
			throws CommuneNetworkException, ProcessorStartException {
		
		super(containerName, context);
	}
}
