package rest.logger.inst;

public class LRETURN extends Instruction {
  public LRETURN(long tid,int iid, int mid) {
    super(tid,iid, mid);
  }

  @Override
  public String toString() {
    return "LRETURN iid=" + iid + " mid=" + mid+ " tid=" + tid;
  }
}
