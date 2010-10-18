/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.eventManagerApi;

/**
 *
 * @author sergio
 */
public class eventOcurredException extends Exception{

	private String text;
	public eventOcurredException() {
		text = "Ocurrio un evento inesperado";
	}

	@Override
	public String toString() {
		return text;
	}
}