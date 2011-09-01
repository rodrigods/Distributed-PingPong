package ping;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import pong.Pong;
import br.edu.ufcg.lsd.commune.context.DefaultContextFactory;
import br.edu.ufcg.lsd.commune.context.ModuleContext;
import br.edu.ufcg.lsd.commune.context.PropertiesParser;
import br.edu.ufcg.lsd.commune.identification.ServiceID;
import br.edu.ufcg.lsd.commune.network.certification.CertificationProperties;
import br.edu.ufcg.lsd.commune.network.certification.providers.FileCertificationDataProvider;
import br.edu.ufcg.lsd.commune.network.certification.providers.FileCertificationProperties;
import br.edu.ufcg.lsd.commune.network.xmpp.XMPPProperties;
import br.edu.ufcg.lsd.commune.processor.interest.InterestRequirements;


public class Main {
	
	private static PingModule pingModule;
	private static ModuleContext pingContext;
	
	
	public static ModuleContext createModuleContext(String username, String server) {
		Map<String, String> properties = new HashMap<String,String>();
		properties.put(XMPPProperties.PROP_USERNAME, username);
		properties.put(XMPPProperties.PROP_XMPP_SERVERNAME, server);
		properties.put(CertificationProperties.PROP_CERT_PROVIDER_CLASS, 
				FileCertificationDataProvider.class.getName());
		properties.put(FileCertificationProperties.PROP_MYCERTIFICATE_FILEPATH, 
				"data/certification" + File.separator + "test" + username + ".cer");
		
		DefaultContextFactory factory = new DefaultContextFactory(new PropertiesParser(properties));
		
		return factory.createContext();
	}
	
	public static void createModules() throws Exception {
//		pingContext = createModuleContext("ping", "dori.lsd.ufcg.edu.br");
		pingContext = createModuleContext("ping", "li85-240.members.linode.com");
		
		pingModule = new PingModule("PING", pingContext);
		
		Thread.sleep(15000);
		System.out.println("PING - Connected!");
	}
	
	public static void deployServices() {
		pingModule.deploy("PING", new PingImpl());
	}
	
	public static void registerInterest(ServiceID pongServiceID) {
		pingModule.registerInterest("PING", Pong.class, pongServiceID, new InterestRequirements(pingContext));
	}
	
	public static void main(String[] args) throws Exception {
		createModules();
		deployServices();
		registerInterest(ServiceID.parse("pong@dori.lsd.ufcg.edu.br/PONG/PONG"));
	}
}
