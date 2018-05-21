package rest.logger.inst;

public class IRETURN extends Instruction {
  public IRETURN(long tid,int iid, int mid) {
    super(tid,iid, mid);
  }

  @Override
  public String toString() {
    return "IRETURN iid=" + iid + " mid=" + mid+ " tid=" + tid;
  }
}
