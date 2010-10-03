package mat7510.eventManagerApi.test;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import mat7510.eventManagerApi.Event;
import mat7510.eventManagerApi.domainExamples.basicDomain.BasicEvent;

import org.junit.Test;

public class LinkedHashMapTest {

	@Test
	public void testHashMap () {
		
		Map<Event, Boolean> hash = new LinkedHashMap<Event, Boolean>();
		hash.put(new BasicEvent("evento A"), true);
		hash.put(new BasicEvent("evento B"), false);
		hash.put(new BasicEvent("evento C"), true);
		hash.put(new BasicEvent("evento D"), false);
		hash.put(new BasicEvent("evento E"), false);
		hash.put(new BasicEvent("evento F"), true);
		hash.put(new BasicEvent("evento G"), false);
		hash.put(new BasicEvent("evento H"), true);
		hash.put(new BasicEvent("evento I"), true);
		hash.put(new BasicEvent("evento J"), false);
		hash.put(new BasicEvent("evento K"), false);
		hash.put(new BasicEvent("evento L"), false);
		hash.put(new BasicEvent("evento M"), false);
		hash.put(new BasicEvent("evento N"), false);
		hash.put(new BasicEvent("evento O"), false);
		hash.put(new BasicEvent("evento P"), false);
		hash.put(new BasicEvent("evento Q"), false);
		hash.put(new BasicEvent("evento R"), false);
		hash.put(new BasicEvent("evento S"), false);
		hash.put(new BasicEvent("evento T"), false);
		hash.put(new BasicEvent("evento V"), false);
		hash.put(new BasicEvent("evento W"), false);
		hash.put(new BasicEvent("evento X"), false);
		hash.put(new BasicEvent("evento Y"), false);
		hash.put(new BasicEvent("evento Z"), false);
		
		for (Iterator<Map.Entry<Event, Boolean>> it = hash.entrySet().iterator() ; it.hasNext();) {
			Map.Entry<Event, Boolean> pairs = it.next();
			System.out.println(pairs.getKey().toString());		
		}
	}
	
}
