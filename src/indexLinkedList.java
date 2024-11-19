
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

	public Node<U> retrieveWords() {
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
			Node<U> temp = current.data;
			while(temp.next != null){
				temp = temp.next;
			}
			temp.next = new Node<U>(word);
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

	public void display() {
		
		Node<U> temp;
		indexNode<T, U> oldCurr = current;

		current = head;

		while (current != null) {
			temp = current.data;
			System.out.println("DocID is " + current.docId);
			while (temp !=null) {
				System.out.println("  word is " + temp.data);
				temp = temp.next;
				
			}
			current = current.next;
		}
		current = oldCurr;
	}

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
		while (current != null) {
			Node<U> temp = current.data;
			while (temp != null) {
				if (temp.data.toString().equalsIgnoreCase(key)) {
					docList.insert(current.docId);
				}
				temp = temp.next;
			}
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
					doQuery(docStk, opStk);
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
