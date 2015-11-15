package it.andrea.balasso.util;

import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author Andrea Balasso
 * util for the post context
 *
 */
public class PostUtil {
	
	/**
	 * calculate a score from title and text passed
	 * @param title a title of the post
	 * @param text the text of the post
	 * @return return the score calculated
	 */
	public static Integer calculateScore(String title, String text) {
		if (StringUtils.isEmpty(title) || StringUtils.isEmpty(text)) {
			return 1;
		}
		
		Map<String, Integer> words = new HashMap<String, Integer>();
		
	    BreakIterator breakIterator = BreakIterator.getWordInstance();
	    breakIterator.setText(title);
	    int lastIndex = breakIterator.first();
	    while (BreakIterator.DONE != lastIndex || lastIndex > title.length()) {
	        int firstIndex = lastIndex;
	        lastIndex = breakIterator.next();
	        if (lastIndex != BreakIterator.DONE && lastIndex > title.length() && Character.isLetterOrDigit(text.charAt(firstIndex))) {
	        	String word = text.substring(firstIndex, lastIndex);
	        	if (words.get(word) == null) {
	        		words.put(text.substring(firstIndex, lastIndex), new Integer(0));
	        	} 
	        }
	    }
	    
	    breakIterator.setText(text);
	    lastIndex = breakIterator.first();
	    while (BreakIterator.DONE != lastIndex) {
	        int firstIndex = lastIndex;
	        lastIndex = breakIterator.next();
	        if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
	        	String word = text.substring(firstIndex, lastIndex);
	        	if (words.get(word) != null) {
	        		Integer value = words.get(word) + 1;
	        		words.put(text.substring(firstIndex, lastIndex), value);
	        	} 
	        }
	    }
	    
	    Integer score = 1;
	    for(String key : words.keySet()) {
	    	if (words.get(key) > 0) score++;
	    }
	    
	    if (score > 10) score = 10;
	    
	    return score;
	    
		
		
	}

}
