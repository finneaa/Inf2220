import java.util.*;

public class Oblig3
{
  public static void main(String[] args)
  {
    Sortering test = new Sortering();
    double [][] verditabell = new double[14][5];
    for(int i = 0; i < 5; i++)
    {
      String svar = "";
      int[] test1 = test.lagRandom(10);
      int[] test11 = test1.clone();
      verditabell[0][i] = test.VRadixMulti(test1);
      verditabell[7][i] = arrayTest(test11);


      int[] test2 = test.lagRandom(100);
      int[] test21 = test2.clone();
      verditabell[1][i] = test.VRadixMulti(test2);
      verditabell[8][i] = arrayTest(test21);


      int[] test3 = test.lagRandom(1000);
      int[] test31 = test3.clone();
      verditabell[2][i]= test.VRadixMulti(test3);
      verditabell[9][i] = arrayTest(test31);


      int[] test4 = test.lagRandom(10000);
      int[] test41 = test4.clone();
      verditabell[3][i] = test.VRadixMulti(test4);
      verditabell[10][i] = arrayTest(test41);


      int[] test5 = test.lagRandom(100000);
      int[] test51 = test5.clone();
      verditabell[4][i] = test.VRadixMulti(test5);
      verditabell[11][i] = arrayTest(test51);


      int[] test6 = test.lagRandom(1000000);
      int[] test61 = test6.clone();
      verditabell[5][i] = test.VRadixMulti(test6);
      verditabell[12][i] = arrayTest(test61);


      int[] test7 = test.lagRandom(10000000);
      int[] test71 = test7.clone();
      verditabell[6][i] = test.VRadixMulti(test7);
      verditabell[13][i] = arrayTest(test71);

    }
  for(int i = 0; i< 14;i++)
  {
    Arrays.sort(verditabell[i]);
  }

  String utskrift1 ="";
  for (int i = 0; i < 14;i++)
  {
    if(i < 7)
    {
      utskrift1 = utskrift1 + "Verdier av VenstreRadix\n";
    }
    else
    {
      utskrift1 = utskrift1 + "Verdier av kvikksort\n";
    }
    for(int j = 0;j<5;j++)
    {
      utskrift1 = utskrift1 + "-" +verditabell[i][j] + "-";
    }
    utskrift1 = utskrift1 + "\n\n";
  }
  System.out.println(utskrift1);

  for (int i = 0; i < 7;i++)
  {
    System.out.println("-----[Mediantid for sorteringene]-----");
    System.out.println("VenstreRadix: " + verditabell[i][2]);
    System.out.println("kvikksort: " + verditabell[i+7][2]);
    System.out.println("Speedup: " + (verditabell[i+7][2] /verditabell[i][2]));
    System.out.println("--------------------------------------");
  }


  }
  public static double arrayTest(int[] a)
  {
    long tt = System.nanoTime();
    Arrays.sort(a);
    double tid = (System.nanoTime() -tt)/1000000.0;
    return tid;
  }


}
