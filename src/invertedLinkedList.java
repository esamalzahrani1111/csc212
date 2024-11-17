
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
 public void findfirst() {
	 current = head;
	 
 }
 
 public void findnext() {
	 
	 
	 current = current.next;
 }
 
 public String retrieveWord() {
	 
	 return current.word;
	 
 }
 public fNode retrieveDocs() {
	 
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
            fNode<T> temp = current.data;
			while (temp.next != null && temp.data.compareTo(doc) != 0){
				temp = temp.next;
			}
			if(temp.data.compareTo(doc) == 0)
			{
				temp.freq++;
				return;
			}else
            temp.next = new fNode(doc);
            return;
        }
        
        
        
        {
			current.next = new invertedNode<T>(key, doc);
		}
	}
 
 
 
 public void display() {
	 
	 fNode<T> temp;
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

    public LinkedList<T> booleanQuery(String query) {
		Stack<LinkedList<T>> docStk = new Stack<>();
		Stack<String> opStk = new Stack<>();
		String[] tokens = query.split("\\s+");

		for (String token : tokens) {
			if (token.equalsIgnoreCase("AND") || token.equalsIgnoreCase("OR")) {
				if (!opStk.empty() && precedence(opStk.peek()) > precedence(token)) {
					doQuery(docStk, opStk);
				} else {
					opStk.push(token);
				}
			} else {
				docStk.push(searchToList(token));
			}
		}

		while (!opStk.empty()) {
			doQuery(docStk, opStk);
		}
		return docStk.pop();
	}

	public LinkedList<T> searchToList(String key) {
		search(key);
		LinkedList<T> docList = new LinkedList<>();
		if (current != null && current.word.compareToIgnoreCase(key) == 0) {
			fNode<T> temp = current.data;
			while (temp != null) {
				docList.insert(temp.data);
				temp = temp.next;
			}
		}
		return docList;
	}

	private void doQuery(Stack<LinkedList<T>> docStk, Stack<String> opStk) {
		LinkedList<T> right = docStk.pop();
		LinkedList<T> left = docStk.pop();
		String op = opStk.pop();

		if (op.equals("AND")) {
			docStk.push(processAndQuery(left, right));
		} else if (op.equals("OR")) {
			docStk.push(processOrQuery(left, right));
		}
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

	private int precedence(String op) {
		if (op.equals("AND"))
			return 2;
		if (op.equals("OR"))
			return 1;
		return 0;
	}
}

//same as slides
