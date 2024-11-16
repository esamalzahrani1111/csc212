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

		indexLinkedList<Integer,String> normalIndex = new indexLinkedList<Integer,String>();
		invertedLinkedList<Integer> invertedIndex = new invertedLinkedList<>();
		BST<Integer> invertedBST = new BST<Integer>();
		

		try { // add read from file

			reader = new BufferedReader(new FileReader(file));
			remover = new BufferedReader(new FileReader(file2));

			line = reader.readLine();

			 for (int i=0;i<50;i++){

			line = reader.readLine().substring(2); //change to first occurance of ,
			String temp[] = line.split(" ");

			tmp = false;

			for (String index : temp) {
				index = index.replaceAll("[^a-zA-Z0-9]", "").toLowerCase(); //merge it with reading 

				while ((rmline = remover.readLine()) != null) { // add if true stop

					if (index.equalsIgnoreCase(rmline))
						tmp = true;
				}

				if (!tmp) {
				invertedBST.insert(index,i);
				}
				remover = new BufferedReader(new FileReader(file2));
				tmp = false;
			}
		
		}


		reader = new BufferedReader(new FileReader(file));
			remover = new BufferedReader(new FileReader(file2));

			line = reader.readLine();


			
			for (int i=0;i<50;i++){

				line = reader.readLine().substring(2); //change to first occurance of ,
				String temp[] = line.split(" ");
	
				tmp = false;
	
				for (String index : temp) {
					index = index.replaceAll("[^a-zA-Z0-9]", "").toLowerCase(); //merge it with reading 
	
					while ((rmline = remover.readLine()) != null) { // add if true stop
	
						if (index.equalsIgnoreCase(rmline))
							tmp = true;
					}
	
					if (!tmp) {
						
					normalIndex.insert(i,index);
					}
					remover = new BufferedReader(new FileReader(file2));
					tmp = false;
				}
			
			}

			reader = new BufferedReader(new FileReader(file));
			remover = new BufferedReader(new FileReader(file2));

			line = reader.readLine();

			 for (int i=0;i<50;i++){

			line = reader.readLine().substring(2); //change to first occurance of ,
			String temp[] = line.split(" ");

			tmp = false;

			for (String index : temp) {
				index = index.replaceAll("[^a-zA-Z0-9]", "").toLowerCase(); //merge it with reading 

				while ((rmline = remover.readLine()) != null) { // add if true stop

					if (index.equalsIgnoreCase(rmline))
						tmp = true;
				}

				if (!tmp) {
				invertedIndex.insert(index,i);
				}
				remover = new BufferedReader(new FileReader(file2));
				tmp = false;
			}
		
		}
		System.out.println("made an index");
		normalIndex.booleanQuery("market and sports").display();
	
	
		System.out.println("made an inverted index");
		invertedIndex.searchToList("market").display();
		System.out.println("made an inverted BST");
		invertedBST.searchToList("market").display();
		//	normalIndex.display();
	//	System.out.println("Made an inverted BST");


	//System.out.println("Testing search and print for the word ai");
	//invert.searchAndPrint("ai");                          //method search and print searchs for the key word then prints the docs which has it 
	
			//LinkedList l2 = invert.processAndQuery("weather", "warming");
			//l2.display();
			//LinkedList l3 = invert.processAndQuery("business", "world");
			//l3.display();
		

		} catch (Exception e) {

		}

	}
}
