import java.security.InvalidParameterException;

public class StockEntry {
	public MetaData MetaData;
	public TimeSeries TimeSeries;
	
	public StockEntry() {
		this.MetaData = new MetaData();
		this.TimeSeries = new TimeSeries();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("MetaData:\n");
		sb.append(this.MetaData.toString());
		sb.append("TimeSeries:\n");
		sb.append(this.TimeSeries.toString());
		
		return sb.toString();
	}
	
	public double[] getArrayOf(String p_subKeyTimeSeries) {
		assertKeyIsPresent(p_subKeyTimeSeries);
		
		double[] v = new double[this.TimeSeries.size()];
		
		int i = 0;
		for (MetaData data : this.TimeSeries.values()) {
			v[i++] = Double.parseDouble(data.get(p_subKeyTimeSeries));
		}
		
		return v;
	}
	
	private void assertKeyIsPresent(String p_subKeyTimeSeries) {
		if (!this.TimeSeries.firstEntry().getValue().containsKey(p_subKeyTimeSeries)) {
			StringBuilder sb = new StringBuilder();
			sb.append(System.lineSeparator());
			sb.append("La clef doit être dans le dictionnaire. Voici les cles possible : ");
			sb.append(System.lineSeparator());
			for (Object key : this.TimeSeries.firstEntry().getValue().keySet()) {
				sb.append(key.toString());
				sb.append(System.lineSeparator());
			}
			throw new InvalidParameterException(sb.toString());
		}
	}
}
