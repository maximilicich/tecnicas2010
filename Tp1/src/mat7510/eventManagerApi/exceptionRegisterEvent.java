/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.eventManagerApi;

public class exceptionRegisterEvent  extends Exception{

	private static final long serialVersionUID = 1L;
	private String text;
	public exceptionRegisterEvent(String e) {
		text = "No se puede registrar los eventos debido a: " + e;
	}

	@Override
	public String toString() {
		return text;
	}
}