package rest.logger.inst;

public class GETTHIS_ThreadId extends Instruction {
  public long thisTid;
  
  public GETTHIS_ThreadId(long thisTid,long tid) {
	    super(tid,-1, -1);
	    this.thisTid = thisTid;
	  }

  @Override
  public String toString() {
    return ("GETTHIS_ThreadId thisTid="
      + thisTid
      + " tid="+tid
      );
  }
}
