package rest.logger.inst;

import java.io.Serializable;

public class Instruction implements Serializable {
 

  public int iid;
  public int mid;
  public long tid;
  public long runtimeNum;


 public Instruction(long tid, int iid, int mid) {
	this.tid = tid;
    this.iid = iid;
    this.mid = mid;
  }
 
}
