import java.util.TreeMap;
import java.util.Map.Entry;

public class MetaData extends TreeMap<String, String> {
	public MetaData() {
		super();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Entry<String, String> kp : this.entrySet()) {
			sb.append("\t" + kp.getKey() + ": " + kp.getValue() + "\n");
		}
		
		return sb.toString();
	}
}
