public class QueryUtils {
    
    public static String[] tokenizeQuery(String query) {
        return query.split("\\s+");
    }

    public static int precedence(String op) {
		if (op.equalsIgnoreCase("AND"))
			return 2;
		if (op.equalsIgnoreCase("OR"))
			return 1;
		return 0;
	}
}