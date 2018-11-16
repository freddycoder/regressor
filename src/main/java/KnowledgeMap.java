import java.util.HashMap;
import java.util.Map;

public class KnowledgeMap extends HashMap<String, Double> {

    public KnowledgeMap() {
        super();
    }

    public void addText(String text) {
        String[] words = text.trim().toLowerCase().split("\\s");
        for (String word : words) {
            if (!this.containsKey(word)) {
                this.put(word, 0.0);
            }
        }
    }

    public void addorUpdateScore(String key, Double value) {
        key = key.trim().toLowerCase();
        if (containsKey(key)) {
            double actual = this.get(key);
            this.replace(key, actual + value);
        } else {
            this.put(key, value);
        }
    }
}
