public class indexLinkedList<T extends Comparable<T>,U> {
	private indexNode<T,U> head;
	private indexNode<T,U> current;

	public indexLinkedList() {
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

	public T retrieveId() {
		return current.docId;
	}

	public BSTWORDS retrieveWords() {
		return current.data;
	}

	public void updateId(T val) {	
		current.docId = val;
	}
	
	public void search(T i){
		current = head;
		while (current.docId.compareTo(i) != 0 && current.next != null){
			current = current.next;
		}
	}

	public void insert (T id,U word) {
		if (empty()) {
			current = head = new indexNode<T, U>(id, word);
			return;
		}
		search(id);
		if(current.docId.compareTo(id) == 0){
			BSTWORDS temp = current.data;
			temp.insert(word.toString());
			return;
		}
		current.next = new indexNode<T,U>(id, word);
	}

	public void remove () {
		if (current == head) {
			head = head.next;
		}
		else {
			indexNode<T, U> tmp = head;

			while (tmp.next != current)
				tmp = tmp.next;

			tmp.next = current.next;
		}

		if (current.next == null)
			current = head;
		else
			current = current.next;
	}

	// public void display() {
		
	// 	LinkedList<U> temp;
	// 	indexNode<T, U> oldCurr = current;

	// 	current = head;
		
	// 	while (current != null) {
	// 		temp = current.data;
	// 		System.out.println("DocID is " + current.docId);
	// 		while (!temp.last()) {
	// 			System.out.println("  word is " + temp.retrieve());
	// 			temp.findNext();
				
	// 		}
	// 		System.out.println("  word is " + temp.retrieve()); // one more time becuase the loop doesnt take in account the last element
	// 		current = current.next;
	// 	}
	// 	current = oldCurr;
	// }

	public int size() {
		int counter = 0;
		indexNode<T, U> tmp = current;
		current = head;
		while(current != null) {
			counter++;
			current = current.next;
		}
		current = tmp;
		return counter;
	}

	public LinkedList<T> searchToList(String key) {
		LinkedList<T> docList = new LinkedList<>();
		current = head;
		BSTWORDS temp;
		while (current != null) {
			// LinkedList<U> temp = current.data;
			// temp.findFirst();
			// while (!temp.last()) {
			// 	if (temp.retrieve().toString().equalsIgnoreCase(key)) {    
			// 		docList.insert(current.docId);
			// 	}
			// 	temp.findNext();
			// }
			// if (temp.retrieve().toString().equalsIgnoreCase(key)) { // one more time becuase the loop doesnt take in account the last element
			// 	docList.insert(current.docId);
			// }
			temp = current.data;
			if (temp.findKey(key))
			docList.insert(current.docId);
			current = current.next;
		}
		return docList;
	}

	public LinkedList<T> booleanQuery(String query) {

		Stack<LinkedList<T>> docStk = new Stack<LinkedList<T>>();
		Stack<String> opStk = new Stack<String>();
		String[] tokens = query.split("\\s+");

		for (String token : tokens) {
			if (token.equalsIgnoreCase("AND") || token.equalsIgnoreCase("OR")) {
				if (!opStk.empty() && (precedence(opStk.peek()) > precedence(token))) {
					processLogicalOperation(docStk, opStk);
				}
				else {
					opStk.push(token);
				}
			}
			else {
				docStk.push(searchToList(token));
			}
		}

		while (!opStk.empty()) {
			processLogicalOperation(docStk, opStk);	
		}
		return docStk.pop();
	}

	private LinkedList<T> processAndQuery(LinkedList<T> word1, LinkedList<T> word2) {

		LinkedList<T> List1 = word1;
		LinkedList<T> List2 = word2;
		LinkedList<T> result = new LinkedList<T>();

		List1.findFirst();
		List2.findFirst();

		int i = 0;
		int j = 0;

		int size1 = List1.size();
		int size2 = List2.size();

		while ((i < size1) && (j < size2)) {
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

	private LinkedList<T> processOrQuery(LinkedList<T> word1, LinkedList<T> word2) {

		LinkedList<T> list1 = word1;
		LinkedList<T> list2 = word2;
		LinkedList<T> result = new LinkedList<T>();

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

	private void processLogicalOperation(Stack<LinkedList<T>> docStk, Stack<String> opStk) {

		LinkedList<T> right = docStk.pop();
		LinkedList<T> left = docStk.pop();
		String op = opStk.pop();

		if (op.equalsIgnoreCase("AND")) {
			docStk.push(processAndQuery(left, right));
		} else if (op.equalsIgnoreCase("OR")) {
			docStk.push(processOrQuery(left, right));
		}
	}

	private int precedence(String op) {
		if (op.equalsIgnoreCase("AND"))
			return 2;
		if (op.equalsIgnoreCase("OR"))
			return 1;
		return 0;
	}

	@SuppressWarnings("unchecked")
	public LinkedList<T> rankedQuery(String query) {
		
		LinkedList<T> results = new LinkedList<>();
		HashMap scores = new HashMap(100); // For docId (T) and scores
		String[] tokens = query.split("\\s+");

		indexNode<T, U> current = head;
		while (current != null) {
			T docId = current.docId;
			int score = scores.containsKey((Integer) docId) ?
			 scores.get((Integer) docId) : 0;
	
			BSTWORDS words = current.data;
				for (String token : tokens) {
					if (words.findKey(token)) {
						score = score + words.retrieveFreq();
					}
				}
			if (score > 0) {
				scores.put((Integer) docId, score);
			}
			current = current.next;
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
		// for (int i = 0; i < docIds.length; i++) {
		// 	if (docScores[i] > 0){
		// 		results.insert((T) Integer.valueOf(docIds[i]));
		// 	}
		// }
	
		return results;
	}
	
}
