
public class invertedLinkedList<T> {
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
 public void findfirst() {
	 current = head;
	 
 }
 
 public void findnext() {
	 
	 
	 current = current.next;
 }
 
 public String retrieveWord() {
	 
	 return current.word;
	 
 }
 public Node retrieveDocs() {
	 
    return current.data;
    
}
 public void updateWord(String val) {
	 
	 
	 current.word = val;
 }

public void search(String i){
current = head;
while (  current.word.compareToIgnoreCase(i) != 0 && current.next != null){
    current = current.next;
}


}
 
 
 public void insert (String key,T doc) {
		if (empty()) {
			current = head = new invertedNode(key,doc);
            return;
		}
		search(key);
        if(current.word.compareToIgnoreCase(key) == 0){
            Node<T> temp = current.data;
            while(temp.next != null){
                temp = temp.next;
                }
            temp.next = new Node(doc);
            return;
        }
        
        
        
        {
			current.next = new invertedNode<T>(key, doc);
		}
	}
 
 
 
 public void display() {
	 
	 Node<T> temp;
	 invertedNode oldCurr = current;


     current = head;

     while (current != null) {
        temp = current.data;
		System.out.print("word is " + current.word +" docs are : \n");
        while (temp !=null) {
            System.out.print(temp.data+",");
            System.out.println("");
            temp = temp.next;
            
        }
        current = current.next;
    }
    current = oldCurr;
     }
 
	public int size() {
		int counter = 0;
		invertedNode tmp = current;
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
