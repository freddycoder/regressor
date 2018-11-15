public class Parser {
	public Parser(String p_jsonText) {
		this.m_lexer = new Lexer(p_jsonText);
		this.m_currentToken = this.m_lexer.GetNextToken();
	}
	
	public StockEntry Parse() throws Exception {
		StockEntry se = null;
		
		if (this.m_currentToken != null) {
			this.Eat(TokenType.OPENCURLY);
			
			se = new StockEntry();
			
			this.Eat(TokenType.STRING);
			this.Eat(TokenType.COLON);
			this.Eat(TokenType.OPENCURLY);
			
			// Parse MetaData
			while (this.m_currentToken.Type() == TokenType.STRING) {
				String key = this.m_currentToken.value();
				this.Eat(TokenType.STRING);
				this.Eat(TokenType.COLON);
				se.MetaData.put(key, this.m_currentToken.value());
				this.Eat(TokenType.STRING);
				
				if (this.m_currentToken.Type() == TokenType.COMMA) {
					this.Eat(TokenType.COMMA);
				}
			}
			
			this.Eat(TokenType.CLOSECURLY);
			this.Eat(TokenType.COMMA);
			this.Eat(TokenType.STRING);
			this.Eat(TokenType.COLON);
			this.Eat(TokenType.OPENCURLY);
			
			// Parse TimeSeries
			while (this.m_currentToken.Type() == TokenType.STRING) {
				String key = this.m_currentToken.value();
				this.Eat(TokenType.STRING);
				this.Eat(TokenType.COLON);
				this.Eat(TokenType.OPENCURLY);
				se.TimeSeries.put(key, new MetaData());
				while (this.m_currentToken.Type() == TokenType.STRING) {
					String subkey = this.m_currentToken.value();
					this.Eat(TokenType.STRING);
					this.Eat(TokenType.COLON);
					se.TimeSeries.get(key).put(subkey, this.m_currentToken.value());
					
					if (this.m_currentToken.Type() == TokenType.STRING) {
						this.Eat(TokenType.STRING);
					}
					else {
						this.Eat(TokenType.INT);
					}
					
					if (this.m_currentToken.Type() == TokenType.COMMA) {
						this.Eat(TokenType.COMMA);
					}
				}
				
				this.Eat(TokenType.CLOSECURLY);
				
				if (this.m_currentToken.Type() == TokenType.COMMA) {
					this.Eat(TokenType.COMMA);
				}
			}
			
			this.Eat(TokenType.CLOSECURLY);
		}
		this.Eat(TokenType.CLOSECURLY);
		
		return se;
	}
	
	private void Eat(TokenType p_type) throws Exception {
		if (this.m_currentToken.Type() == p_type) {
			this.m_currentToken = this.m_lexer.GetNextToken();
		}
		else {
			throw new Exception("Error while eating current token. Current token is " + this.m_currentToken.toString() + " but eat reveived " + p_type);
		}
	}
	
	private Lexer m_lexer;
	private Token m_currentToken;
}
