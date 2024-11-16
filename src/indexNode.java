public class indexNode<T> {
    public int docId;
    public Node<T> data;
	public indexNode next;

	public indexNode () {
        docId = -1;
		data = null;
		next = null;
	}

	public indexNode (int id,T word) {
        docId = id;
		data = new Node<T>(word);
		next = null;
	}

}
