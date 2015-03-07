package parser;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**Regex searches for a pattern for a given string using regular expression. 
 * 
 * @author Sierra, Yancheng, Duvall
 *
 */
public class Regex {
	private List<Map.Entry<String, Pattern>> mySyntaxPatterns;
	private List<Map.Entry<String, Pattern>> myLanguagePatterns;

	/**
	 * Constructor for Regex.
	 * @param syntax String
	 * @param language String
	 */
	public Regex(String syntax, String language) {
		mySyntaxPatterns = makePatterns(syntax);
		myLanguagePatterns = makePatterns(language);
	}

	/**
	 * Method match.
	 * @param input String
	 * @param regex Pattern
	 * @return boolean
	 */
	public boolean match(String input, Pattern regex) {
		return regex.matcher(input).matches();
	}

	/**
	 * Method changeLanguagePattern.
	 * @param language String
	 */
	public void changeLanguagePattern(String language){
		myLanguagePatterns = makePatterns(language);
	}
	
	/**
	 * Method matchSyntax.
	 * @param toMatch String
	 * @return String
	 */
	public String matchSyntax(String toMatch){
		return findMatch(toMatch, mySyntaxPatterns);
	}
	
	/**
	 * Method matchCommand.
	 * @param toMatch String
	 * @return String
	 */
	public String matchCommand(String toMatch){
		return findMatch(toMatch, myLanguagePatterns);
	}
	
	/**
	 * Method findMatch.
	 * @param test String
	 * @param patterns List<Map.Entry<String,Pattern>>
	 * @return String
	 */
	private String findMatch(String test,
			List<Map.Entry<String, Pattern>> patterns) {
		String result = null;
		boolean matched = false;
		for (Map.Entry<String, Pattern> p : patterns) {
			if (match(test, p.getValue())) {
				result = p.getKey();
				System.out.println(String.format("%s matches %s", test,
						p.getKey()));
				matched = true;
				break;
			}
		}
		if (!matched) {
			System.out.println(String.format("%s not matched", test));
		}
		return result;
	}

	/**
	 * Method makePatterns.
	 * @param syntax String
	 * @return List<Map.Entry<String,Pattern>>
	 */
	public List<Map.Entry<String, Pattern>> makePatterns(String syntax) {
		ResourceBundle resources = ResourceBundle.getBundle(syntax);
		List<Map.Entry<String, Pattern>> patterns = new ArrayList<>();
		Enumeration<String> iter = resources.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			String regex = resources.getString(key);
			patterns.add(new AbstractMap.SimpleEntry<String, Pattern>(key,
			// THIS IS THE KEY LINE
					Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
		}
		return patterns;
	}

}
