package abstractlanguage;

import java.util.List;

import org.eclipse.cdt.core.dom.ast.IASTAttributeSpecifier;
import org.eclipse.cdt.core.dom.parser.c.ICParserExtensionConfiguration;
import org.eclipse.cdt.core.index.IIndex;
import org.eclipse.cdt.core.parser.EndOfFileException;
import org.eclipse.cdt.core.parser.IParserLogService;
import org.eclipse.cdt.core.parser.IScanner;
import org.eclipse.cdt.core.parser.IToken;
import org.eclipse.cdt.core.parser.ParserMode;
import org.eclipse.cdt.internal.core.dom.parser.BacktrackException;
import org.eclipse.cdt.internal.core.dom.parser.c.GNUCSourceParser;

public class KeilC51SourceParser extends GNUCSourceParser {
	public KeilC51SourceParser(IScanner scanner, ParserMode parserMode,
			IParserLogService logService,
			ICParserExtensionConfiguration config, IIndex index) {
		super(scanner, parserMode, logService, config, index);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    protected List<IASTAttributeSpecifier> __attribute_decl_seq(boolean allowAttrib, boolean allowDeclspec)
    		throws BacktrackException, EndOfFileException {

    	/*
    	 * This is for tokens after declarations
    	 */
myloop:
		for(;;) {			
			IToken token = LA(1);
			switch(token.getType()) {
			case IC51Token.t_compact:
			case IC51Token.t_large:
			case IC51Token.t_small:
			case IC51Token.t_area:
			case IC51Token.t_reentrant:
				consume();
				break;
			case IC51Token.t_at:
				consume();
				consume(IToken.tINTEGER);
				break;
			case IC51Token.t_interrupt:
				consume();
				consume(IToken.tINTEGER);
				
				token = LA(1);
				if(token.getType() == IC51Token.t_using) {
					consume();
					consume(IToken.tINTEGER);
				}			
				break;
			case IC51Token.t_task:
				consume();
				consume(IToken.tINTEGER);
				token = LA(1);
				if(token.getType() == IC51Token.t_priority) {
					consume();
					consume(IToken.tINTEGER);
				}			
				break;
			default:
				break myloop;
			}
		}

		List<IASTAttributeSpecifier> result = super.__attribute_decl_seq(allowAttrib,allowDeclspec);

		return result;
    }
	
	@Override
	protected void handleOtherDeclSpecModifier() throws BacktrackException, EndOfFileException {
		// default action: consume keyword plus optional parenthesized "something"

		IToken token = LA(1);

		/*
		 * this is tokens before declreations 
		 */
		switch(token.getType()) {
		default:
			super.handleOtherDeclSpecModifier();
		}
	}
}
