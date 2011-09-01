package ping;
import br.edu.ufcg.lsd.commune.api.Remote;

@Remote
public interface Ping {
	void pong(int number);
}
