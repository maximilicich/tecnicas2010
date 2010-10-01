package mat7510.eventManager;

import mat7510.eventManagerApi.EventListener;

public class MedidorDePresionDeAgua {
	
	private EventListener eventListener;

	public void addEventListener(EventListener eventListener) {
		this.eventListener = eventListener;
	}
	
	// Aca deberian haber metodos que en algun momento
	// detecten que Hay Presion suficiente o no haya presion
	// y disparen los eventos correspondientes al Listener
	
}
