package rest.logger.inst;

public class ARETURN extends Instruction {
  public ARETURN(long tid, int iid, int mid) {
    super(tid, iid, mid);
  }

  @Override
  public String toString() {
    return "ARETURN iid=" + iid + " mid=" + mid + " tid=" + tid;
  }
}
