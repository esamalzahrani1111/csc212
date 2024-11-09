import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

public class test {
	public static void main(String[] args) {

		String file = "src\\dataset.csv";
		String file2 = "src\\stop.txt";

		BufferedReader reader = null;
		BufferedReader remover = null;

		String line;
		String rmline;
		Boolean tmp;
		LinkedList<String> words = new LinkedList<String>();

		try {

			reader = new BufferedReader(new FileReader(file));
			remover = new BufferedReader(new FileReader(file2));

			line = reader.readLine();

			// for (int i=0;i<50;i++){

			line = reader.readLine().substring(2);
			String temp[] = line.split(" ");

			tmp = false;

			for (String index : temp) {
				index = index.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

				while ((rmline = remover.readLine()) != null) {

					if (index.equalsIgnoreCase(rmline))
						tmp = true;
				}

				if (!tmp) {
					
					words.insert(index);
				}
				remover = new BufferedReader(new FileReader(file2));
				tmp = false;
				//test

			}

		} catch (Exception e) {

		}

	}
}
