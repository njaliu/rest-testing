package rest.logger;

import rest.logger.inst.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger extends AbstractLogger {

  private PrintWriter pw;

  public FileLogger() {
    try {
      pw = new PrintWriter(new FileOutputStream(Config.instance.traceFileName));
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }


  @Override
  protected synchronized void log(Instruction insn) {
    //if (Config.instance.printTrace) 
    	//	System.out.println(insn);
    	pw.println(insn.toString());
    	pw.flush();
  }

}
