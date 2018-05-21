package rest.parseMethod;
import java.util.ArrayList;

import org.eclipse.persistence.jpa.jpql.parser.AbsExpression;
import org.eclipse.persistence.jpa.jpql.parser.AbstractSchemaName;
import org.eclipse.persistence.jpa.jpql.parser.AdditionExpression;
import org.eclipse.persistence.jpa.jpql.parser.AllOrAnyExpression;
import org.eclipse.persistence.jpa.jpql.parser.AndExpression;
import org.eclipse.persistence.jpa.jpql.parser.ArithmeticFactor;
import org.eclipse.persistence.jpa.jpql.parser.AvgFunction;
import org.eclipse.persistence.jpa.jpql.parser.BadExpression;
import org.eclipse.persistence.jpa.jpql.parser.BetweenExpression;
import org.eclipse.persistence.jpa.jpql.parser.CaseExpression;
import org.eclipse.persistence.jpa.jpql.parser.CoalesceExpression;
import org.eclipse.persistence.jpa.jpql.parser.CollectionExpression;
import org.eclipse.persistence.jpa.jpql.parser.CollectionMemberDeclaration;
import org.eclipse.persistence.jpa.jpql.parser.CollectionMemberExpression;
import org.eclipse.persistence.jpa.jpql.parser.CollectionValuedPathExpression;
import org.eclipse.persistence.jpa.jpql.parser.ComparisonExpression;
import org.eclipse.persistence.jpa.jpql.parser.ConcatExpression;
import org.eclipse.persistence.jpa.jpql.parser.ConstructorExpression;
import org.eclipse.persistence.jpa.jpql.parser.CountFunction;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.eclipse.persistence.jpa.jpql.parser.DeleteClause;
import org.eclipse.persistence.jpa.jpql.parser.DeleteStatement;
import org.eclipse.persistence.jpa.jpql.parser.DivisionExpression;
import org.eclipse.persistence.jpa.jpql.parser.EmptyCollectionComparisonExpression;
import org.eclipse.persistence.jpa.jpql.parser.EntityTypeLiteral;
import org.eclipse.persistence.jpa.jpql.parser.EntryExpression;
import org.eclipse.persistence.jpa.jpql.parser.ExistsExpression;
import org.eclipse.persistence.jpa.jpql.parser.Expression;
import org.eclipse.persistence.jpa.jpql.parser.ExpressionVisitor;
import org.eclipse.persistence.jpa.jpql.parser.FromClause;
import org.eclipse.persistence.jpa.jpql.parser.FunctionExpression;
import org.eclipse.persistence.jpa.jpql.parser.GroupByClause;
import org.eclipse.persistence.jpa.jpql.parser.HavingClause;
import org.eclipse.persistence.jpa.jpql.parser.IdentificationVariable;
import org.eclipse.persistence.jpa.jpql.parser.IdentificationVariableDeclaration;
import org.eclipse.persistence.jpa.jpql.parser.InExpression;
import org.eclipse.persistence.jpa.jpql.parser.IndexExpression;
import org.eclipse.persistence.jpa.jpql.parser.InputParameter;
import org.eclipse.persistence.jpa.jpql.parser.JPQLExpression;
import org.eclipse.persistence.jpa.jpql.parser.Join;
import org.eclipse.persistence.jpa.jpql.parser.KeyExpression;
import org.eclipse.persistence.jpa.jpql.parser.KeywordExpression;
import org.eclipse.persistence.jpa.jpql.parser.LengthExpression;
import org.eclipse.persistence.jpa.jpql.parser.LikeExpression;
import org.eclipse.persistence.jpa.jpql.parser.LocateExpression;
import org.eclipse.persistence.jpa.jpql.parser.LowerExpression;
import org.eclipse.persistence.jpa.jpql.parser.MaxFunction;
import org.eclipse.persistence.jpa.jpql.parser.MinFunction;
import org.eclipse.persistence.jpa.jpql.parser.ModExpression;
import org.eclipse.persistence.jpa.jpql.parser.MultiplicationExpression;
import org.eclipse.persistence.jpa.jpql.parser.NotExpression;
import org.eclipse.persistence.jpa.jpql.parser.NullComparisonExpression;
import org.eclipse.persistence.jpa.jpql.parser.NullExpression;
import org.eclipse.persistence.jpa.jpql.parser.NullIfExpression;
import org.eclipse.persistence.jpa.jpql.parser.NumericLiteral;
import org.eclipse.persistence.jpa.jpql.parser.ObjectExpression;
import org.eclipse.persistence.jpa.jpql.parser.OnClause;
import org.eclipse.persistence.jpa.jpql.parser.OrExpression;
import org.eclipse.persistence.jpa.jpql.parser.OrderByClause;
import org.eclipse.persistence.jpa.jpql.parser.OrderByItem;
import org.eclipse.persistence.jpa.jpql.parser.RangeVariableDeclaration;
import org.eclipse.persistence.jpa.jpql.parser.ResultVariable;
import org.eclipse.persistence.jpa.jpql.parser.SelectClause;
import org.eclipse.persistence.jpa.jpql.parser.SelectStatement;
import org.eclipse.persistence.jpa.jpql.parser.SimpleFromClause;
import org.eclipse.persistence.jpa.jpql.parser.SimpleSelectClause;
import org.eclipse.persistence.jpa.jpql.parser.SimpleSelectStatement;
import org.eclipse.persistence.jpa.jpql.parser.SizeExpression;
import org.eclipse.persistence.jpa.jpql.parser.SqrtExpression;
import org.eclipse.persistence.jpa.jpql.parser.StateFieldPathExpression;
import org.eclipse.persistence.jpa.jpql.parser.StringLiteral;
import org.eclipse.persistence.jpa.jpql.parser.SubExpression;
import org.eclipse.persistence.jpa.jpql.parser.SubstringExpression;
import org.eclipse.persistence.jpa.jpql.parser.SubtractionExpression;
import org.eclipse.persistence.jpa.jpql.parser.SumFunction;
import org.eclipse.persistence.jpa.jpql.parser.TreatExpression;
import org.eclipse.persistence.jpa.jpql.parser.TrimExpression;
import org.eclipse.persistence.jpa.jpql.parser.TypeExpression;
import org.eclipse.persistence.jpa.jpql.parser.UnknownExpression;
import org.eclipse.persistence.jpa.jpql.parser.UpdateClause;
import org.eclipse.persistence.jpa.jpql.parser.UpdateItem;
import org.eclipse.persistence.jpa.jpql.parser.UpdateStatement;
import org.eclipse.persistence.jpa.jpql.parser.UpperExpression;
import org.eclipse.persistence.jpa.jpql.parser.ValueExpression;
import org.eclipse.persistence.jpa.jpql.parser.WhenClause;
import org.eclipse.persistence.jpa.jpql.parser.WhereClause;
import org.eclipse.persistence.jpa.jpql.utility.iterable.ListIterable;

