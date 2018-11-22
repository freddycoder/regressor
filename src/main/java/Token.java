public class Token {
	public Token(String content, TokenType type) {
		this.value = new String(content);
		this.type = type;
	}
	public final String value;
	public final TokenType type;
}

enum TokenType {
	OPENCURLY,
	CLOSECURLY,
	STRING,
	INT,
	DOUBLE,
	ARRAY,
	JSON,
	COMMA,
	COLON,
};