import java.util.*;

public class Task {
int id , time , staff ;
String navn ;
int earliestStart , latestStart ;
ArrayList < Task > taskKrav ;
int antallKrav;
int slack; //"!" utregningsmetode for slack
boolean samlet;
int[] kravID;

private final int ferdig = 0;
private final int ikkeBesokt = -1;
private final int underveis = -2;
private int status;

private static int totalTid;

  public Task(String taskInfo)
  {
    String[] taskINFO = taskInfo.split("\\s+|\\t+");

    id = Integer.parseInt(taskINFO[0]);
    navn = taskINFO[1];
    time = Integer.parseInt(taskINFO[2]);
    staff = Integer.parseInt(taskINFO[3]);
    antallKrav = taskINFO.length - 5;
    kravID = new int[antallKrav];
    for(int i =4; (i-4)< kravID.length;i++)
    {
      kravID[i-4] = Integer.parseInt(taskINFO[i]);
    }

    taskKrav = new ArrayList<Task>();
    earliestStart = -1;
    status = ikkeBesokt;
    samlet = false;
  }

  public boolean addKrav(Task krav)
  {
    return taskKrav.add(krav);
  }

  public void findEST()
  {
    findEST(0);
  }

  public void findEST(int startTid)
  {
    if(startTid > earliestStart)
    {
      earliestStart = startTid;
    }
    else
    {
      return;
    }
    for(Task t : taskKrav)
    {
      t.findEST(earliestStart + time);
    }
    if(earliestStart + time > totalTid)
    {
      totalTid =  earliestStart + time;
    }
  }

  public int findLST()
  {
    latestStart = totalTid - time;
    for(Task t : taskKrav)
    {
      int siste = t.findLST() - time;
      if(siste < latestStart)
      {
        latestStart = siste;
      }
    }
    return latestStart;
  }

  public int findLoop()
  {
    if(status == underveis)
    {
      return id;
    }
    if(status == ferdig)
    {
      return ferdig;
    }
    for(Task t : taskKrav)
    {
      status = underveis;
      status = t.findLoop();
      if(status > ferdig)
      {
        return status;
      }
    }
    return ferdig;
  }

  public int hentTotal()
  {
    return totalTid;
  }

  public String kravString()
  {
    String s = "";
    for(Task t : taskKrav)
    {
      s += t.id + "  ";
    }
    if(s.length() == 0)
    {
      return "none";
    }
    return s;
  }

  public void free()
  {
    for(Task t: taskKrav)
    {
      t.antallKrav--;
    }
  }

  public boolean isCritical()
  {
    return (latestStart - earliestStart == 0);
  }


}
