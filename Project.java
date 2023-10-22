import java.io.*;
import java.util.*;

public class Project
{
  private Task[] oppgaver;
  private String filnavn;
  int tid;

  public Project(String filnavn)
  {
    this.filnavn = filnavn;
    tid = 0;
    innlesning(filnavn);
		settOppKrav();
  }

  private void innlesning(String filnavn)
  {

    try
    {
      Scanner filleser = new Scanner(new File(filnavn));
      int antallOppgaver = Integer.parseInt(filleser.nextLine());
      oppgaver = new Task[antallOppgaver];
      filleser.nextLine(); //hopp over tom linje
      int taskNUM = 0;
      while(filleser.hasNextLine())
      {
         oppgaver[taskNUM] = new Task(filleser.nextLine());
         taskNUM++;
      }
    }
    catch (FileNotFoundException f)
    {
      System.out.println("File ikke funnet!");
      f.printStackTrace();
      System.exit(1);
    }

  }

  private void settOppKrav()
  {
    for(Task t : oppgaver)
    {
      for(int i = 0; i< t.antallKrav; i++)
      {
        oppgaver[t.kravID[i]-1].addKrav(t);
      }
    }
  }

  private void reportLoop(int id)
  {
    System.out.println("Programet fant en loop!");
    System.out.println("TaskID: " + id);
    System.exit(0);
  }

  //opptimat timeplan
  public void printUtTask(int id)
  {
    System.out.println("\n---------------------------------");
    System.out.println("Task nummer: " + oppgaver[id-1].id);
    System.out.println("Task navn er: " + oppgaver[id-1].navn);
    System.out.println("Task tar: " + oppgaver[id-1].time + " tids enheter");
    System.out.println("Task bruker: " + oppgaver[id-1].staff + " manpower");
    System.out.println("Task beste starttid: " + oppgaver[id-1].earliestStart);
    System.out.println("Task siste starttid: " + oppgaver[id-1].latestStart);
    System.out.println("Task slack: " + (oppgaver[id-1].latestStart - oppgaver[id-1].earliestStart));
    System.out.println("Etterfolgende Task: " + oppgaver[id-1].kravString());
    System.out.println("---------------------------------\n");


  }

  public void run()
  {

		for (Task t : oppgaver) {
			if (t.antallKrav == 0) {
				int loopID = t.findLoop();
				if (loopID > 0) reportLoop(loopID);
				t.findEST();
			}
		}

		for (Task t : oppgaver) {
			if (t.antallKrav == 0)
				t.findLST();
		}

		for(int i = 0; i <oppgaver.length;i++)
    {
      printUtTask(i+1);
    }

    ArrayList<Task> critical = new ArrayList<Task>();
    ArrayList<Task> slack = new ArrayList<Task>();
    ArrayList<Task> inProgress = new ArrayList<Task>();
    ArrayList<String> status = new ArrayList<String>();

    System.out.println("\n------------------------------------------");
    do
    {
      String curState = "Tid: " + tid +"\n\t";

      int progLength = inProgress.size();
      for(int i = 0; i < progLength; i++)
      {
        Task curTask = inProgress.get(0);
        inProgress.remove(0);
        if(curTask.time <=0)
        {
          curState += ("Ferdig: " + curTask.id +"\n\t");
          curTask.free();
        }
        else
        {
          inProgress.add(curTask);
        }
      }

      for(Task t : oppgaver)
      {
        if(t.antallKrav == 0 && !t.samlet)
        {
          t.samlet = true;
          if(t.isCritical())
          {
            critical.add(t);
          }
          else
          {
            slack.add(t);
          }
        }
      }

      int critLength = critical.size();
      for(int i = 0;i < critLength; i++)
      {
        Task curTask = critical.get(0);
        critical.remove(0);
        inProgress.add(curTask);
        curState += ("Startet: " + curTask.id +"\n\t");
      }

      int slackLength = slack.size();
      for(int i = 0; i < slackLength; i++)
      {
        Task curTask = slack.get(0);
        slack.remove(0);
        curState += ("Startet: " + curTask.id +"\n\t");
        inProgress.add(curTask);
      }

      for(Task t : inProgress)
      {
        t.time--;
      }

      if(curState.length() > 11)
      {
        status.add(curState);
      }

      tid++;
    } while(inProgress.size() != 0);

    for(String s : status)
    {
      System.out.println(s);
    }
    System.out.println("\n**** Kortest mulig tid for project utforelse er " + (tid-1) + " ****\n\n");
  }
}