public class JPQuerySelectVisitor implements ExpressionVisitor{
	private JPQueryMetaData jqmd;
	
	public JPQuerySelectVisitor(JPQueryMetaData jqmd) {
		this.jqmd = jqmd;
	}
	
	@Override
	public void visit(AbsExpression expression) {
	}

	@Override
	public void visit(AbstractSchemaName expression) {
	}

	@Override
	public void visit(AdditionExpression expression) {
	}

	@Override
	public void visit(AllOrAnyExpression expression) {
	}

	@Override
	public void visit(AndExpression expression) {
	}

	@Override
	public void visit(ArithmeticFactor expression) {
	}

	@Override
	public void visit(AvgFunction expression) {
	}

	@Override
	public void visit(BadExpression expression) {
	}

	@Override
	public void visit(BetweenExpression expression) {
	}

	@Override
	public void visit(CaseExpression expression) {
	}

	@Override
	public void visit(CoalesceExpression expression) {
	}

	@Override
	public void visit(CollectionExpression expression) {
	}

	@Override
	public void visit(CollectionMemberDeclaration expression) {
	}

	@Override
	public void visit(CollectionMemberExpression expression) {
	}

	@Override
	public void visit(CollectionValuedPathExpression expression) {
	}

	@Override
	public void visit(ComparisonExpression expression) {
	}

	@Override
	public void visit(ConcatExpression expression) {
	}

	@Override
	public void visit(ConstructorExpression expression) {
	}

	@Override
	public void visit(CountFunction expression) {
	}

	@Override
	public void visit(DateTime expression) {
	}

	@Override
	public void visit(DeleteClause expression) {
	}

	@Override
	public void visit(DeleteStatement expression) {
	}

	@Override
	public void visit(DivisionExpression expression) {
	}

	@Override
	public void visit(EmptyCollectionComparisonExpression expression) {
	}

	@Override
	public void visit(EntityTypeLiteral expression) {
	}

	@Override
	public void visit(EntryExpression expression) {
	}

	@Override
	public void visit(ExistsExpression expression) {
	}

