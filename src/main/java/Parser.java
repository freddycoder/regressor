import java.util.HashMap;
import java.util.Map;

public class Parser {
	public Parser(String p_jsonText) {
		this.lexer = new Lexer(p_jsonText);
		this.currentToken = this.lexer.GetNextToken();
	}

	public Map<String, JsonObject> GenericParse() throws Exception  {
		Map<String, JsonObject> jsonMap = null;

		if (currentToken != null) {
			Eat(TokenType.OPENCURLY);
			jsonMap = new HashMap<>();

			String key = currentToken.value;
			Eat(TokenType.STRING);
			Eat(TokenType.COLON);

			switch (currentToken.type) {
				case INT:
					jsonMap.put(key, new JsonObject(Integer.parseInt(currentToken.value)));
					break;
				case DOUBLE:
					jsonMap.put(key, new JsonObject(Double.parseDouble(currentToken.value)));
					break;
				case STRING:
					jsonMap.put(key, new JsonObject(currentToken.value));
					break;
				case ARRAY:

					break;
				case JSON:

					break;
			}

		}

		return jsonMap;
	}
	
	public StockEntry Parse() throws Exception {
		StockEntry se = null;
		
		if (currentToken != null) {
			Eat(TokenType.OPENCURLY);
			
			se = new StockEntry();
			
			Eat(TokenType.STRING);
			Eat(TokenType.COLON);
			Eat(TokenType.OPENCURLY);
			
			// Parse MetaData
			while (this.currentToken != null && currentToken.type == TokenType.STRING) {
				String key = this.currentToken.value;
				this.Eat(TokenType.STRING);
				this.Eat(TokenType.COLON);
				se.MetaData.put(key, this.currentToken.value);
				this.Eat(TokenType.STRING);
				
				if (this.currentToken.type == TokenType.COMMA) {
					this.Eat(TokenType.COMMA);
				}
			}
			
			this.Eat(TokenType.CLOSECURLY);
			this.Eat(TokenType.COMMA);
			this.Eat(TokenType.STRING);
			this.Eat(TokenType.COLON);
			this.Eat(TokenType.OPENCURLY);
			
			// Parse TimeSeries
			while (this.currentToken.type == TokenType.STRING) {
				String key = this.currentToken.value;
				this.Eat(TokenType.STRING);
				this.Eat(TokenType.COLON);
				this.Eat(TokenType.OPENCURLY);
				se.TimeSeries.put(key, new MetaData());
				while (this.currentToken.type == TokenType.STRING) {
					String subkey = this.currentToken.value;
					this.Eat(TokenType.STRING);
					this.Eat(TokenType.COLON);
					se.TimeSeries.get(key).put(subkey, currentToken.value);
					
					if (this.currentToken.type == TokenType.STRING) {
						this.Eat(TokenType.STRING);
					}
					else {
						this.Eat(TokenType.INT);
					}
					
					if (currentToken.type == TokenType.COMMA) {
						Eat(TokenType.COMMA);
					}
				}
				
				Eat(TokenType.CLOSECURLY);
				
				if (this.currentToken.type == TokenType.COMMA) {
					this.Eat(TokenType.COMMA);
				}
			}
			
			Eat(TokenType.CLOSECURLY);
		}
		Eat(TokenType.CLOSECURLY);
		
		return se;
	}
	
	private void Eat(TokenType type) throws Exception {
		if (currentToken.type == type) {
			currentToken = lexer.GetNextToken();
		}
		else {
			throw new Exception("Error while eating current token. Current token is " + this.currentToken.toString() + " but eat reveived " + type);
		}
	}
	
	private Lexer lexer;
	private Token currentToken;
}
