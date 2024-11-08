import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

public class test {
public static void main(String[] args) {
	
	String file = "src\\dataset.csv";
	String file2 = "src\\stop.txt";
	BufferedReader reader =null;
	BufferedReader remover =null;
	String line;    
	String rmline;
	LinkedList<String> words = new LinkedList<String>();
	
	
	try {
		
		reader = new BufferedReader(new FileReader(file));
		remover = new BufferedReader(new FileReader(file2));
		
		line = reader.readLine();
		
		
		
		for (int i=0;i<50;i++){
			
		line = reader.readLine().substring(2);
		String temp[] = line.split(" ");
		
		
		
		for (String index :temp) 
		{	
			index = index.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
			System.out.println(index);
		}
		
		
		}
	

		
		
}catch(Exception e) {
		
		
		
	}
	
}
}
