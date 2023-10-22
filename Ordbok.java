import java.io.*;

public class Ordbok{

  private TreeNode ordBoktre;
  private Scanner innleser = null;

  private StringBuilder gjettningsOrd = new StringBuilder();
  private char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

  public Ordbok(String filnavn){
    innleser = new Scanner(new File(filnavn));
    lesInnNyFil();
  }

  private void lesInnNyFil(){
    String ordTilListe = innleser.nextLine();
    ordBoktre = new TreeNode(ordTilListe);
    while(innleser.hasNextLine()){
      ordTilListe = innleser.nextLine();
      addOrd(ordTilListe);
    }
  }

  public void addOrd(String nyttOrd){
    ordBoktre.leggTilOrd(nyttOrd);
  }

  public boolean finnOrd(String ord){
    return ordBoktre.inneholder(ord);
  }

  //fjern metode

  spellChecker

  statistikk
}
