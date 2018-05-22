package rest.logger.inst;

public class INVOKESTATIC extends Instruction {
  public String owner;
  public String name;
  public String desc;
  public String access;
  public int syn;

  public INVOKESTATIC(int syn, long tid, int iid, int mid, String owner, String name, String desc, String access) {
    super(tid, iid, mid);
    this.owner = owner;
    this.name = name;
    this.desc = desc;
    this.syn = syn;
    this.access = access;
  }

  
  @Override
  public String toString() {
    return "INVOKESTATIC iid="
        + iid
        + " mid="
        + mid
        + " tid=" + tid
        + " owner="
        + owner
        + " name="
        + name
        + " desc="
        + desc
        + " syn="
        + syn
    		+ " JPAaccess="
    		+ access;
  }
}
