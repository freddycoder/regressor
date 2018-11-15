import java.util.TreeMap;
import java.util.Map.Entry;

public class TimeSeries extends TreeMap<String, MetaData> {
	public TimeSeries() {
		super();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		boolean firstTabDone = false;
		
		for (Entry<String, MetaData> kp : this.entrySet()) {
			sb.append(kp.getKey() + "\n");
			if (firstTabDone) {
				sb.append("\t" + kp.getValue().toString() + "\n");
			}
			else {
				sb.append(kp.getValue().toString() + "\n");
			}
		}
		
		return sb.toString();
	}
}
