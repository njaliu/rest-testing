package rest.logger;

public final class DJVM {
  private static Logger intp = Config.instance.getLogger();

  private DJVM() {} 

  
  public static void setInterpreter(Logger logger) {
	 intp = logger;
  }
  
  public static void IRETURN(long tid, int iid, int mid) {
	 intp.IRETURN(tid, iid, mid);
  }

  public static void LRETURN(long tid, int iid, int mid) {
	 intp.LRETURN(tid, iid, mid);
  }
  
  public static void FRETURN(long tid, int iid, int mid) {
	 intp.FRETURN(tid, iid, mid);
  }

  public static void DRETURN(long tid, int iid, int mid) {
	 intp.DRETURN(tid, iid, mid);
  }

  public static void ARETURN(long tid, int iid, int mid) {
	 intp.ARETURN(tid, iid, mid);
  }

  public static void RETURN(long tid, int iid, int mid) {
	 intp.RETURN(tid, iid, mid);
  }
  
  
  public static void INVOKEVIRTUAL(int syn, long tid, int iid, int mid, String owner, String name, String desc, String access) {
	  intp.INVOKEVIRTUAL(syn, tid, iid, mid, owner, name, desc, access);
  }

  public static void INVOKESPECIAL(int syn, long tid, int iid, int mid, String owner, String name, String desc, String access) {
	  intp.INVOKESPECIAL(syn, tid, iid, mid, owner, name, desc, access);
  }

  public static void INVOKESTATIC(int syn, long tid, int iid, int mid, String owner, String name, String desc, String access) {
	  intp.INVOKESTATIC(syn, tid, iid, mid, owner, name, desc, access);
  }

  public static void INVOKEINTERFACE(int syn, long tid, int iid, int mid, String owner, String name, String desc, String access) {
	  intp.INVOKEINTERFACE(syn, tid, iid, mid, owner, name, desc, access);
  }

  public static void MONITORENTER(Object ob, long tid, int iid, int mid) {
	  intp.MONITORENTER(ob,tid, iid, mid);
  }
  
  public static void MONITOREXIT(Object ob, long tid, int iid, int mid) {
     intp.MONITOREXIT(ob, tid, iid, mid);
  }
  
  public static void GETTHIS_ThreadId(long thisId, long tid) {
	 intp.GETTHIS_ThreadId(thisId, tid);
  }

  public static void GETTHIS_MonitorObject(Object ob, long tid) {
	  intp.GETTHIS_MonitorObject(ob, tid);
  }
}
