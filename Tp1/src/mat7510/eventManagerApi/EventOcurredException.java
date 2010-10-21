package mat7510.eventManagerApi;

/**
 *
 * @author sergio
 */
public class EventOcurredException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EventOcurredException() {
		super("Ocurrio un evento inesperado");
	}

}

