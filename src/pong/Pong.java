package pong;
import ping.Ping;
import br.edu.ufcg.lsd.commune.api.Remote;

@Remote
public interface Pong {
	void ping(Ping ping, int number);
	void ping2(Ping ping, int number);
	void ping3(Ping ping, int number);
}
