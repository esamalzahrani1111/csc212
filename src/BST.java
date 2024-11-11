public class BST <T extends Comparable<T>> {
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
	
	public T retrieve () {
		return current.data.data;
	}
	public boolean findkey(String tkey) {
		BSTNode<T> p = root,q = root;
				
		if(empty())
			return false;
		
		while(p != null) {
			q = p;
			if(p.key.compareToIgnoreCase(tkey) == 0) {
				current = p;
				return true;
			}
			else if(p.key.compareToIgnoreCase(tkey) > 0)
				p = p.left;
			else
				p = p.right;
		}
		
		current = q;
		return false;
	}
	public boolean insert(String k, T val) {
		BSTNode<T> p, q = current;
		
		if(findkey(k)) {
			
            Node<T> temp;
            temp = current.data;
            while(temp.next != null){
              temp = temp.next;
             }
            temp.next = new Node<T>(val);
           current = q;  // findkey() modified current
			return false; // key already in the BST
		}
		
		p = new BSTNode<T>(k, val);
		if (empty()) {
			root = current = p;
			return true;
		}
		else {
			// current is pointing to parent of the new key
			if (current.key.compareTo(k) > 0)
				current.left = p;
			else
				current.right = p;
			current = p;
			return true;
		}
	}

    public void searchAndPrint(String k){                  //used for testing ,not actually used in the program

        if (findkey(k)){

        Node<T> temp = current.data;

        while(temp != null)
        {
            System.out.println(temp.data);
            temp = temp.next;
        }

        }
        else 
        System.out.println("no node with this key");
    }

   	public LinkedList<T> searchToList(String k) {

		if (findkey(k)) {
			LinkedList<T> DocList = new LinkedList<T>();

			Node<T> temp = current.data;

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


		public LinkedList<T> processAndQuery(String word1, String word2) {

		LinkedList<T> List1 = searchToList(word1.toLowerCase());
		LinkedList<T> List2 = searchToList(word2.toLowerCase());
		LinkedList<T> result = new LinkedList<T>();

		List1.findfirst();
		List2.findfirst();
		
		int i = 0;
		int j = 0;
		int list1size = List1.size();
		int list2size = List2.size();
		while ((i < list1size) && (j < list2size)) {       //changed here size check if error happened                            
			if ((List1).retrieve().equals(List2.retrieve())) {
				result.insert(List1.retrieve());
				List1.findnext();
				List2.findnext();
				i++;
				j++;
			} else if (List1.retrieve().compareTo(List2.retrieve()) < 0) {                   //
				List1.findnext();
				i++;
			} else {
				List2.findnext();
				j++;
			}

		}
		return result;

	}


		public LinkedList<T> processOrQuery(String word1, String word2) {
		
		LinkedList<T> list1 = searchToList(word1.toLowerCase());
		LinkedList<T> list2 = searchToList(word2.toLowerCase());
		LinkedList<T> result = new LinkedList<T>();

		list1.findfirst();
		list2.findfirst();
		
		int i = 0;
		int j = 0;
		int size1 =list1.size();
		int size2 =list2.size();
		
		while (i < size1|| j < size2) {
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

	



}
