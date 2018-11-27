package utilidades;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	
	private String filename = "";
	private Properties p;
	
	public PropertiesReader(String filename) {
		this.filename = filename;
		this.p = new Properties();
		try {
			String directorioRaiz = "C:\\Users\\FABIOLA\\Documents\\11voTrimestreFab\\Develop\\apache-tomcat-9.0.8\\webapps\\MangaReaderRA\\src\\utilidades";
			String path = directorioRaiz + "\\" + this.filename;
			//System.out.println(path);
			p.load(new FileReader(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String reading(String var) {
		String value= "";
		try {
			value = this.p.getProperty(var);
		} catch (Exception e) {
			return null;
		}
		return value;
	}
	
}