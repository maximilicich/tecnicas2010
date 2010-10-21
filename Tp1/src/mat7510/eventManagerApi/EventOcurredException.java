package mat7510.eventManagerApi;

/**
 *
 * @author sergio
 */
public class EventOcurredException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String text;

	public EventOcurredException() {
		super("Ocurrio un evento inesperado");
	}

	@Override
	public String toString() {
		return text;
	}
}

