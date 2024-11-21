
public class invertedLinkedList<T extends Comparable<T>> {

	private invertedNode<T> head;
	private invertedNode<T> current;

	public invertedLinkedList() {
		head = null;
		current=null;
	}

	public boolean empty() {
		return head ==null;
	}

	public boolean last() {		
		return current.next ==null;
	}

	public void findFirst() {
		current = head;
		
	}

	public void findNext() {
		current = current.next;
	}

	public String retrieveWord() {
		return current.word;
	}

	public fLinkedList<T> retrieveDocs() {	
		return current.data;
	}

	public void updateWord(String val) {		
		current.word = val;
	}

	public void search(String i){
		current = head;
		while (current.word.compareToIgnoreCase(i) != 0 && current.next != null){
			current = current.next;
		}
	}

	public void insert (String key,T doc) {
		if (empty()) {
			current = head = new invertedNode<T>(key, doc);
			return;
		}
		search(key);
		if(current.word.compareToIgnoreCase(key) == 0){
			fLinkedList<T> temp = current.data;
			while (!temp.last() && temp.retrieve().compareTo(doc) != 0){
				temp.findNext();
			}
			if(temp.retrieve().compareTo(doc) == 0)
			{
				temp.addfreq();
				return;
			}else
			temp.insert(doc);
			return;
		}
		
		
		
		{
			current.next = new invertedNode<T>(key, doc);
		}
	}

	public void display() {
		
		fLinkedList<T> temp;
		invertedNode<T> oldCurr = current;


		current = head;

		while (current != null) {
		temp = current.data;
		temp.findFirst();
		System.out.print("word is " + current.word +" docs are : \n");
		while (!temp.last()) {
			System.out.print(temp.retrieve()+",");
			System.out.println("");
			temp.findNext();
			
		}
		System.out.print(temp.retrieve()+",");
			System.out.println("");
		current = current.next;
	}
	current = oldCurr;
		}

	public int size() {
		int counter = 0;
		invertedNode<T> tmp = current;
		current = head;
		while(current != null) {
			counter++;
			current = current.next;
		}
		current = tmp;
		return counter;
	}

	public fLinkedList<T> booleanQuery(String query) {
		Stack<fLinkedList<T>> docStk = new Stack<>();
		Stack<String> opStk = new Stack<>();
		String[] tokens = query.split("\\s+");

		for (String token : tokens) {
			if (token.equalsIgnoreCase("AND") || token.equalsIgnoreCase("OR")) {
				if (!opStk.empty() && precedence(opStk.peek()) > precedence(token)) {
					processLogicalOperation(docStk, opStk);
				} else {
					opStk.push(token);
				}
			} else {
				docStk.push(searchToList(token));
			}
		}

		while (!opStk.empty()) {
			processLogicalOperation(docStk, opStk);
		}
		return docStk.pop();
	}

	public fLinkedList<T> searchToList(String key) {
		search(key);
		if (current != null && current.word.compareToIgnoreCase(key) == 0) {
			return current.data;
		}
		return new fLinkedList<>();
	}

	private void processLogicalOperation(Stack<fLinkedList<T>> docStk, Stack<String> opStk) {
		fLinkedList<T> right = docStk.pop();
		fLinkedList<T> left = docStk.pop();
		String op = opStk.pop();

		if (op.equalsIgnoreCase("AND")) {
			docStk.push(processAndQuery(left, right));
		} else if (op.equalsIgnoreCase("OR")) {
			docStk.push(processOrQuery(left, right));
		}
	}

	private fLinkedList<T> processAndQuery(fLinkedList<T> word1, fLinkedList<T> word2) {

		fLinkedList<T> List1 = word1;
		fLinkedList<T> List2 = word2;
		fLinkedList<T> result = new fLinkedList<T>();

		List1.findFirst();
		List2.findFirst();

		int i = 0;
		int j = 0;

		while ((i < List1.size()) && (j < List2.size())) {
			if ((List1).retrieve().equals(List2.retrieve())) {
				if(result.empty() || List1.retrieve().compareTo(result.retrieve()) != 0)
				result.insert(List1.retrieve());
				List1.findNext();
				List2.findNext();
				i++;
				j++;
			} else if (List1.retrieve().compareTo(List2.retrieve()) < 0) {
				List1.findNext();
				i++;
			} else {
				List2.findNext();
				j++;
			}

		}

		return result;

	}

	private fLinkedList<T> processOrQuery(fLinkedList<T> word1, fLinkedList<T> word2) {

		fLinkedList<T> list1 = word1;
		fLinkedList<T> list2 = word2;
		fLinkedList<T> result = new fLinkedList<T>();

		list1.findFirst();
		list2.findFirst();

		int i = 0;
		int j = 0;

		int size1 = list1.size();
		int size2 = list2.size();

		while (i < size1 || j < size2) {
			if (i < size1 && (j >= size2 || list1.retrieve().compareTo(list2.retrieve()) < 0)) {
				if(result.empty() || list1.retrieve().compareTo(result.retrieve()) != 0)
				result.insert(list1.retrieve());
				list1.findNext();
				i++;
			} else if (j < size2 && (i >= size1 || list1.retrieve().compareTo(list2.retrieve()) > 0)) {
				if(result.empty() || list2.retrieve().compareTo(result.retrieve()) != 0)
				result.insert(list2.retrieve());
				list2.findNext();
				j++;
			} else {
				if(result.empty() || list1.retrieve().compareTo(result.retrieve()) != 0)
				result.insert(list1.retrieve());
				list1.findNext();
				list2.findNext();
				i++;
				j++;
			}
		}

		return result;
	}

	private int precedence(String op) {
		if (op.equals("AND"))
			return 2;
		if (op.equals("OR"))
			return 1;
		return 0;
	}

	@SuppressWarnings("unchecked")
	public fLinkedList<T> rankedQuery(String query) {
		fLinkedList<T> results = new fLinkedList<>();
		HashMap scores = new HashMap(100);
		String[] tokens = query.split("\\s+");

		for (String token : tokens) {
			invertedNode<T> current = head;
			while (current != null) {
				if (current.word.equalsIgnoreCase(token)) {
					fLinkedList<T> docs = current.data;
					docs.findFirst();
					while (!docs.last()) {
						T docId = docs.retrieve();
						int score = scores.containsKey((Integer) docId) ?
						 scores.get((Integer) docId) : 0;
						scores.put((Integer) docId, score + docs.retrieveFreq());
						docs.findNext();;
					}
					T docId = docs.retrieve();
						int score = scores.containsKey((Integer) docId) ?
						 scores.get((Integer) docId) : 0;
						scores.put((Integer) docId, score + docs.retrieveFreq());
						docs.findNext();;
				}
				current = current.next;
			}
		}
	
		int[] docIds = scores.keys();
		int[] docScores = scores.values();
	
		SortUtils.mergeSort(docIds, docScores, 0, docIds.length - 1);
	
		for (int docId : docIds) {
			results.insert((T) Integer.valueOf(docId)); 
		}
		int sizeOfR = docIds.length;
		System.out.println("DocID\t\tScore");
		for (int i =0;i<sizeOfR;i++){
			System.out.println(docIds[i] + "\t\t" + docScores[i]);
		}
	
		return results;
	}
	
}