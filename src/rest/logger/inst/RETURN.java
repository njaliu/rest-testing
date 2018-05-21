package rest.logger.inst;

public class RETURN extends Instruction {
  public RETURN(long tid,int iid, int mid) {
    super(tid,iid, mid);
  }

  @Override
  public String toString() {
    return "RETURN iid=" + iid + " mid=" + mid+ " tid=" + tid;
  }
}
