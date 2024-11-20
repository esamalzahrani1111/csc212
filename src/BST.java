public class BST<T extends Comparable<T>> {
	BSTNode<T> root, current;

	/** Creates a new instance of BST */
	public BST() {
		root = current = null;
	}

	public boolean empty() {
		return root == null;
	}

	public boolean full() {
		return false;
	}

	public fLinkedList retrieve() {
		return current.data;
	}

	public boolean findKey(String targetKey) {
		BSTNode<T> p = root, q = root;

		if (empty())
			return false;

		while (p != null) {
			q = p;
			if (p.key.compareToIgnoreCase(targetKey) == 0) {
				current = p;
				return true;
			} else if (p.key.compareToIgnoreCase(targetKey) > 0)
				p = p.left;
			else
				p = p.right;
		}

		current = q;
		return false;
	}

	public boolean insert(String k, T val) {
		BSTNode<T> p, q = current;

		if (findKey(k)) {

			// fNode<T> temp;
			// temp = current.data;
			// while (temp.next != null && temp.data.compareTo(val)!=0) {
			// 	temp = temp.next;
			// }
			// if(temp.data.compareTo(val)==0)
			// temp.freq++;
			// else
			// temp.next = new fNode<T>(val);
			fLinkedList<T> temp = current.data;
			temp.insert(val);
			current = q; // findKey() modified current
			return false; // key already in the BST
		}

		p = new BSTNode<T>(k, val);
		if (empty()) {
			root = current = p;
			return true;
		} else {
			// current is pointing to parent of the new key
			if (current.key.compareTo(k) > 0)
				current.left = p;
			else
				current.right = p;
			current = p;
			return true;
		}
	}

	public void searchAndPrint(String k) {

		if (findKey(k)) {

			fLinkedList<T> temp = current.data;
			temp.findFirst();
			while (!temp.last()) {
				System.out.print(temp.retrieve() + ",");
				temp.findNext();
			}
			System.out.print(temp.retrieve() + ",");// one more time becuase the loop doesnt take in account the last element

		} else
			System.out.println("no node with this key");
	}

	public LinkedList<T> searchToList(String k) {

		if (findKey(k)) {
			LinkedList<T> DocList = new LinkedList<T>();

			fLinkedList<T> temp = current.data;
			temp.findFirst();
			while (!temp.last()) {

				DocList.insert(temp.retrieve());
				temp.findNext();
			}
			DocList.insert(temp.retrieve());// one more time becuase the loop doesnt take in account the last element
			return DocList;

		} else {
			System.out.println("no node with this key");
			return new LinkedList<T>();
		}

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
				else{
				    opStk.push(token);
					
				}
			} else
				docStk.push(searchToList(token));
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

		while ((i < List1.size()) && (j < List2.size())) {
			if ((List1).retrieve().equals(List2.retrieve())) {
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
				result.insert(list1.retrieve());
				list1.findNext();
				i++;
			} else if (j < size2 && (i >= size1 || list1.retrieve().compareTo(list2.retrieve()) > 0)) {
				result.insert(list2.retrieve());
				list2.findNext();
				j++;
			} else {
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
		HashMap scores = new HashMap(100);
		String[] tokens = query.split("\\s+");
	
		for (String token : tokens) {
			if (findKey(token)) {
				fLinkedList<T> docs = current.data;
				docs.findFirst();
				while (!docs.last()) {
					T docId = docs.retrieve();
					int score = scores.containsKey((Integer) docId) ?
					 scores.get((Integer) docId) : 0;
					scores.put((Integer) docId, score + docs.retrieveFreq());
					docs.findNext();
				}
				T docId = docs.retrieve(); // one more time becuase the loop doesnt take in account the last element
					int score = scores.containsKey((Integer) docId) ?
					 scores.get((Integer) docId) : 0;
					scores.put((Integer) docId, score + docs.retrieveFreq());
			}
		}
	
		int[] docIds = scores.keys();
		System.out.println("size of docIds array : "+ docIds.length);;
		int[] docScores = scores.values();
	
		SortUtils.mergeSort(docIds, docScores, 0, docIds.length - 1);
	
		for (int docId : docIds) {
			results.insert((T) Integer.valueOf(docId)); 
		}
	
		return results;
	}
	
}
