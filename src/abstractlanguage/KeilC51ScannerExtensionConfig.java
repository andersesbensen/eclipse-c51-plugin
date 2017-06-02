package abstractlanguage;

import org.eclipse.cdt.core.dom.parser.AbstractScannerExtensionConfiguration;
import org.eclipse.cdt.core.dom.parser.GNUScannerExtensionConfiguration;
import org.eclipse.cdt.core.parser.GCCKeywords;
import org.eclipse.cdt.core.parser.IGCCToken;
import org.eclipse.cdt.core.parser.IMacro;
import org.eclipse.cdt.core.parser.IPreprocessorDirective;
import org.eclipse.cdt.core.parser.IToken;
import org.eclipse.cdt.core.parser.Keywords;
import org.eclipse.cdt.core.parser.util.CharArrayIntMap;
/**
 * This code is provided under GPLv2
 * @author Anders Lynge Esbensen
 * 
 */

public class KeilC51ScannerExtensionConfig extends AbstractScannerExtensionConfiguration  {
	private static KeilC51ScannerExtensionConfig sInstance;

	@SuppressWarnings("nls")
	public KeilC51ScannerExtensionConfig() {

		/*TODO real version number */
		addMacro("__C51__", "950");
		addMacro("__CX51__", "950");
		addMacro("__STDC__", "1");
		
//		addMacro("__complex__", "_Complex");
//    	addPreprocessorKeyword(Keywords.cINCLUDE_NEXT, IPreprocessorDirective.ppInclude_next); 

		addKeyword("_at_".toCharArray(), IC51Token.t_at);
		addKeyword("alien".toCharArray(), IToken.t_volatile);

		addKeyword("bdata".toCharArray(), IC51Token.t_area);
		addKeyword("xdata".toCharArray(), IC51Token.t_area);
		addKeyword("data".toCharArray(), IC51Token.t_area);
		addKeyword("idata".toCharArray(), IC51Token.t_area);
		addKeyword("code".toCharArray(), IC51Token.t_area);

		addKeyword("bit".toCharArray(), IToken.t_char);
		addKeyword("compact".toCharArray(), IC51Token.t_compact);
		addKeyword("far".toCharArray(), IToken.t_volatile);
		addKeyword("large".toCharArray(), IC51Token.t_large);
		addKeyword("pdata".toCharArray(), IToken.t_volatile);
		addKeyword("_priority_".toCharArray(), IC51Token.t_priority);
		addKeyword("reentrant".toCharArray(), IC51Token.t_reentrant);
		addKeyword("sfr".toCharArray(), IToken.t_char);
		addKeyword("sfr16".toCharArray(), IToken.t_int);
		addKeyword("small".toCharArray(), IC51Token.t_small);
		addKeyword("_task_".toCharArray(), IC51Token.t_task);

		addKeyword("interrupt".toCharArray(), IC51Token.t_interrupt);
		addKeyword("using".toCharArray(), IC51Token.t_using);

		addKeyword("sbit".toCharArray(), IToken.t_volatile);
		
		
	}

    @Override
	public boolean support$InIdentifiers() {
        return true;
    }

        	
	/**
	 * @deprecated simply derive from this class and use {@link #addMacro(String, String)} to
	 * add additional macros.
	 */
	@Deprecated
	public static IMacro[] getAdditionalGNUMacros() {
		if (sInstance == null) {
			sInstance= new KeilC51ScannerExtensionConfig() {};
		}
		return sInstance.getAdditionalMacros();
	}
	
	/**
	 * @deprecated simply derive from this class and use {@link #addKeyword(char[], int)} to
	 * add additional keywords.
	 */
	@Deprecated
	public static void addAdditionalGNUKeywords(CharArrayIntMap target) {
		if (sInstance == null) {
			sInstance= new KeilC51ScannerExtensionConfig() {};
		}
		target.putAll(sInstance.getAdditionalKeywords());
	}
}
