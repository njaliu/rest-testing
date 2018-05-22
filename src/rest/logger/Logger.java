package rest.logger;

public interface Logger {
  public void IRETURN(long tid, int iid, int mid);

  public void LRETURN(long tid, int iid, int mid);

  public void FRETURN(long tid, int iid, int mid);

  public void DRETURN(long tid, int iid, int mid);

  public void ARETURN(long tid, int iid, int mid);

  public void RETURN(long tid, int iid, int mid);
 
  public void INVOKEVIRTUAL(int syn, long tid, int iid, int mid, String owner, String name, String desc, String access);

  public void INVOKESPECIAL(int syn, long tid, int iid, int mid, String owner, String name, String desc, String access);

  public void INVOKESTATIC(int syn, long tid, int iid, int mid, String owner, String name, String desc, String access);

  public void INVOKEINTERFACE(int syn, long tid, int iid, int mid, String owner, String name, String desc, String access);

  public void MONITORENTER(Object ob, long tid, int iid, int mid);

  public void MONITOREXIT(Object ob, long tid, int iid, int mid);

  public void GETTHIS_ThreadId(long thisTid, long tid);
  
  public void GETTHIS_MonitorObject(Object ob, long tid);
}
