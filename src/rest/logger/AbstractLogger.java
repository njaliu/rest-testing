package rest.logger;

import rest.logger.inst.*;

public abstract class AbstractLogger implements Logger {
	
  protected abstract void log(Instruction insn); 

  public void IRETURN(long tid, int iid, int mid) {
	  log(new IRETURN(tid, iid, mid));
  }

  public void LRETURN(long tid, int iid, int mid) {
	  log(new LRETURN(tid, iid, mid));
  }

  public void FRETURN(long tid, int iid, int mid) {
	  log(new FRETURN(tid, iid, mid));
  }

  public void DRETURN(long tid, int iid, int mid) {
	  log(new DRETURN(tid, iid, mid));
  }

  public void ARETURN(long tid, int iid, int mid) {
	  log(new ARETURN(tid, iid, mid));
  }

  public void RETURN(long tid, int iid, int mid) {
	  log(new RETURN(tid, iid, mid));
  }
  
  public void INVOKEVIRTUAL(int syn, long tid, int iid, int mid, String owner, String name, String desc) {
	  log(new INVOKEVIRTUAL(syn, tid, iid, mid, owner, name, desc));
  }

  public void INVOKESPECIAL(int syn, long tid, int iid, int mid, String owner, String name, String desc) {
	  log(new INVOKESPECIAL(syn, tid, iid, mid, owner, name, desc));
  }

  public void INVOKESTATIC(int syn, long tid, int iid, int mid, String owner, String name, String desc) {
	  log(new INVOKESTATIC(syn, tid, iid, mid, owner, name, desc));
  }

  public void INVOKEINTERFACE(int syn, long tid, int iid, int mid, String owner, String name, String desc) {
	  log(new INVOKEINTERFACE(syn, tid, iid, mid, owner, name, desc));
  }

  public void MONITORENTER(Object ob, long tid, int iid, int mid) {
	  log(new MONITORENTER(ob, tid, iid, mid));
  }

  public void MONITOREXIT(Object ob, long tid, int iid, int mid) {
	  log(new MONITOREXIT(ob, tid, iid, mid));
  }
  
  public void GETTHIS_ThreadId(long thisTid, long tid) {
	  log(new GETTHIS_ThreadId(thisTid, tid));
  }
  
  public void GETTHIS_MonitorObject(Object ob, long tid) {
	  log(new GETTHIS_MonitorObject(ob, tid));
  }
 

}