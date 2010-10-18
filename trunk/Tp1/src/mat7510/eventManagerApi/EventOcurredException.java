/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.eventManagerApi;

/**
 *
 * @author sergio
 */
public class EventOcurredException extends RuntimeException{

	private String text;
	public EventOcurredException() {
		text = "Ocurrio un evento inesperado";
                throw new RuntimeException(text);
	}

	@Override
	public String toString() {
		return text;
	}
}

