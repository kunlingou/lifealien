package lifealien.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class pattern {
	public static void main(String[] args) {
		
	      String line = "问题时零";
	      String pattern = "([零一两二三四五六七八九十批台件套个吨斤把项平平方米千米米])";
	      Pattern r = Pattern.compile(pattern);
	      Matcher m = r.matcher(line);
	      boolean matched = Pattern.matches(pattern, line);
	      if(m.find()) {
	    	  System.out.println();
	      }
	}
}
