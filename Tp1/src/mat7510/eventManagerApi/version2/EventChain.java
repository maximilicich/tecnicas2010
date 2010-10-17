package mat7510.eventManagerApi.version2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Grupo 10 
 *
 */
public abstract class EventChain implements EventListener {

	private EventContext context;
	private List<Element> chain = new ArrayList<Element>();

	public void setContext(EventContext cxt) {
		this.context = cxt;
	}

	public EventContext getContext() {
		return context;
	}
	
	public void addEvent(Event e) {
		chain.add(new Element(e));
	}
	
	public Iterator<Element> iterator() {
		return chain.iterator();
	}


	public void resetOccurrences() {
		for (Iterator<Element> iterator = this.iterator(); iterator.hasNext();) {
			Element element = iterator.next();
			element.setOccurred(false);
		}
	}

	public Boolean isAccomplished() {
		Boolean accomplished = true;
		for (Iterator<Element> iterator = this.iterator(); iterator.hasNext();) {
			Element element = iterator.next();
			accomplished = accomplished && element.hasOccurred();
		}
		return accomplished;
	}
	
	protected void setChain(List<Element> chain) {
		this.chain = chain;
	}

	protected List<Element> getChain() {
		return this.chain;
	}
	
	
	public class Element {
		private Event event;
		private Boolean occurred;
		public Element(Event event) {
			this.event = event;
			this.setOccurred(false);
		}
		public Event getEvent() {
			return event;
		}
		public void setOccurred(Boolean occurred) {
			this.occurred = occurred;
		}
		public Boolean hasOccurred() {
			return occurred;
		}
		
	}
}
