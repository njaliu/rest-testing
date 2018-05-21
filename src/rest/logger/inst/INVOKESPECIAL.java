package rest.logger.inst;

public class INVOKESPECIAL extends Instruction {
  public String owner;
  public String name;
  public String desc;
  public int syn;

  public INVOKESPECIAL(int syn, long tid, int iid, int mid, String owner, String name, String desc) {
    super(tid,iid, mid);
    this.owner = owner;
    this.name = name;
    this.desc = desc;
    this.syn = syn;
  }

  @Override
  public String toString() {
    return "INVOKESPECIAL iid="
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
        + syn;
  }
}