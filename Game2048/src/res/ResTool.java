package res;

import java.net.URL;

public class ResTool {
	
	public static URL getImage(String picName)
	  {
	    return ResTool.class.getResource(picName);
	  }
	
	
	

}
