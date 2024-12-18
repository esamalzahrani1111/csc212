
public class LinkedList<T> {
	private Node<T> head;
	private Node<T> current;

	public LinkedList() {
		head = null;
		current=null;
	}
	public LinkedList(T val){
		head = null;
		current=null;
		insert(val);
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

	public T retrieve() {
		return current.data;
	}
	public void update(T val) {
		current.data = val;
	}



	public void insert (T val) {
		Node<T> tmp;
		if (empty()) {
			current = head = new Node<T> (val);
		}
		else {
			tmp = current.next;
			current.next = new Node<T> (val);
			current = current.next;
			current.next = tmp;
		}
	}

	public void remove () {
		if (current == head) {
			head = head.next;
		}
		else {
			Node<T> tmp = head;

			while (tmp.next != current){
				tmp = tmp.next;
			}
			tmp.next = current.next;
		}

		if (current.next == null)
			current = head;
		else
			current = current.next;
	}


	public void display() {
		
		Node<T> tmp = current;
		current = head;
		while (current != null) {
			System.out.println(current.data);
			current = current.next;
		}
		current = tmp;
	}
	public int size() {
		int counter = 0;
		Node<T> tmp = current;
		current = head;
		while(current != null) {
			counter++;
			current = current.next;
		}
		current = tmp;
		return counter;
	}
}
