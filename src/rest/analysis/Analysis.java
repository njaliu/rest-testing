package rest.analysis;

import rest.logger.AbstractLogger;
import rest.logger.inst.*;

/**
 * 
 * @author aliu
 *
 */
public abstract class Analysis extends AbstractLogger{
	
	/**
	 * Analysis probe APIs that should be implemented by sub-Analysis classes.
	 * @param insn
	 */
	protected abstract void writeAccess(INVOKEINTERFACE insn);
	protected abstract void readAccess(INVOKEINTERFACE insn);
	protected abstract void onMonitorEnter(MONITORENTER insn);
	protected abstract void onMonitorExit(MONITOREXIT insn);
	
	@Override
	protected void log(Instruction insn) {
		if (insn instanceof INVOKEINTERFACE) {
			if (!((INVOKEINTERFACE)insn).access.equals("NON_JPA")) {
				
			}
		} else if (insn instanceof MONITORENTER) {
			onMonitorEnter((MONITORENTER) insn);
		} else if (insn instanceof MONITOREXIT) {
			onMonitorExit((MONITOREXIT) insn);
		}
	}

}
