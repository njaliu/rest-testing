package rest.instrument;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rest.logger.Config;

/** An object to keep track of (classId, methodId, instructionId) tuples during
 instrumentation. */
public class GlobalStateForInstrumentation {
  public static GlobalStateForInstrumentation instance = new GlobalStateForInstrumentation();
  private int iid = 0;
  private int mid = 0;
  private int cid = 0;
  //用来表示某个方法是否为同步方法
  public static HashMap<String,Boolean> methodTosyn = new HashMap<String,Boolean>();
  // When one gets the id, she gets the result of mergind all three ids.
  // NOTE
  // Beaware of truncation errors.
  private final static int CBITS = 14; // CID occupies the upper 14 bits
  private final static int MBITS = 8;  // MID occupies the next 8 bits
  
  private JSONObject jb;

  public JSONObject getJb() {
	  if(jb == null)
		  inputJson();
	  return jb;
  }
  
  public JSONArray isMethodJPA(String owner, String name) {
	  if(jb != null) {
		  try {
			return (JSONArray) jb.get(owner+"#_#"+name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return null;
		}
	  }
	  return null;
  }
  
  
  
  public JSONObject inputJson() {
	  File file = new File(Config.instance.jsonFile);
	  assert(file.exists());
	  try {
		BufferedReader bf = new BufferedReader(new FileReader(file));
		try {
			jb = new JSONObject(bf.readLine());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//	System.out.println(jb.toString());
	return jb;
  }
  /** Increment iid and get the complete id */
  public int incAndGetId() {
    iid++;
    return getId();
  }

  public int getId() {
    return (cid << (32 - CBITS)) + (mid << (32 - CBITS - MBITS)) + iid;
  }

  // Used for testing
  public void reset() {
    iid = 0;
    mid = 0;
    cid = 0;
  }

  public static int getCidMid(int cid, int mid) {
    return cid << MBITS + mid;
  }

  public static int extractCidMid(int id) {
    return (id >> 32 - MBITS - CBITS);
  }

  public int getMid() {
    return mid;
  }

  public void incMid() {
    this.mid++;
    this.iid = 0;
  }

  public int getCid() {
    return cid;
  }

  public void setCid(int cid) {
    this.iid = 0;
    this.mid = 0;
    this.cid = cid;
  }
}
