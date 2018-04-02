import java.util.ArrayList;

public class MetaData {
	private String kind;
	private ArrayList<String> tables;
	private ArrayList<String> columns;
	
	public String getkind() {
		return kind;
	}
	
	public void addTable(String table) {
		if(tables == null) {
			tables = new ArrayList<String>();
		}
		tables.add(table);
	}
	
	public void addColumns(String column) {
		if(columns == null) {
			columns = new ArrayList<String>();
		}
		columns.add(column);
	}

	public void setKind(String string) {
		// TODO Auto-generated method stub
		kind = string;
	}
	
	@Override
	public String toString() {
		StringBuilder strB = new StringBuilder();
		strB.append("{");
		strB.append(kind);
		strB.append(",");
		strB.append(tables);
		strB.append(",");
		strB.append(columns);
		strB.append("}");
		return strB.toString();
	}
	
}
