public class Lexer {
	public Lexer(String text) {
		this.m_text = text;
	}
	
	public Token GetNextToken() {
		Token t = null;
		
		SkipWhiteSpace();
		
		if (!IsEndOfText()) {
			switch (CurrentChar()) {
			case '{':
				t = new Token("{", TokenType.OPENCURLY);
				break;
			case '}':
				t = new Token("}", TokenType.CLOSECURLY);
				break;
			case '"':
				t = new Token(GetText(), TokenType.STRING);
				break;
			case ',':
				t = new Token(",", TokenType.COMMA);
				break;
			case ':':
				t = new Token(":", TokenType.COLON);
				break;
			default:
				if (Character.isDigit(CurrentChar())) {
					t = new Token(GetNumber(), TokenType.INT);
				}
				break;
			}
		}
		
		this.m_position++;
		
		return t;
	}
	
	private String GetNumber() {
		
		StringBuffer sb = new StringBuffer();
		
		while (Character.isDigit(CurrentChar())) {
			sb.append(CurrentChar());
			this.m_position++;
		}
		
		return sb.toString();
	}
	
	private String GetText() {
		if (CurrentChar() == '"') {
			this.m_position++;
		}
		
		StringBuffer sb = new StringBuffer();
		
		while (CurrentChar() != '"') {
			sb.append(CurrentChar());
			this.m_position++;
		}
		
		return sb.toString();
	}
	
	private void SkipWhiteSpace() {
		while (!IsEndOfText() && Character.isWhitespace(CurrentChar())) {
			this.m_position++;
		}
	}
	
	private boolean IsEndOfText() {
		return this.m_position == this.m_text.length();
	}
	
	private char CurrentChar() {
		return this.m_text.charAt(this.m_position);
	}
	
	private String m_text;
	private int m_position;
}
