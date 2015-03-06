package parser;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class Regex {
	private List<Map.Entry<String, Pattern>> mySyntaxPatterns;
	private List<Map.Entry<String, Pattern>> myLanguagePatterns;

	public Regex(String syntax, String language) {
		mySyntaxPatterns = makePatterns(syntax);
		myLanguagePatterns = makePatterns(language);
	}

	public boolean match(String input, Pattern regex) {
		return regex.matcher(input).matches();
	}

	public void changeLanguagePattern(String language){
		myLanguagePatterns = makePatterns(language);
	}
	
	public String matchSyntax(String toMatch){
		return findMatch(toMatch, mySyntaxPatterns);
	}
	
	public String matchCommand(String toMatch){
		return findMatch(toMatch, myLanguagePatterns);
	}
	
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
