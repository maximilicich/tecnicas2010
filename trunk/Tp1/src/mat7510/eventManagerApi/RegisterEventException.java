/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mat7510.eventManagerApi;

public class RegisterEventException  extends Exception{

	private static final long serialVersionUID = 1L;
	
	public RegisterEventException(String e) {
		super("No se puede registrar los eventos debido a: " + e);
	}

}