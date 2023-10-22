


public class Oblig4
{
  public static void main(String[] args) throws Exception
  {
    if (args.length != 2)
    {
			System.out.println("Correct usage: Oblig4 <naal> <haystack>");
			System.exit(1);
		}

    String haystack = new String(Files.readAllBytes(Paths.get(args[1])));
		String naal = new String(Files.readAllBytes(Paths.get(args[0])));

		System.out.printf("Needle: %s\nHaystack: %s\n\n", naal, haystack);
		Haystack hs = new Haystack(haystack);
		hs.finnNaal(naal);

  }
}