	@Override
	public void visit(FromClause expression) {
		 Expression a =  expression.getDeclaration();
		 expression.getDeclaration().accept(this);
	}

	@Override
	public void visit(FunctionExpression expression) {
	}

	@Override
	public void visit(GroupByClause expression) {
	}

	@Override
	public void visit(HavingClause expression) {
	}

	@Override
	public void visit(IdentificationVariable expression) {
		String a = expression.getText();
		jqmd.addReadColums(a);
	}

	@Override
	public void visit(IdentificationVariableDeclaration expression) {
		Expression a = expression.getRangeVariableDeclaration();
		expression.getRangeVariableDeclaration().accept(this);
	}

	@Override
	public void visit(IndexExpression expression) {
	}

	@Override
	public void visit(InExpression expression) {
	}

	@Override
	public void visit(InputParameter expression) {
	}

	@Override
	public void visit(Join expression) {
	}

	@Override
	public void visit(JPQLExpression expression) {
	}

	@Override
	public void visit(KeyExpression expression) {
	}

	@Override
	public void visit(KeywordExpression expression) {
	}

	@Override
	public void visit(LengthExpression expression) {
	}

	@Override
	public void visit(LikeExpression expression) {
	}

	@Override
	public void visit(LocateExpression expression) {
	}

	@Override
	public void visit(LowerExpression expression) {
	}

	@Override
	public void visit(MaxFunction expression) {
	}

	@Override
	public void visit(MinFunction expression) {
	}

	@Override
	public void visit(ModExpression expression) {
	}

	@Override
	public void visit(MultiplicationExpression expression) {
	}

	@Override
	public void visit(NotExpression expression) {
	}

	@Override
	public void visit(NullComparisonExpression expression) {
	}

	@Override
	public void visit(NullExpression expression) {
	}

	@Override
	public void visit(NullIfExpression expression) {
	}

	@Override
	public void visit(NumericLiteral expression) {
	}

	@Override
	public void visit(ObjectExpression expression) {
	}

	@Override
	public void visit(OnClause expression) {
	}

	@Override
	public void visit(OrderByClause expression) {
	}

	@Override
	public void visit(OrderByItem expression) {
	}

	@Override
	public void visit(OrExpression expression) {
	}

	@Override
	public void visit(RangeVariableDeclaration expression) {
		Expression s = expression.getRootObject();
		Expression v = expression.getIdentificationVariable();
		jqmd.addTable(s,v);
	}

	@Override
	public void visit(ResultVariable expression) {
	}

	@Override
	public void visit(SelectClause expression) {
		Expression a = expression.getSelectExpression();
		 expression.getSelectExpression().accept(this);
	}

	@Override
	public void visit(SelectStatement expression) {
		 expression.getFromClause().accept(this);
		 expression.getSelectClause().accept(this);
		 expression.getWhereClause().accept(this);
	}

	@Override
	public void visit(SimpleFromClause expression) {
	}

	@Override
	public void visit(SimpleSelectClause expression) {
	}

	@Override
	public void visit(SimpleSelectStatement expression) {
	}

	@Override
	public void visit(SizeExpression expression) {
	}

	@Override
	public void visit(SqrtExpression expression) {
	}

	@Override
	public void visit(StateFieldPathExpression expression) {
//		Expression v = expression.getIdentificationVariable();
		jqmd.addReadColums(expression.toString());
	}

	@Override
	public void visit(StringLiteral expression) {
	}

	@Override
	public void visit(SubExpression expression) {
	}

	@Override
	public void visit(SubstringExpression expression) {
	}

	@Override
	public void visit(SubtractionExpression expression) {
	}

	@Override
	public void visit(SumFunction expression) {
	}

	@Override
	public void visit(TreatExpression expression) {
	}

	@Override
	public void visit(TrimExpression expression) {
	}

	@Override
	public void visit(TypeExpression expression) {
	}

	@Override
	public void visit(UnknownExpression expression) {
	}

	@Override
	public void visit(UpdateClause expression) {
	}

	@Override
	public void visit(UpdateItem expression) {
	}

	@Override
	public void visit(UpdateStatement expression) {
	}

	@Override
	public void visit(UpperExpression expression) {
	}

	@Override
	public void visit(ValueExpression expression) {
	}

	@Override
	public void visit(WhenClause expression) {
	}

	@Override
	public void visit(WhereClause expression) {
		Expression a = expression.getConditionalExpression();
		 expression.getConditionalExpression().accept(new JPQueryWhereClauseVisitor(jqmd));
	}
}
