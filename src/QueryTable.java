import net.sf.jsqlparser.schema.Table;

public class QueryTable {
	Table table;
	
	public QueryTable(Table table) {
		this.table = table;
	}
	
	public String toString() {
		return table.getName();
	}
}
