package parseMethod;
import java.util.ArrayList;

public abstract class AbQueryParser {
	String query;
	
	public AbQueryParser(String query) {
		this.query = query;
	}
	
	public abstract void parseQuery();

	public abstract ArrayList<MetaData> dealWithData();
}
