

public class ProjectMain
{
  public static void main(String[] args)
  {
		if (args.length != 1)
    {
      System.out.println("\nFeil oppsto!")
			System.out.println("Riktig input: java ProjectMain <projectName.txt>\n");
		}
    else
    {
			Project tm = new Project(args[0]);
			tm.run();
		}
	}
}
