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

	public T retrieve() {
		return current.data.data;
	}

	public boolean findkey(String tkey) {
		BSTNode<T> p = root, q = root;

		if (empty())
			return false;

		while (p != null) {
			q = p;
			if (p.key.compareToIgnoreCase(tkey) == 0) {
				current = p;
				return true;
			} else if (p.key.compareToIgnoreCase(tkey) > 0)
				p = p.left;
			else
				p = p.right;
		}

		current = q;
		return false;
	}

	public boolean insert(String k, T val) {
		BSTNode<T> p, q = current;

		if (findkey(k)) {

			fNode<T> temp;
			temp = current.data;
			while (temp.next != null && temp.data.compareTo(val)!=0) {
				temp = temp.next;
			}
			if(temp.data.compareTo(val)==0)
			temp.freq++;
			else
			temp.next = new fNode<T>(val);
			current = q; // findkey() modified current
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

		if (findkey(k)) {

			fNode<T> temp = current.data;

			while (temp != null) {
				System.out.print(temp.data + ",");
				temp = temp.next;
			}

		} else
			System.out.println("no node with this key");
	}

	public LinkedList<T> searchToList(String k) {

		if (findkey(k)) {
			LinkedList<T> DocList = new LinkedList<T>();

			fNode<T> temp = current.data;

			while (temp != null) {

				DocList.insert(temp.data);
				temp = temp.next;
			}
			return DocList;

		} else {
			System.out.println("no node with this key");
			return null;
		}

	}

	public LinkedList<T> booleanQuery(String query) {

		Stack<LinkedList<T>> docStk = new Stack<LinkedList<T>>();
		Stack<String> opStk = new Stack<String>();
		String[] tokens = query.split("\\s+");

		for (String token : tokens) {


			if (token.equalsIgnoreCase("AND") || token.equalsIgnoreCase("OR")) {
				
				if (!opStk.empty() && (precedence(opStk.peek()) > precedence(token))) {
				
					doQuery(docStk, opStk);
			}
				else{
				    opStk.push(token);
					
				}
			} else
				docStk.push(searchToList(token));
		}

		while (!opStk.empty()) {
			
			doQuery(docStk, opStk);
			
		}
		return docStk.pop();
	}

	private LinkedList<T> processAndQuery(LinkedList<T> word1, LinkedList<T> word2) {

		LinkedList<T> List1 = word1;
		LinkedList<T> List2 = word2;
		LinkedList<T> result = new LinkedList<T>();

		List1.findfirst();
		List2.findfirst();

		int i = 0;
		int j = 0;

		while ((i < List1.size()) && (j < List2.size())) {
			if ((List1).retrieve().equals(List2.retrieve())) {
				result.insert(List1.retrieve());
				List1.findnext();
				List2.findnext();
				i++;
				j++;
			} else if (List1.retrieve().compareTo(List2.retrieve()) < 0) {
				List1.findnext();
				i++;
			} else {
				List2.findnext();
				j++;
			}

		}
		// Remove duplicates from the result
		result.findfirst();
		T last = null;
		while (!result.last()) {
			if (result.retrieve().equals(last)) {
				result.remove();
			} else {
				last = result.retrieve();
				result.findnext();
			}
		}
		return result;

	}

	private LinkedList<T> processOrQuery(LinkedList<T> word1, LinkedList<T> word2) {

		LinkedList<T> list1 = word1;
		LinkedList<T> list2 = word2;
		LinkedList<T> result = new LinkedList<T>();

		list1.findfirst();
		list2.findfirst();

		int i = 0;
		int j = 0;

		int size1 = list1.size();
		int size2 = list2.size();

		while (i < size1 || j < size2) {
			if (i < size1 && (j >= size2 || list1.retrieve().compareTo(list2.retrieve()) < 0)) {
				result.insert(list1.retrieve());
				list1.findnext();
				i++;
			} else if (j < size2 && (i >= size1 || list1.retrieve().compareTo(list2.retrieve()) > 0)) {
				result.insert(list2.retrieve());
				list2.findnext();
				j++;
			} else {
				result.insert(list1.retrieve());
				list1.findnext();
				list2.findnext();
				i++;
				j++;
			}
		}
		// Remove duplicates from the result
		result.findfirst();
		T last = null;
		while (!result.last()) {
			if (result.retrieve().equals(last)) {
				result.remove();
			} else {
				last = result.retrieve();
				result.findnext();
			}
		}

		return result;
	}

	private void doQuery(Stack<LinkedList<T>> docStk, Stack<String> opStk) {

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

}
