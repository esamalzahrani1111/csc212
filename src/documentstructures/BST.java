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

	public fLinkedList<T> retrieve() {
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

	public fLinkedList<T> searchToList(String k) {

		if (findKey(k)) {
			return current.data;

		} else {
			System.out.println("no node with this key");
			return new fLinkedList<T>();
		}

	}

	public fLinkedList<T> booleanQuery(String query) {

		Stack<fLinkedList<T>> docStk = new Stack<fLinkedList<T>>();
		Stack<String> opStk = new Stack<String>();
		String[] tokens = QueryUtils.tokenizeQuery(query);

		for (String token : tokens) {
			if (token.equalsIgnoreCase("AND") || token.equalsIgnoreCase("OR")) {
				if (!opStk.empty() && (QueryUtils.precedence(opStk.peek()) > QueryUtils.precedence(token))) {
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

	private fLinkedList<T> processAndQuery(fLinkedList<T> word1, fLinkedList<T> word2) {

		fLinkedList<T> List1 = word1;
		fLinkedList<T> List2 = word2;
		fLinkedList<T> result = new fLinkedList<T>();

		List1.findFirst();
		List2.findFirst();

		int size1 = List1.size();
		int size2 = List2.size();

		int i = 0;
		int j = 0;

		while ((i < size1) && (j < size2 )) {
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
