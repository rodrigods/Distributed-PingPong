package pong;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import br.edu.ufcg.lsd.commune.context.DefaultContextFactory;
import br.edu.ufcg.lsd.commune.context.ModuleContext;
import br.edu.ufcg.lsd.commune.context.PropertiesParser;
import br.edu.ufcg.lsd.commune.network.certification.CertificationProperties;
import br.edu.ufcg.lsd.commune.network.certification.providers.FileCertificationDataProvider;
import br.edu.ufcg.lsd.commune.network.certification.providers.FileCertificationProperties;
import br.edu.ufcg.lsd.commune.network.xmpp.XMPPProperties;


public class Main {
	
	private static PongModule pongModule;
	private static ModuleContext pongContext;
	
	
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
//		pongContext = createModuleContext("pong", "xmpp.ourgrid.org");
		pongContext = createModuleContext("pong", "dori.lsd.ufcg.edu.br");
		
		pongModule = new PongModule("PONG", pongContext);	
		
		Thread.sleep(15000);
		System.out.println("PONG - Connected!");
	}
	
	public static void deployServices() {
		pongModule.deploy("PONG", new PongImpl());
	}
	
	public static void main(String[] args) throws Exception {
		createModules();
		deployServices();
	}
}
