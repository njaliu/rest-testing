package rest.logger.inst;

public class GETTHIS_MonitorObject extends Instruction {
  public Object ob;
  
  public GETTHIS_MonitorObject(Object v,long tid) {
	    super(tid,-1, -1);
	    this.ob = v;
	  }


  @Override
  public String toString() {
    return "GETTHIS_MonitorObject MonitorObject="
      + ob.getClass().getName()+"@"+ob.hashCode()
      + " tid="+tid;
  }
}
