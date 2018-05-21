package rest.parseMethod;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.persistence.jpa.jpql.parser.Expression;
import org.eclipse.persistence.jpa.jpql.utility.iterable.ListIterable;

public class JPQueryMetaData {
	private QueryKind kind;
	/** Varible->TableName. */
	private HashMap<String,String> tables;
	private ArrayList<String> readColums;
	private ArrayList<String> writeColums;
	
	public void setKind(QueryKind select) {
		kind = select;
	}

	public void addTable(Expression s, Expression v) {
		if(tables == null) {
			tables = new HashMap<>();
		}
		tables.put(v.toString(), s.toString());
	}

	public void addReadColums(String paths) {
		if(readColums == null) {
			readColums = new ArrayList<>();
		}
		readColums.add(paths);
	}

	public void addWriteColums(String string) {
		if(writeColums == null) {
			writeColums = new ArrayList<>();
		}
		writeColums.add(string);
	}

	public ArrayList<MetaData> dealWithData() {
		ArrayList<MetaData> results = new ArrayList<>();
		if(readColums != null) {
			MetaData md = new MetaData();
			md.setKind("read");
			for(String column:readColums) {
				if(column.contains(".")) {
					String v = column.split("\\.")[0];
					String table = tables.get(v);
					assert(table != null);         //该variable对应的table不能为空
					md.addTable(table);
					md.addColumns(column.replaceFirst(v, table));
				}else {
					String table = tables.get(column);
					md.addTable(table);
					md.addColumns(table);
				}
			}
			results.add(md);
		}
		if(writeColums != null) {
			MetaData md = new MetaData();
			md.setKind("write");
			for(String column:writeColums) {
				if(column.contains(".")) {
					String v = column.split(".")[0];
					String table = tables.get(v);
					assert(table != null);         //该variable对应的table不能为空
					md.addColumns(column.replaceFirst(v, table));
				}else {
					String table = tables.get(column);
					md.addColumns(table);
				}
			}
			results.add(md);
		}
		
		return results;
	}
}
