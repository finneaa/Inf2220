
import java.util.Arrays;

public class Haystack
{
  private final char wildcard = '_';
	private final String hayString;
	private int[] badCharShift;

  public Haystack (String hayString)
  {
		this.hayString = hayString;
	}

  public void finnNaal(String naal)
  {
		badCharShift = new int[256];
		setBadCharShift(naal);
		int needlelen = naal.length();

		int index = 0;
		while(index <= (hayString.length() - needlelen))
    {
			String comp = new String(hayString.substring(index, index + needlelen));

			if (matchesPattern(comp, naal))
			{
        System.out.printf("Index %d: %s\n", index, comp);
      }

			index += badCharShift[comp.charAt(needlelen - 1)];
		}
	}

	private boolean matchesPattern(String comp, String naal)
  {
		int i = naal.length() - 1;
		while (i != -1)
    {
			if (naal.charAt(i) == wildcard || naal.charAt(i) == comp.charAt(i))
      {
        i--;
      }
			else
      {
        break;
      }
		}
		return (i == -1);
	}

	private void setBadCharShift(String naal)
  {
		Arrays.fill(badCharShift, naal.length());
		int shift = naal.length() - 1;
		if (shift == 0)
    {
      return;
    }
		for (char c : naal.toCharArray())
    {
			if (c == '_')
			{
        Arrays.fill(badCharShift, shift);
      }
			else
      {
        badCharShift[c] = shift;
      }
			if (--shift == 0)
      {
        break;
      }
		}
	}
}
