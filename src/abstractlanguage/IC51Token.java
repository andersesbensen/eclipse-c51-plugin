package abstractlanguage;
import org.eclipse.cdt.core.parser.IToken;
/**
 * This code is provided under GPLv2
 * @author Anders Lynge Esbensen
 * 
 */

public interface IC51Token extends IToken {
	int t_interrupt = FIRST_RESERVED_IExtensionToken;
	int t_using = FIRST_RESERVED_IExtensionToken+1;
	int t_at = FIRST_RESERVED_IExtensionToken+2;
	int t_reentrant = FIRST_RESERVED_IExtensionToken+3;
	int t_task = FIRST_RESERVED_IExtensionToken+4;
	int t_priority = FIRST_RESERVED_IExtensionToken+5;

	int t_compact = FIRST_RESERVED_IExtensionToken+6;
	int t_large = FIRST_RESERVED_IExtensionToken+7;
	int t_small = FIRST_RESERVED_IExtensionToken+8;
	int t_area = FIRST_RESERVED_IExtensionToken+9;


}
