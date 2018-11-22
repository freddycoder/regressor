public class JsonObject {

    public final TokenType type;
    public final Object value;

    public JsonObject(int integer) {
        type = TokenType.INT;
        value = integer;
    }

    public JsonObject(double floatingPoint) {
        type = TokenType.DOUBLE;
        value = floatingPoint;
    }

    public JsonObject(String string) {
        type = TokenType.STRING;
        value = string;
    }

    public JsonObject(JsonObject[] array) {
        type = TokenType.ARRAY;
        value = array;
    }

    public JsonObject(JsonObject json) {
        type = TokenType.JSON;
        value = json;
    }

}
