/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.eventManagerApi;

/**
 *
 * @author sergio
 */
public class exceptionRegisterEvent  extends Exception{
	private String text;
	public exceptionRegisterEvent(String e) {
		text = "No se puede registrar los eventos debido a: " + e;
	}

        @Override
	public String toString() {
		return text;
	}
}