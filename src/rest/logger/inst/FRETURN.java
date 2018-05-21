package rest.logger.inst;

public class FRETURN extends Instruction {
  public FRETURN(long tid, int iid, int mid) {
    super(tid, iid, mid);
  }


  @Override
  public String toString() {
    return "FRETURN iid=" + iid + " mid=" + mid+ " tid=" + tid;
  }
}
