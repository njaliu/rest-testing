package rest.logger.inst;

public class DRETURN extends Instruction {
  public DRETURN(long tid, int iid, int mid) {
    super(tid, iid, mid);
  }
 
  @Override
  public String toString() {
    return "DRETURN iid=" + iid + " mid=" + mid+ " tid=" + tid;
  }
}
