package mat7510.playground.xml.dom;

import java.io.IOException;

import mat7510.playground.xml.dom.NodeProcessor;
import mat7510.playground.xml.dom.PropertyPrinter;

import org.w3c.dom.Node;

public class PrinterNodeProcessor implements NodeProcessor {

	private PropertyPrinter printer;
	
	public PrinterNodeProcessor() {
		printer = new PropertyPrinter();
	}
	
	@Override
	public void process(Node node) throws IOException {
		printer.writeNode(node);
	}

}
