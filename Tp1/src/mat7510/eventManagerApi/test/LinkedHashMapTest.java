package mat7510.eventManagerApi.test;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import mat7510.eventManagerApi.Event;

import org.junit.Test;

class SimpleEvent implements Event {

	private String eventName;

	public SimpleEvent(String eventName) {
		this.eventName = eventName;
	}
	
	public String getEventName() {
		return this.eventName;
	}
	
	public boolean equals(Event anotherEvent) {
		// Asumimos que todo evento de esta clase es el mismo
		if (!(anotherEvent instanceof BasicEvent)) {
			return false;
		}
		return ((SimpleEvent)anotherEvent).eventName.equals(this.eventName);
	}
	
	@Override
	public String toString() {
		return this.eventName;
	}
	
}

class SimpleEvent2 implements Event {

	@Override
	public boolean equals(Event anotherEvent) {
		// Asumimos que todo evento de esta clase es el mismo
		if (!(anotherEvent instanceof BasicEvent)) {
			return false;
		}
		return true;
	}
	
}


public class LinkedHashMapTest {

	@Test
	public void testHashMap () {
		
		Map<Event, Boolean> hash = new LinkedHashMap<Event, Boolean>();
		hash.put(new SimpleEvent("evento A"), true);
		hash.put(new SimpleEvent("evento B"), false);
		hash.put(new SimpleEvent("evento C"), true);
		hash.put(new SimpleEvent("evento D"), false);
		hash.put(new SimpleEvent("evento E"), false);
		hash.put(new SimpleEvent("evento F"), true);
		hash.put(new SimpleEvent("evento G"), false);
		hash.put(new SimpleEvent("evento H"), true);
		hash.put(new SimpleEvent("evento I"), true);
		hash.put(new SimpleEvent("evento J"), false);
		hash.put(new SimpleEvent("evento K"), false);
		hash.put(new SimpleEvent("evento L"), false);
		hash.put(new SimpleEvent("evento M"), false);
		hash.put(new SimpleEvent("evento N"), false);
		hash.put(new SimpleEvent("evento O"), false);
		hash.put(new SimpleEvent("evento P"), false);
		hash.put(new SimpleEvent("evento Q"), false);
		hash.put(new SimpleEvent("evento R"), false);
		hash.put(new SimpleEvent("evento S"), false);
		hash.put(new SimpleEvent("evento T"), false);
		hash.put(new SimpleEvent("evento V"), false);
		hash.put(new SimpleEvent("evento W"), false);
		hash.put(new SimpleEvent("evento X"), false);
		hash.put(new SimpleEvent("evento Y"), false);
		hash.put(new SimpleEvent("evento Z"), false);
		
		for (Iterator<Map.Entry<Event, Boolean>> it = hash.entrySet().iterator() ; it.hasNext();) {
			Map.Entry<Event, Boolean> pairs = it.next();
			System.out.println(pairs.getKey().toString());		
		}
	}
	
}
