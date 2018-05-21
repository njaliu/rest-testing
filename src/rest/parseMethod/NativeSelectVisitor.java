package rest.parseMethod;
import net.sf.jsqlparser.expression.AllComparisonExpression;
import net.sf.jsqlparser.expression.AnyComparisonExpression;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.CaseExpression;
import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.InverseExpression;
import net.sf.jsqlparser.expression.JdbcParameter;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.TimeValue;
import net.sf.jsqlparser.expression.TimestampValue;
import net.sf.jsqlparser.expression.WhenClause;
import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseAnd;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseOr;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseXor;
import net.sf.jsqlparser.expression.operators.arithmetic.Concat;
import net.sf.jsqlparser.expression.operators.arithmetic.Division;
import net.sf.jsqlparser.expression.operators.arithmetic.Multiplication;
import net.sf.jsqlparser.expression.operators.arithmetic.Subtraction;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.Between;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.Matches;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.FromItemVisitor;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItemVisitor;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.SubJoin;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.Union;

class NativeSelectVisitor implements SelectVisitor, SelectItemVisitor, FromItemVisitor, ExpressionVisitor{
		QueryMetaData qmd;
		
		public void parseSelect(Select select, QueryMetaData qmd) {
			select.getSelectBody().accept(this);
			this.qmd = qmd;
		}
		
		/** SelectVisitor接口实现. */
		public void visit(PlainSelect plainSelect) {
			plainSelect.getFromItem().accept(this);
			
			if(plainSelect.getWhere() != null) {
				plainSelect.getWhere().accept(this);
			}
		}
		
		public void visit(Union union) {
			//for(Iterator<E> iter=union.getPlainSelects().iterator(); iter.hasNext();)
		}
		
		/** FromItemVisitor接口实现. */
		public void visit(SubJoin subjoin) {
		}
		
		public void visit(SubSelect subselect) {
		}
		
		public void visit(Table tablename) {
			qmd.addTable(tablename);
		}
		
		/** ExpressionVisitor接口实现. */
		public void 	visit(Addition addition) {
			visitBinaryExpression(addition);
		}
        
		public void	visit(AllComparisonExpression allComparisonExpression) {
			allComparisonExpression.GetSubSelect().getSelectBody().accept(this);
		}
		           
		public void	visit(AndExpression andExpression) {
			visitBinaryExpression(andExpression);
		}
		           
		public void	visit(AnyComparisonExpression anyComparisonExpression) {
			anyComparisonExpression.GetSubSelect().getSelectBody().accept(this);
		}
		           
		public void	visit(Between between) {
			between.getLeftExpression().accept(this);
			between.getBetweenExpressionStart().accept(this);
			between.getBetweenExpressionEnd().accept(this);
		}
		           
		public void	visit(CaseExpression caseExpression) {
			caseExpression.getElseExpression().accept(this);
			caseExpression.getSwitchExpression().accept(this);
			for(Object o:caseExpression.getWhenClauses()) {
				if(o instanceof WhenClause) {
					WhenClause whenClause = (WhenClause) o;
					whenClause.accept(this);
				}
			}
		}
		           
		public void	visit(Column tableColumn) {
			 qmd.addColumn(tableColumn);
		 }
		           
		public void	visit(DateValue dateValue) {
		}
		           
		public void	visit(Division division) {
			 visitBinaryExpression(division);
		 }
		           
		public void	visit(DoubleValue doubleValue) {
		}
		           
		public void	visit(EqualsTo equalsTo) {
			 visitBinaryExpression(equalsTo);
		 }
		           
		public void	visit(ExistsExpression existsExpression) {
			existsExpression.accept(this);
		}
		           
		public void	visit(Function function) {
			function.accept(this);
		}
		           
		public void	visit(GreaterThan greaterThan) {
			visitBinaryExpression(greaterThan);
		}
		           
		public void	visit(GreaterThanEquals greaterThanEquals) {
			visitBinaryExpression(greaterThanEquals);
		}
		           
		public void	visit(InExpression inExpression) {
		}
		           
		public void	visit(InverseExpression inverseExpression) {
			inverseExpression.getExpression().accept(this);
		}
		           
		public void	visit(IsNullExpression isNullExpression) {
			isNullExpression.getLeftExpression().accept(this);
		}
		           
		public void	visit(JdbcParameter jdbcParameter) {
		}
		           
		public void	visit(LikeExpression likeExpression) {
			visitBinaryExpression(likeExpression);
		}
		           
		public void	visit(LongValue longValue) {
		}
		           
		public void	visit(MinorThan minorThan) {
			visitBinaryExpression(minorThan);
		}
		           
		public void	visit(MinorThanEquals minorThanEquals) {
			visitBinaryExpression(minorThanEquals);
		}
		           
		public void	visit(Multiplication multiplication) {
			visitBinaryExpression(multiplication);
		}
		           
		public void	visit(NotEqualsTo notEqualsTo) {
			visitBinaryExpression(notEqualsTo);
		}
		           
		public void	visit(NullValue nullValue) {
		 }
		           
		public void	visit(OrExpression orExpression) {
			visitBinaryExpression(orExpression);
		}
		           
		public void	visit(Parenthesis parenthesis) {
			parenthesis.getExpression().accept(this);
		}
		           
		public void	visit(StringValue stringValue) {
		}
		           
/** Public void visit(SubSelect subSelect). */
		           
		public void	visit(Subtraction subtraction) {
			visitBinaryExpression(subtraction);
		}
		           
		public void	visit(TimestampValue timestampValue) {
		}
		           
		public void	visit(TimeValue timeValue) {
		}
		           
		public void	visit(WhenClause whenClause) {
			whenClause.getWhenExpression().accept(this);
			whenClause.getThenExpression().accept(this);
		}
	   /** 自定义函数. */
		public void visitBinaryExpression(BinaryExpression be) {
			be.getLeftExpression().accept(this);
			be.getRightExpression().accept(this);
		}

		@Override
		public void visit(Concat arg0) {
		}
	
		@Override
		public void visit(Matches arg0) {
		}
	
		@Override
		public void visit(BitwiseAnd arg0) {
		}
	
		@Override
		public void visit(BitwiseOr arg0) {
		}
	
		@Override
		public void visit(BitwiseXor arg0) {
		}
		/** SelectItemVisitor接口实现. */
		@Override
		public void visit(AllColumns arg0) {
		}

		@Override
		public void visit(AllTableColumns arg0) {
		}

		@Override
		public void visit(SelectExpressionItem arg0) {
		}
	}
