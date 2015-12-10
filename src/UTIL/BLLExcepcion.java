package UTIL;

@SuppressWarnings("serial")
public class BLLExcepcion extends Exception {

	public BLLExcepcion(String message) {
		super(message);
	}

	public BLLExcepcion(Exception e) {
		super(e.getMessage());
	}

}
