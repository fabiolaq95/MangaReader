package utilidades;

import java.security.MessageDigest;
import java.util.Base64;

public class md5hasher {
	
	MessageDigest md = null;
	
	private md5hasher() {}
	
	//Return Base64 MD5 hashed string.
	public String hashString(String original) {
		byte[] result = null;
		try {
			md = MessageDigest.getInstance("MD5");
			result = md.digest(original.getBytes("UTF-8"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return Base64.getEncoder().encodeToString(result);
	}
	
	private static md5hasher mh = new md5hasher();
	
	public static md5hasher getInstance() {
		return mh;
	}
}
