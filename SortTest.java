import java.util.*;

public class SortTest
{
  public static void main(String[] args)
  {

    int [] a = {1,3,6,8,90,234,45,12,63,81,136};

    int [] b = new int [a.length];


    int max = 0;
    for (int i = 0; i< a.length; i++)
    {
      if(a[i] > max)
      {
        max = a[i];
      }
    }
    System.out.println("max = " + max);

    int numBit = Integer.toBinaryString(max).length();

    System.out.println("numBit = " + numBit);

    int i, temp, maxIN = a.length -1;
    for(int j = 0; j < maxIN; j++)
    {
      if(a[j] > a[j+1])
      {
        temp = a[j+1];
        i=j;
        do
        {
          a[i+1] = a[i];
          i--;
        } while (i >= 0 && a[i]>temp);

        a[i+1]= temp;
      }
    }

    for(int f = 0; f< a.length;f++)
    {
      System.out.println(a[f]);
    }
  }

}
