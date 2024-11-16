
public class indexLinkedList<T> {
	private indexNode<T> head;
	private indexNode<T> current;
 
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
 public void findfirst() {
	 current = head;
	 
 }
 
 public void findnext() {
	 
	 
	 current = current.next;
 }
 
 public int retrieveId() {
	 
	 return current.docId;
	 
 }
 public Node retrieveWords() {
	 
    return current.data;
    
}
 public void updateId(int val) {
	 
	 
	 current.docId = val;
 }
public void search(int i){
current = head;
while (  current.docId != i && current.next != null){
    current = current.next;
}


}
 
 
 public void insert (int id,T word) {
		if (empty()) {
			current = head = new indexNode (id,word);
            return;
		}
		search(id);
        if(current.docId == id){
            Node<T> temp = current.data;
            while(temp.next != null){
                temp = temp.next;
                }
            temp.next = new Node(word);
            return;
        }
        
        
        
        {
			current.next = new indexNode<T>(id, word);
		}
	}
 
 public void remove () {
		if (current == head) {
			head = head.next;
		}
		else {
			indexNode tmp = head;

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
	 
	 Node<T> temp;
	 indexNode oldCurr = current;


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
		indexNode tmp = current;
		current = head;
		while(current != null) {
			counter++;
			current = current.next;
		}
		current = tmp;
		return counter;
	}

     private LinkedList<T> searchToList(String key) {
        LinkedList<T> docList = new LinkedList<>();
        current = head;
        while (current != null) {
            Node<T> temp = current.data;
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
}

//same as slides
