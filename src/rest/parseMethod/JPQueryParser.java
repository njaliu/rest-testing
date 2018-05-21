package rest.parseMethod;
import java.util.ArrayList;

import org.eclipse.persistence.jpa.jpql.parser.DeleteStatement;
import org.eclipse.persistence.jpa.jpql.parser.Expression;
import org.eclipse.persistence.jpa.jpql.parser.JPQLExpression;
import org.eclipse.persistence.jpa.jpql.parser.JPQLGrammar2_1;
import org.eclipse.persistence.jpa.jpql.parser.SelectStatement;
import org.eclipse.persistence.jpa.jpql.parser.UpdateStatement;

public class JPQueryParser extends AbQueryParser{
	JPQueryMetaData jqmd;
	
	public JPQueryParser(String query) {
		super(query);
		jqmd = new JPQueryMetaData();
	}
	
	public void parseQuery() {
		JPQLExpression jpql = new JPQLExpression(query,new JPQLGrammar2_1());
		Expression jpqlStatement = jpql.getQueryStatement();
		if(jpqlStatement instanceof SelectStatement) {
			jqmd.setKind(QueryKind.SELECT);
			SelectStatement select = (SelectStatement) jpqlStatement;
			select.accept(new JPQuerySelectVisitor(jqmd));
		}else if(jpqlStatement instanceof UpdateStatement) {
			jqmd.setKind(QueryKind.UPDATE);
			UpdateStatement update = (UpdateStatement) jpqlStatement;
			update.accept(new JPQueryUpdateVisitor(jqmd));
		}else if(jpqlStatement instanceof DeleteStatement) {
			jqmd.setKind(QueryKind.DELETE);
			DeleteStatement delete = (DeleteStatement) jpqlStatement;
			delete.accept(new JPQueryDeleteVisitor(jqmd));
		}else {
			System.err.println("not a JPQL statement");
		}
	}

	@Override
	public ArrayList<MetaData> dealWithData() {
		return jqmd.dealWithData();
	}
}
