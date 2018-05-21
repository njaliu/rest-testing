package rest.parseMethod;
import java.util.ArrayList;

public class MetaData
 {
	private String kind;
	private ArrayList<String> tables;
	private ArrayList<String> columns;
	
	public String getkind() {
		return kind;
	}
	
	public void addTable(String table) {
		if(tables == null) {
			tables = new ArrayList<>();
		}
		tables.add(table);
	}
	
	public void addColumns(String column) {
		if(columns == null) {
			columns = new ArrayList<>();
		}
		for(String col:columns) {
			if(col.equals(column))
				return;
		}
		columns.add(column);
	}

	public void setKind(String string) {
		kind = string;
	}
	
	@Override
	public String toString() {
		StringBuilder strB = new StringBuilder();
		strB.append("\"");
		strB.append(kind);
		strB.append("#_#");
		strB.append(tables);
		strB.append("#_#");
		strB.append(columns);
		strB.append("\"");
		return strB.toString();
	}
}
