import java.io.*;

public class OrdbokHovedprogram{
  public static void main(String[] args) {

    private Scanner input = new Scanner(System.in);
    private Ordbok obligOrdbok = new Ordbok(/*FILNavn #IF*/);

    private String brukerinput;

    while ( true){
      System.out.println("Skriv inn ordet du leter etter eller q for aa stoppe");
      brukerinput = input.nextLine();
      if(brukerinput.equalsIgnoreCase("q")){
        break
      }
      boolean svar = obligOrdbok.finnOrd(brukerinput);
      if (svar){
        System.out.println("Fant " + brukerinput + " i ordbok");
      }
      else{

      }

    }

  }
}
