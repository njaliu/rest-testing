package rest.logger.inst;

public class MONITORENTER extends Instruction {
  Object ob;
  
  public MONITORENTER(Object ob, long tid, int iid, int mid) {
    super(tid,iid, mid);
    this.ob = ob;
  }
  
  @Override
  public String toString() {
    return "MONITORENTER iid=" + iid + " mid=" + mid+ " tid=" + tid + " object=" + ob.getClass().getName()+"@"+ob.hashCode();
  }
}
