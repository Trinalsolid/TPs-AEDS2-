import java.util.Scanner;

class Celula {
   public int elemento;
   public Celula inf, sup, esq, dir;
 
   public Celula(){
      this(0);
   }
 
   public Celula(int elemento){
      this(elemento, null, null, null, null);
   }
 
   public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){
      this.elemento = elemento;
      this.inf = inf;
      this.sup = sup;
      this.esq = esq;
      this.dir = dir;
   }
}
 
class Matriz {
   private Celula primeiro;
   private Celula ultimo;

   public Matriz() {
      primeiro = new Celula();
      ultimo = primeiro;
   }

   private void construirMatriz(int tamanho) {
      Celula tmp = primeiro = ultimo;
      for (int l = 0; l < tamanho; l++) {
         for(int c = 1; c < tamanho; c++) {
            ultimo.dir = new Celula();
            ultimo.dir.esq = ultimo;
            ultimo = ultimo.dir;
         }
         if (l != (tamanho - 1)) {
             tmp.inf = new Celula();
            tmp.inf.sup = tmp;
            tmp = ultimo = tmp.inf;
         }
      }
      Celula linha = tmp = primeiro;
      for (int l = 1; l < tamanho; l++) {
         ultimo = linha;
         linha = tmp = linha.dir;
         ultimo = ultimo.inf.dir;
         for (int c = 1; c < tamanho; c++) {
            tmp.inf = ultimo;
            ultimo.sup = tmp;
            tmp = ultimo;
            if (c != (tamanho - 1)) {
               ultimo = ultimo.esq.inf.dir;
            }
         }
         ultimo = primeiro;
      }
   }

   public void inserir(int tamanho) {
      construirMatriz(tamanho);
      Celula tmp = ultimo = primeiro;
      for (int l = 0; l < tamanho; l++) {
         for (int c = 0; c < tamanho; c++) {
            tmp.elemento = MyIO.readInt();
            tmp = tmp.dir;
         }
         ultimo = tmp = ultimo.inf;
      }
      ultimo = primeiro;

   }

   public void mostrar() {
      Celula tmp = primeiro;

      for (Celula l = primeiro; l != null; l = l.inf) {
         for (Celula c = tmp; c != null; c = c.dir) {
            MyIO.print("|" + c.elemento + " ");
            MyIO.print("|\n");
            tmp = l.inf;
         }
      }

   }

   public void DiagonalPrincipal(int tamanho) {
      ultimo = primeiro;
      for (int l = 0; l < tamanho; l++) {
         MyIO.print(ultimo.elemento + " ");
         if (l != tamanho - 1) {
            ultimo = ultimo.inf.dir;
         }
      }
      MyIO.println("");
      ultimo = primeiro;
   }

   public void DiagonalSecundaria(int tamanho) {
      ultimo = primeiro;
      for (int l = 1; l < tamanho; l++){
         ultimo = ultimo.dir;
      }
      for (int l = 0; l < tamanho; l++) {
         MyIO.print(ultimo.elemento + " ");
         if (l != tamanho - 1) {
            ultimo = ultimo.inf.esq;
         }
      }

      MyIO.println("");
      ultimo = primeiro;
   }

   public void soma(Matriz m){
      Celula tmp = primeiro;
      Celula tmp2 = m.primeiro;
      for (Celula l = primeiro, l1 = m.primeiro; l != null; l = l.inf, l1 = l1.inf) {
         for (Celula c = tmp, c1 = m.primeiro; c != null; c = c.dir, c1 = c1.dir){
            MyIO.print((c.elemento + c1.elemento) + " ");
            tmp = l.inf;
            tmp2 = l1.inf;
         }
         MyIO.print("\n");
      }
   }
   
   public void multiplicacao(Matriz m,int tamanho){
      Celula tmp=ultimo=primeiro;
      Celula tmp2=m.primeiro;
      int total=0;
      for(int l=0;l!=tamanho;l++){
         for(Celula c=m.primeiro;c!=null;c=c.dir){
               for(Celula h=tmp,h1=tmp2;h!=null;h = h.dir, h1 = h1.inf)
               total += h1.elemento * h.elemento;
               MyIO.print(total + " ");
               tmp2 = c.dir;
               total = 0;
         }
         MyIO.println("");
         tmp2 = m.primeiro;
         tmp = ultimo = ultimo.inf;
      }

      ultimo = primeiro;
      m.ultimo = m.primeiro;
   }

}
public class questao5{
   public static void main(String[] args) {
      Matriz matriz1 = new Matriz();
      Matriz matriz2 = new Matriz();
      int casos = MyIO.readInt();
      for (; casos != 0; casos--) {
         MyIO.readInt();
         int tamanho = MyIO.readInt();
         matriz1.inserir(tamanho);
         MyIO.readInt();
         MyIO.readInt(); 
         matriz2.inserir(tamanho);
         matriz1.DiagonalPrincipal(tamanho);
         matriz1.DiagonalSecundaria(tamanho);
         matriz1.soma(matriz2);
         matriz1.multiplicacao(matriz2, tamanho);

      }
   }

}