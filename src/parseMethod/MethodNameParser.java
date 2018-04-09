package parseMethod;
public class MethodNameParser {
	private String methodName;
	private MetaData rmd;
	private MetaData wmd;
	private String table;
	String WHEREKEYS = "And|Or|Is|Equals|Like|Not";
	String SELECTKEYS = "Distinct|First|Top|find";
	String DELETEKEYS = " ";
	
	MetaData getRmd() {
		return rmd;
	}
	
	MetaData getWmd() {
		return wmd;
	}
	
	public MethodNameParser(String methodName,String classSign) {
		this.methodName = methodName;
		String [] strs = classSign.split(";")[1].split("/");
		this.table = strs[strs.length - 1];
	}

	public void parse() {
		if(methodName.matches("[find|query|read|count|get].*By.*")) {
			rmd = new MetaData();
			rmd.setKind("read");
			rmd.addTable(table);
			String whereClause = methodName.split("By")[1];
			parseWhereClause(whereClause);
			String selectClause = methodName.split("By")[0];
			parserSelectClause(selectClause);
		}
		if(methodName.matches("delete.*By.*")) {
			rmd = new MetaData();
			rmd.setKind("read");
			rmd.addTable(table);
			String whereClause = methodName.split("By")[1];
			parseWhereClause(whereClause);
			
			wmd = new MetaData();
			wmd.setKind("write");
			wmd.addTable(table);
			String deleteClause = methodName.split("By")[0];
			parseDeleteClause(deleteClause);
		}
	}

	private void parseDeleteClause(String deleteClause) {
		String[] properties = deleteClause.split(DELETEKEYS);
		if(properties.length == 0 && isPropertyName(deleteClause)) {
			rmd.addColumns("all");
		}
		else {
			for(String p:properties) {
				if(p != null && isPropertyName(deleteClause)) {
					rmd.addColumns(deleteClause);
				}
			}
		}
	}

	private void parserSelectClause(String selectClause) {
		String[] properties = selectClause.split(SELECTKEYS);
		if(properties.length == 0 && isPropertyName(selectClause)) {
			rmd.addColumns("all");
		}
		else {
			for(String p:properties) {
				if(p != null && isPropertyName(selectClause)) {
					rmd.addColumns(selectClause);
				}
			}
		}
	}

	private void parseWhereClause(String whereClause) {
		String[] properties = whereClause.split(WHEREKEYS);
		if(properties.length == 0 && isPropertyName(whereClause)) {
			rmd.addColumns("all");
		}
		else{
			for(String p:properties) {
				if(p != null && isPropertyName(whereClause)) {
					rmd.addColumns(whereClause);
				}
			}
		}
	}

	boolean isPropertyName(String property) {
		return property.matches("^[a-zA-Z\\$_][a-zA-Z\\d_]*$");
	}
}
