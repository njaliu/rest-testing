package rest.parseMethod;
import java.io.StringReader;
import java.util.ArrayList;

import org.eclipse.persistence.jpa.jpql.parser.Expression;
import org.eclipse.persistence.jpa.jpql.parser.JPQLExpression;
import org.eclipse.persistence.jpa.jpql.parser.JPQLGrammar2_1;
import org.eclipse.persistence.jpa.jpql.parser.SelectStatement;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;

import net.sf.jsqlparser.statement.Statement;

import net.sf.jsqlparser.statement.select.Select;

public class NativeQueryParser extends AbQueryParser{
	QueryMetaData qmd;
	
	public NativeQueryParser(String query) {
		super(query);
		qmd = new QueryMetaData();
	}
	
		public void parseQuery() {
		CCJSqlParserManager pm = new CCJSqlParserManager();
		Statement statement = null;
//		query = "SELECT * FROM MY_TABLE1, MY_TABLE2, (SELECT * FROM MY_TABLE3) LEFT OUTER JOIN MY_TABLE4 "+
//				" WHERE ID = (SELECT MAX(ID) FROM MY_TABLE5) AND ID2 IN (SELECT * FROM MY_TABLE6)" ;
		try {
			statement = pm.parse(new StringReader(query));
		} catch (JSQLParserException e) {
			e.printStackTrace();
		}
		if(statement instanceof Select) {
			qmd.kind = QueryKind.SELECT;
			Select selectStatement = (Select) statement;
			NativeSelectVisitor sp = new NativeSelectVisitor();
			sp.parseSelect(selectStatement,qmd);
		}
	}

	@Override
	public ArrayList<MetaData> dealWithData() {
		return null;
	}
	}
