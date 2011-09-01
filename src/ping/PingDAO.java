package ping;

import pong.Pong;

public class PingDAO {
	private Pong pong;
	
	
	private static PingDAO instance;
	
	
	public static PingDAO getInstance() {
		if (instance == null) {
			instance = new PingDAO();
		}
		
		return instance;
	}
	
	
	private PingDAO() {}

	
	public void setPong(Pong pong) {
		this.pong = pong;
	}

	public Pong getPong() {
		return pong;
	}
}
