
public class TreeNode{

  private String myWord;
  private TreeNode left;
  private TreeNode right;

  public TreeNode(String word){
    myWord = word;
  }


  //metode for aa teste om dette er riktig ord og ellers sende soket videre
  public boolean inneholder(String target){

    if (target.equalsIgnoreCase(myWord)){
      return true;
    }

      TreeNode neste = target.compareToIgnoreCase(myWord) < 0 ? left : right;
      return neste != null && neste.inneholder(target);
  }


    //legger til ord i treet og passer paa at treet ikke inneholder ordet alt
  public void leggTilOrd(String nyttOrd){

    if (!nyttOrd.equalsIgnoreCase(myWord)){

      boolean neste = nyttOrd.compareToIgnoreCase(myWord) < 0 ? true : false;
        //innsetting av node eller vidrekall for left
      if (neste){
        if(left == null){
          left = new TreeNode(nyttOrd);
        }
        else{
          left.leggTilOrd(nyttOrd);
        }
      }
      //innsetting av node eller vidrekall for right
      else{
        if(right == null){
          right = new TreeNode(nyttOrd);
        }
        else{
          right.leggTilOrd(nyttOrd);
        }
      }

    }
  }


}
