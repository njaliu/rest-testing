package rest.logger.inst;

public class MONITOREXIT extends Instruction {
  Object ob;
  
  public MONITOREXIT(Object ob,long tid,int iid, int mid) {
    super(tid,iid, mid);
    this.ob = ob;
  }

  @Override
  public String toString() {
    return "MONITOREXIT iid=" + iid + " mid=" + mid+ " tid=" + tid + " object=" + ob.getClass().getName()+"@"+ob.hashCode();
  }
}
