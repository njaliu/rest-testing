import java.util.ArrayList;
import java.util.List;

import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;

public class QueryMetaData {
	QueryKind kind;
	List<QueryTable> tables;
	List<Column> readColumns;
	
	public QueryMetaData(){
		tables = new ArrayList<QueryTable>();
		readColumns = new ArrayList<Column>();
	}
	
	public void addTable(Table table) {
		//待处理：当添加相同table
		tables.add(new QueryTable(table));
	}
	
	public void addColumn(Column column) {
		readColumns.add(column);
	}
	
}

enum QueryKind{
	INSERT, SELECT, UPDATE, DELETE
}

