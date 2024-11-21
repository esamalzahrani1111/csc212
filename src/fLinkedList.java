
public class fLinkedList<T> {
	private fNode<T> head;
	private fNode<T> current;
 
 public fLinkedList() {
	 head = null;
	 current=null;
 }
 public fLinkedList(T val){
   head=null;
   current = null;
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
 public void addfreq(){
	current.freq++;
 }
 
 public T retrieve() {
	 return current.data;
 }
 public int retrieveFreq(){
return current.freq;

 }
 
 public void update(T val) { 	 
	current.data = val;
 } 

 public void insert (T val) {
		fNode<T> tmp;
		if (empty()) {
			current = head = new fNode<T> (val);
		}
		else {
			tmp = current.next;
			current.next = new fNode<T> (val);
			current = current.next;
			current.next = tmp;
		}
	}
 
 public void remove () {
		if (current == head) {
			head = head.next;
		}
		else {
			fNode<T> tmp = head;

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
	 
	 
	 fNode<T> tmp = current;
     current = head;
     while (current != null) {
         System.out.println(current.data);
         current = current.next;
     }
     current = tmp;
 }
	public int size() {
		int counter = 0;
		fNode<T> tmp = current;
		current = head;
		while(current != null) {
			counter++;
			current = current.next;
		}
		current = tmp;
		return counter;
	}
}

//same as slides
