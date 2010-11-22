package mat7510.playground.xml.dom.apps;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import mat7510.playground.xml.dom.DOMUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class DOMtoXMLExample {

	public static void main(String[] args) throws Exception {

		(new DOMtoXMLExample()).execute();

	}


	void execute() throws Exception {

		Document dom = DOMUtils.createDocument();

		//create the root element 
		Element rootElement = dom.createElement("Books");
		dom.appendChild(rootElement);

		Book book1 = new Book("Head First Design Patterns", "Programming", "Katy Sierra et al.");
		Book book2 = new Book("Harry Potter and the Filosopher Stone", "Adventures", "J K Rowlings");
		Book book3 = new Book("The Bible", "Spiritual", "Anonymous");

		rootElement.appendChild(createBookElement(dom, book1));
		rootElement.appendChild(createBookElement(dom, book2));
		rootElement.appendChild(createBookElement(dom, book3));

		// Use a Transformer for output
		TransformerFactory tFactory =
			TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();

		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		StreamResult resultToFile = new StreamResult("C:/temp/books30.xml");
		StreamResult result = new StreamResult(System.out);
		
		DOMSource source = new DOMSource(dom);

		
		transformer.transform(source, result);
		
		transformer.transform(source, resultToFile);
		
		


	}


	/**
	 * Helper method which creates a XML element 
	 * @param b The book for which we need to create an xml representation
	 * @return XML element snippet representing a book
	 */
	private Element createBookElement(Document dom, Book b){

		Element bookEle = dom.createElement("Book");
		bookEle.setAttribute("Subject", b.getSubject());

		//create author element and author text node and attach it to bookElement
		Element authEle = dom.createElement("Author");
		Text authText = dom.createTextNode(b.getAuthor());
		authEle.appendChild(authText);
		bookEle.appendChild(authEle);

		//create title element and title text node and attach it to bookElement
		Element titleEle = dom.createElement("Title");
		Text titleText = dom.createTextNode(b.getTitle());
		titleEle.appendChild(titleText);
		bookEle.appendChild(titleEle);

		return bookEle;

	}



}


class Book {

	public Book(String title, String subject, String author) {
		this.title = title;
		this.subject = subject;
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getSubject() {
		return subject;
	}

	private String title;
	private String subject;
	private String author;
}
