package org.lifealien.util;

/**
 * 语法异常类
 * @author ljz
 *
 */
public class SyntaxException extends Exception {
	private static final long serialVersionUID = -944693025422672843L;
	
	private boolean canIgnore=false;

	public SyntaxException() {
	}

	public SyntaxException(String message) {
		super(message);
	}

	public SyntaxException(Throwable cause) {
		super(cause.getMessage(), cause);
	}

	public SyntaxException(String message, Throwable cause) {
		super(message, cause);
	}

//	public SyntaxException(Token token, String message) {
//		super((token == null ? "语法异常：" : token.toString() + " 语法异常：") + message);
//	}
//	
//	public SyntaxException(Token token, Throwable cause) {
//		super(token == null ? "语法异常：" : token.toString() + " 语法异常：", cause);
//	}
	
	public boolean isCanIgnore() {
		return canIgnore;
	}

	public void setCanIgnore(boolean canIgnore) {
		this.canIgnore = canIgnore;
	}

	@Override
	public String toString() {
		String msg = getLocalizedMessage();
		return (msg != null) ? msg : getClass().getName();
	}
}
