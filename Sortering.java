/** N.B. Sorterer a[] stigende – antar at: 0 ≤ a[i]) < 232 , returnerer tiden i millisek.
 *
*/
import java.util.*;

public class Sortering
{
 final static int NUM_BIT =13; // eller: 6-13 er kanskje best.. finn selv ut hvilken verdi som er raskest
 final static int MIN_NUM = 30; // mellom 16 og 60, kvikksort bruker 47

 public int[] lagRandom (int lengde) {
   int[] a = new int[lengde];
   Random r = new Random();
   for (int i =0; i < lengde;i++) {
      a[i] = r.nextInt(lengde-1);
     }

     return a;
 } // end legRandom

 public double VRadixMulti(int [] a)
 {
   long tt = System.nanoTime();
   int [] b = new int [a.length];

   // a) finn ‘max’ verdi i a[]
   int max = 0;
   for (int i = 0; i< a.length; i++)
   {
     if(a[i] > max)
     {
       max = a[i];
     }
   }
   // b) bestem numBit = høyeste (mest venstre) bit i ‘max’ som ==1
   int numBit = 0;
   while (max >= (1<<numBit))
   {
     numBit++;
   }
   // c) Første kall (rot-kallet) på VenstreRadix med a[], b[] , numBit, og lengden av første siffer
   venstreRadix(a, b,0,a.length, numBit, NUM_BIT, 0);

   double tid = (System.nanoTime() -tt)/1000000.0;
   testSort(a);
   return tid; // returnerer tiden i ms. det tok å sortere a, som nå er sortert og testet
 } // end VRadixMulti


 // Sorter a[left..right] på siffer med start i bit: leftSortBit, og med lengde: maskLen bit,
 public void venstreRadix ( int [] a, int [] b, int left, int right, int leftSortBit, int maskLen, int dyb)
 {

   if(maskLen > leftSortBit)
   {
     maskLen = leftSortBit;
   }

   int  acumVal = 0, j= 0;
   int mask = (1<<maskLen) -1;
   int [] count = new int [mask+1];
   //……………. Andre deklarasjoner ……………

   // d) count[] =hvor mange det er med de ulike verdiene
   // av dette sifret I a [left..right]
   for (int i = left; i < right; i++)
   {
    count[(a[i]>>(leftSortBit -maskLen)) & mask]++;
   }

   // e) Tell opp verdiene I count[] slik at count[i] sier hvor i b[] vi skal flytte første element med verdien ‘i’ vi sorterer.
   for (int i = 0; i <= mask; i++)
   {
     j = count[i];
     count[i] = acumVal;
     acumVal += j;
    }

    int[] tall = count.clone();



   // f) Flytt tallene fra a[] til b[] sorter på dette sifferet I a[left..right] for alle de ulike verdiene for dette sifferet
   for (int i = left; i < right; i++)
   {
    b[count[(a[i]>>(leftSortBit -maskLen)) & mask] + left] = a[i];
    count[(a[i]>>(leftSortBit -maskLen)) & mask]++;
   }

   // g) Kall enten innstikkSort eller rekursivt VenstreRadix
   // på neste siffer (hvis vi ikke er ferdige) for alle verdiene vi har av nåværende siffer
   // Vurder når vi. skal kopiere tilbake b[] til a[] ??

   for(int l = 0; l< count.length; l++)
   {
     if((count[l]-tall[l]) >= MIN_NUM && (leftSortBit - maskLen) > 0)
     {

       venstreRadix(b, a, tall[l] + left, count[l] + left,leftSortBit - maskLen, maskLen, dyb +1);
     }
     else if((count[l]-tall[l]) > 1)
     {
       innstikkSortering(b,tall[l] + left, count[l] + left );

       if(dyb % 2 == 0)
       {
         for(int i = tall[l] + left; i < count[l] + left;i++)
         {
           a[i] = b[i];
         }
       }
     }
     else
     {
       if(dyb % 2 == 0)
       {
         for(int i = tall[l] + left; i < count[l] + left;i++)
         {
           a[i] = b[i];
         }
       }
     }
   }
}// end VenstreRadix


 public void testSort(int [] a)
 {
   for (int i = 0; i< a.length-1;i++)
   {
     if (a[i] > a[i+1])
     {
       System.out.println("SorteringsFEIL på: "+
       i +" a["+i+"]:"+a[i]+" > a["+(i+1)+"]:"+a[i+1]);
       return;
     }
   }
 }// end testSort


 public void innstikkSortering(int [] a, int left, int right)
 {
   int temp;
   for(int j = left+1; j < right; j++)
   {
    for(int i = j; i > left; i--)
    {
      if( a[i] < a[i-1])
      {
        temp = a[i];
        a[i] = a[i-1];
        a[i-1] = temp;
      }
    }
  }
 } //end innstikkSortering


}
