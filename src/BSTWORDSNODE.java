public class BSTWORDSNODE<T> {
    public String key;
	public int freq =0;
	public BSTWORDSNODE<T> left, right;
	
	/** Creates a new instance of BSTNode */



    public BSTWORDSNODE(String k){
		key = k;
		freq++;
		left = right = null;
	}
}
