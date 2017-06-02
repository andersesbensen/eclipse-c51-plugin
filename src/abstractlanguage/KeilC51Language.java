package abstractlanguage;


import org.eclipse.cdt.core.dom.ILinkage;
import org.eclipse.cdt.core.dom.parser.AbstractCLikeLanguage;
import org.eclipse.cdt.core.dom.parser.IScannerExtensionConfiguration;
import org.eclipse.cdt.core.dom.parser.ISourceCodeParser;
import org.eclipse.cdt.core.dom.parser.c.GCCParserExtensionConfiguration;

import org.eclipse.cdt.core.dom.parser.c.ICParserExtensionConfiguration;
import org.eclipse.cdt.core.index.IIndex;
import org.eclipse.cdt.core.parser.IParserLogService;
import org.eclipse.cdt.core.parser.IParserSettings;
import org.eclipse.cdt.core.parser.IScanner;
import org.eclipse.cdt.core.parser.IScannerInfo;
import org.eclipse.cdt.core.parser.ParserLanguage;
import org.eclipse.cdt.core.parser.ParserMode;
import org.eclipse.cdt.internal.core.pdom.dom.IPDOMLinkageFactory;
import org.eclipse.cdt.internal.core.pdom.dom.c.PDOMCLinkageFactory;



public class KeilC51Language extends AbstractCLikeLanguage {
	// Must match the id in the extension
	public static final String ID = "c51-plugin.keil_c51";
	protected static final KeilC51ScannerExtensionConfig KEIL_C51_SCANNER_EXTENSION= new KeilC51ScannerExtensionConfig();

	//protected static final GCCScannerExtensionConfiguration C_GNU_SCANNER_EXTENSION= GCCScannerExtensionConfiguration.getInstance();
	protected static final GCCParserExtensionConfiguration C_GNU_PARSER_EXTENSION= GCCParserExtensionConfiguration.getInstance();


	private static final KeilC51Language DEFAULT_INSTANCE = new KeilC51Language();
	
	public static KeilC51Language getDefault() {
		return DEFAULT_INSTANCE;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class adapter) {
		if (adapter == IPDOMLinkageFactory.class) {
			return new PDOMCLinkageFactory();
		}
		return super.getAdapter(adapter);
	}
	
	@Override
	public String getId() {
		return ID; 
	}

	@Override
	public int getLinkageID() {
		return ILinkage.C_LINKAGE_ID;
	}

	/**
	 * @nooverride This method is not intended to be re-implemented or extended by clients.
	 * @deprecated Since 5.4 not called by the framework. Override
	 *     {@link #getScannerExtensionConfiguration(IScannerInfo)} instead.
	 */
	@Deprecated
	@Override
	protected IScannerExtensionConfiguration getScannerExtensionConfiguration() {
		return KEIL_C51_SCANNER_EXTENSION;
	}

	@Override
	protected IScannerExtensionConfiguration getScannerExtensionConfiguration(IScannerInfo info) {
		return KEIL_C51_SCANNER_EXTENSION;
	}

	/**
	 * Returns the extension configuration used for creating the parser.
	 * @since 5.1
	 */
	protected ICParserExtensionConfiguration getParserExtensionConfiguration() {
		return C_GNU_PARSER_EXTENSION;
	}

	@Override
	protected ISourceCodeParser createParser(IScanner scanner, ParserMode parserMode, IParserLogService logService, IIndex index) {
		return new KeilC51SourceParser(scanner, parserMode, logService, getParserExtensionConfiguration(), index);
	}

	@Override
	protected ISourceCodeParser createParser(IScanner scanner, ParserMode parserMode, IParserLogService logService, IIndex index,
			int options, IParserSettings settings) {
		KeilC51SourceParser parser = new KeilC51SourceParser(scanner, parserMode, logService, getParserExtensionConfiguration(), index);
		if (settings != null) {
			int maximumTrivialExpressions = settings.getMaximumTrivialExpressionsInAggregateInitializers();
			if (maximumTrivialExpressions >= 0 && (options & OPTION_SKIP_TRIVIAL_EXPRESSIONS_IN_AGGREGATE_INITIALIZERS) != 0) {
					parser.setMaximumTrivialExpressionsInAggregateInitializers(maximumTrivialExpressions);
			}
		}
		return parser;
	}

	@Override
	protected ParserLanguage getParserLanguage() {
		return ParserLanguage.C;
	}
}
