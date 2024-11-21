public class BSTWORDS {
    BSTWORDSNODE root, current;
	
	/** Creates a new instance of BST */
	public BSTWORDS() {
		root = current = null;
	}
	
	public boolean empty() {
		return root == null;
	}
	
	public boolean full() {
		return false;
	}
	
	public int retrieveFreq () {
		return current.freq;
	}
    public boolean findKey(String targetKey) {
		BSTWORDSNODE p = root, q = root;

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

    	// new method for a bst with the key as data
	public boolean insert(String k){
		BSTWORDSNODE p, q = current;
		if (findKey(k)){
            current.freq++;
			return false;
		}
		p = new BSTWORDSNODE(k);
		if (empty()){
			root = current = p;
			return true;
		}else {
			if (current.key.compareTo(k) > 0)
			current.left = p;
		else
			current.right = p;
		current = p;
		return true;
	}
	}




	}



