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
		int i =0;
		int firstocc;
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

			 
				while((line = reader.readLine()).compareTo(",,") != 0){
					firstocc = line.indexOf(",");
			line = line.substring(firstocc); //change to first occurance of ,
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
			i++;
		}
		i=0;

		reader = new BufferedReader(new FileReader(file));
			remover = new BufferedReader(new FileReader(file2));

			line = reader.readLine();


			
			while((line = reader.readLine()).compareTo(",,") != 0 ){

				firstocc = line.indexOf(",");
				line = line.substring(firstocc); //change to first occurance of ,
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
				i++;
			
			}
			i=0;
			reader = new BufferedReader(new FileReader(file));
			remover = new BufferedReader(new FileReader(file2));

			line = reader.readLine();

			while((line = reader.readLine()).compareTo(",,") != 0){

				firstocc = line.indexOf(",");
			line = line.substring(firstocc); //change to first occurance of ,
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
			i++;
		
		}
		i=0;
		//System.out.println("made an index");
		//normalIndex.booleanQuery("market or sports AND warming").display();
//while (!normalIndex.last()){
	//	normalIndex.findnext();
//	}
//	System.out.println(normalIndex.retrieveId());
	
		//System.out.println("made an inverted index");
		//invertedIndex.booleanQuery("market OR sports AND warming").display();
		//invertedIndex.findfirst();
		//fNode<Integer> testing= invertedIndex.retrieveDocs();
		//System.out.println(invertedIndex.retrieveDocs().next.data);
		//invertedIndex.search("plastic");
		//System.out.println(invertedIndex.retrieveDocs().freq);
				//invertedIndex.findfirst();
	//	System.out.println("made an inverted BST");
	//	invertedBST.booleanQuery("market OR sports AND warming").display();
		//	normalIndex.display();
	//	System.out.println("Made an inverted BST");


	//System.out.println("Testing search and print for the word ai");
	//invert.searchAndPrint("ai");                          //method search and print searchs for the key word then prints the docs which has it 
	
			//LinkedList l2 = invert.processAndQuery("weather", "warming");
			//l2.display();
			//LinkedList l3 = invert.processAndQuery("business", "world");
			//l3.display();

			//invertedBST.findkey("plastic");
			//System.out.println(invertedBST.current.data.next);
		

		} catch (Exception e) {

		}

	}
}
