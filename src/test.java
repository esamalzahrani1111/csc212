import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Scanner;

public class test {
	public static void main(String[] args) {

		System.out.println("Current Working Directory: " + new File(".").getAbsolutePath());

		String file = "src//dataset.csv";
		String file2 = "src//stop.txt";

		System.out.println("Resolved dataset.csv Path: " + new File(file).getAbsolutePath());
        System.out.println("Resolved stop.txt Path: " + new File(file2).getAbsolutePath());

		System.out.println("dataset.csv exists? " + new File(file).exists());
        System.out.println("stop.txt exists? " + new File(file2).exists());

		BufferedReader reader = null;
		BufferedReader remover = null;
		int i =0;
		int firsToOccur;
		String line;
		String removeLine;
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
					firsToOccur = line.indexOf(",");
			line = line.substring(firsToOccur); //change to first occurrence of ,
			String temp[] = line.split(" ");

			tmp = false;

			for (String index : temp) {
				index = index.replaceAll("[^a-zA-Z0-9]", "").toLowerCase(); //merge it with reading 

				while ((removeLine = remover.readLine()) != null) { // add if true stop

					if (index.equalsIgnoreCase(removeLine))
						tmp = true;
				}

				if (!tmp) {
					words.insert(index);
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


			
			while((line = reader.readLine()).compareTo(",,") != 0){

				firsToOccur = line.indexOf(",");
				line = line.substring(firsToOccur); //change to first occurrence of ,
				String temp[] = line.split(" ");
	
				tmp = false;
	
				for (String index : temp) {
					index = index.replaceAll("[^a-zA-Z0-9]", "").toLowerCase(); //merge it with reading 
	
					while ((removeLine = remover.readLine()) != null) { // add if true stop
	
						if (index.equalsIgnoreCase(removeLine))
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

				firsToOccur = line.indexOf(",");
			line = line.substring(firsToOccur); //change to first occurrence of ,
			String temp[] = line.split(" ");

			tmp = false;

			for (String index : temp) {
				index = index.replaceAll("[^a-zA-Z0-9]", "").toLowerCase(); //merge it with reading 

				while ((removeLine = remover.readLine()) != null) { // add if true stop

					if (index.equalsIgnoreCase(removeLine))
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
		reader.close();
		reader.close();

		// System.out.println("Testing the invertedindex");
		// System.out.println("testing the search for market");
		// invertedIndex.searchToList("market").display();
		// System.out.println("testing the and market and sports");
		// invertedIndex.booleanQuery("market and sports").display();
		// System.out.println("testing the or for market or sports");
		// invertedIndex.booleanQuery("market or sports").display();
		// System.out.println("testing the funcationlity of and/or market or sports and warming");
		// invertedIndex.booleanQuery("market or sports and warming").display();;
		// System.out.println("testing the ranked market sports");
	//	System.out.println("made an index");
	  //  System.out.println("doing boolean query: market OR sports AND warming");
		//normalIndex.booleanQuery("market or sports AND warming").display();
		//System.out.println("doing ranked query: market sports");
		//normalIndex.rankedQuery("market sports").display();
//while (!normalIndex.last()){
	//	normalIndex.findnext();
//	}
//	System.out.println(normalIndex.retrieveId());
	
	//	System.out.println("made an inverted index");
	//	System.out.println("doing boolean query: market OR sports AND warming");
		//invertedIndex.booleanQuery("market OR sports AND warming").display();
	//	System.out.println("doing ranked query: market sports");
		//invertedIndex.rankedQuery("market sports").display();
		//invertedIndex.findfirst();
		//fNode<Integer> testing= invertedIndex.retrieveDocs();
		//System.out.println(invertedIndex.retrieveDocs().next.data);
		//invertedIndex.search("plastic");
		//System.out.println(invertedIndex.retrieveDocs().freq);
				//invertedIndex.findfirst();
//		System.out.println("made an inverted BST");
	//	System.out.println("doing boolean query: market OR sports AND warming");
		//invertedBST.booleanQuery("market OR sports AND warming").display();
		//System.out.println("doing ranked query: market sports");
		//invertedBST.rankedQuery("market sports").display();
		//	normalIndex.display();



	//System.out.println("Testing search and print for the word ai");
	//invert.searchAndPrint("ai");                          //method search and print searches for the key word then prints the docs which has it 
	
			//LinkedList l2 = invert.processAndQuery("weather", "warming");
			//l2.display();
			//LinkedList l3 = invert.processAndQuery("business", "world");
			//l3.display();

			//invertedBST.findkey("plastic");
			//invertedBST.rankedQuery("market sports");
			//normalIndex.booleanQuery("market and sports").display();
		// // --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// //                                                                                      LAST TEST HERE THEN MAIN
		// // --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			// System.out.println("TESTING NORMAL INDEX TESTING NORMAL INDEX TESTING NORMAL INDEX TESTING NORMAL INDEX TESTING NORMAL INDEX TESTING NORMAL INDEX TESTING NORMAL INDEX TESTING NORMAL INDEX");
			// System.out.println("BOOLEAN QUERYS");
			// normalIndex.booleanQuery("market and sports").display();
			// System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			// normalIndex.booleanQuery("weather and warming").display();
			// System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			// normalIndex.booleanQuery("business and world").display();
			// System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			// normalIndex.booleanQuery("weather or warming").display();
			// System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			// normalIndex.booleanQuery("market or sports").display();
			// System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			// normalIndex.booleanQuery("market or sports and warming").display();
			// System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			// System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			// System.out.println("RANKED QUERYS");
			// normalIndex.rankedQuery("market sports");
			// System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			// normalIndex.rankedQuery("weather warming");
			// System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			// normalIndex.rankedQuery("business world market");
			// System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			//  System.out.println("################################################################################################################################################################################################");
			// System.out.println("TESTING INVERTED INDEX TESTING INVERTED INDEX TESTING INVERTED INDEX TESTING INVERTED INDEX TESTING INVERTED INDEX TESTING INVERTED INDEX TESTING INVERTED INDEX TESTING INVERTED INDEX ");
			// System.out.println("BOOLEAN QUERYS");
			// invertedIndex.booleanQuery("market and sports").display();
			// System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			// invertedIndex.booleanQuery("weather and warming").display();
			// System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			// invertedIndex.booleanQuery("business and world").display();
			// System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			// invertedIndex.booleanQuery("weather or warming").display();
			// System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			// invertedIndex.booleanQuery("market or sports").display();
			// System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			// invertedIndex.booleanQuery("market or sports and warming").display();
			// System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			// System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			// System.out.println("RANKED QUERYS");
			// invertedIndex.rankedQuery("market sports");
			// System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			// invertedIndex.rankedQuery("weather warming");
			// System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			// invertedIndex.rankedQuery("business world market");
			// System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			System.out.println("################################################################################################################################################################################################");
			System.out.println("TESTING INVERTED BST TESTING INVERTED BST TESTING INVERTED BST TESTING INVERTED BST TESTING INVERTED BST TESTING INVERTED BST TESTING INVERTED BST TESTING INVERTED BST TESTING INVERTED BST ");
			System.out.println("BOOLEAN QUERYS");
			invertedBST.booleanQuery("market and sports").display();
			System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			invertedBST.booleanQuery("weather and warming").display();
			System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			invertedBST.booleanQuery("business and world").display();
			System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			invertedBST.booleanQuery("weather or warming").display();
			System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			invertedBST.booleanQuery("market or sports").display();
			System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			invertedBST.booleanQuery("market or sports and warming").display();
			System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("RANKED QUERYS");
			invertedBST.rankedQuery("market sports");
			System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			invertedBST.rankedQuery("weather warming");
			System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			invertedBST.rankedQuery("business world market");
			System.out.println("SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||SEPARATOR|||");
			System.out.println("################################################################################################################################################################################################");
		//// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		////                                                                                  TEST ENDS HERE
		//// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
