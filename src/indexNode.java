public class indexNode<T,U> {
    public T docId;
    public BSTWORDS data;
	public indexNode<T,U> next;

	public indexNode () {
        docId = null;
		data = null;
		next = null;
	}

	public indexNode (T id,U word) {
        docId = id;
		data = new BSTWORDS();
		data.insert(word.toString());
		next = null;
	}

}
