public class Token {
	public Token(String content, TokenType type) {
		this.m_value = new String(content);
		this.m_type = type;
	}
	public String value() {
		return this.m_value;
	}
	public TokenType Type() {
		return this.m_type;
	}
	public String toString() {
		return "{'" + this.m_value + "': '" + this.m_type + "'}";
	}
	private String m_value;
	private TokenType m_type;
}

enum TokenType {
	OPENCURLY,
	CLOSECURLY,
	STRING,
	INT,
	COMMA,
	COLON,
};