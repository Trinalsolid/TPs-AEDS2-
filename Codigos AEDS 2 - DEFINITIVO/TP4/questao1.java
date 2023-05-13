import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Scanner;

import javax.annotation.processing.FilerException;
import javax.swing.text.html.parser.Element;

class Personagem {

	private String nome;
	private int altura;
	private double peso;
	private String corDoCabelo;
	private String corDaPele;
	private String corDosOlhos;
	private String anoNascimento;
	private String genero;
	private String homeworld;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getCorDoCabelo() {
		return corDoCabelo;
	}

	public void setCorDoCabelo(String corDoCabelo) {
		this.corDoCabelo = corDoCabelo;
	}

	public String getCorDaPele() {
		return corDaPele;
	}

	public void setCorDaPele(String corDaPele) {
		this.corDaPele = corDaPele;
	}

	public String getCorDosOlhos() {
		return corDosOlhos;
	}

	public void setCorDosOlhos(String corDosOlhos) {
		this.corDosOlhos = corDosOlhos;
	}

	public String getAnoNascimento() {
		return anoNascimento;
	}

	public void setAnoNascimento(String anoNascimento) {
		this.anoNascimento = anoNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getHomeworld() {
		return homeworld;
	}

	public void setHomeworld(String homeworld) {
		this.homeworld = homeworld;
	}

	protected Personagem clone() throws CloneNotSupportedException {
		Personagem novo = new Personagem();
		novo.nome = this.nome;
		novo.altura = this.altura;
		novo.corDoCabelo = this.corDoCabelo;
		novo.corDaPele = this.corDaPele;
		novo.corDosOlhos = this.corDosOlhos;
		novo.anoNascimento = this.anoNascimento;
		novo.genero = this.genero;
		novo.homeworld = this.homeworld;
		return novo;
	}

	public void ler(String nomeArquivo) throws Exception {
		FileReader file = new FileReader(nomeArquivo);
		BufferedReader buffer = new BufferedReader(file);
		String json = "";
		String line = buffer.readLine();
		while (line != null) {
			json += line;
			line = buffer.readLine();
		}

		buffer.close();
		file.close();

		String temp;
		temp = json.substring(json.indexOf("name") + 8);
		temp = temp.substring(0, temp.indexOf("',"));
		this.nome = temp;

		temp = json.substring(json.indexOf("height") + 10);
		temp = temp.substring(0, temp.indexOf("',"));
		if (temp.equals("unknown"))
			this.altura = 0;
		else
			this.altura = Integer.parseInt(temp);

		temp = json.substring(json.indexOf("mass") + 8);
		temp = temp.substring(0, temp.indexOf("',"));
		if (temp.equals("unknown"))
			this.peso = 0;
		else
			this.peso = Double.parseDouble(temp.replace(",", ""));

		temp = json.substring(json.indexOf("hair_color") + 14);
		temp = temp.substring(0, temp.indexOf("',"));
		this.corDoCabelo = temp;

		temp = json.substring(json.indexOf("skin_color") + 14);
		temp = temp.substring(0, temp.indexOf("',"));
		this.corDaPele = temp;

		temp = json.substring(json.indexOf("eye_color") + 13);
		temp = temp.substring(0, temp.indexOf("',"));
		this.corDosOlhos = temp;

		temp = json.substring(json.indexOf("birth_year") + 14);
		temp = temp.substring(0, temp.indexOf("',"));
		this.anoNascimento = temp;

		temp = json.substring(json.indexOf("gender") + 10);
		temp = temp.substring(0, temp.indexOf("',"));
		this.genero = temp;

		temp = json.substring(json.indexOf("homeworld") + 13);
		temp = temp.substring(0, temp.indexOf("',"));
		this.homeworld = temp;
	}

	public void imprimir() {
		System.out.println(toString());
	}

	public String toString() {
		DecimalFormat df = new DecimalFormat("#0.##");
		String resp = " ## " + nome + " ## " + altura + " ## ";
		resp += df.format(peso) + " ## " + corDoCabelo + " ## ";
		resp += corDaPele + " ## " + corDosOlhos + " ## ";
		resp += anoNascimento + " ## " + genero + " ## ";
		resp += homeworld + " ## ";
		return resp;
	}

	public void imprimirNome() {
		System.out.println(nome);
	}

}

class No {
    public Personagem elemento; 
    public No esq, dir;  
 
    
    public No(Personagem elemento) {
        this(elemento, null, null);
    }
 
   
    public No(Personagem elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}
 
class ArvoreBinaria{
    private No raiz; // Raiz da arvore.
 
    
    public ArvoreBinaria() {
        raiz = null;
    }
 
     /**
      * Metodo publico iterativo para pesquisar elemento.
      * @param x Elemento que sera procurado.
      * @return <code>true</code> se o elemento existir,
      * <code>false</code> em caso contrario.
      */
    public boolean pesquisar(String x) {
        MyIO.print("raiz ");
        return pesquisar(x, raiz);
    }
 
     /**
      * Metodo privado recursivo para pesquisar elemento.
      * @param x Elemento que sera procurado.
      * @param i No em analise.
      * @return <code>true</code> se o elemento existir,
      * <code>false</code> em caso contrario.
      */
    private boolean pesquisar(String x, No i) {
        boolean resp;
            if (i == null) {
            resp = false;
        } else if (x.compareTo(i.elemento.getNome()) == 0) {
            resp = true;
    
    
        } else if (x.compareTo(i.elemento.getNome()) < 0) {
            MyIO.print("esq "); 
            resp = pesquisar(x, i.esq);
            
    
        } else {
            MyIO.print("dir "); 
            resp = pesquisar(x, i.dir);
            
        }
        return resp;
    }
 
     /**
      * Metodo publico iterativo para exibir elementos.
      */
    public void caminharCentral() {
        System.out.print("In..: ");
        caminharCentral(raiz);
        System.out.println("\t");
    }
 
     /**
      * Metodo privado recursivo para exibir elementos.
      * @param i No em analise.
      */
    private void caminharCentral(No i) {
        if (i != null) {
            caminharCentral(i.esq); // Elementos da esquerda.
            System.out.println(i.elemento.getNome() + " "); // Conteudo do no.
            caminharCentral(i.dir); // Elementos da direita.
        }
    }
 
     /**
      * Metodo publico iterativo para exibir elementos.
      */
    public void caminharPre() {
        System.out.print("Pre.: ");
        caminharPre(raiz);
        System.out.println("\t");
    }
 
     /**
      * Metodo privado recursivo para exibir elementos.
      * @param i No em analise.
      */
    private void caminharPre(No i) {
        if (i != null) {
            System.out.print(i.elemento + " "); // Conteudo do no.
            caminharPre(i.esq); // Elementos da esquerda.
            caminharPre(i.dir); // Elementos da direita.
        }
     }
 
     /**
      * Metodo publico iterativo para exibir elementos.
      */
    public void caminharPos() {
        System.out.print("Post: ");
        caminharPos(raiz);
        System.out.println("\t");
    }
 
     /**
      * Metodo privado recursivo para exibir elementos.
      * @param i No em analise.
      */
    private void caminharPos(No i) {
        if (i != null) {
            caminharPos(i.esq); // Elementos da esquerda.
            caminharPos(i.dir); // Elementos da direita.
            System.out.print(i.elemento + " "); // Conteudo do no.
        }
    }
 
 
     /**
      * Metodo publico iterativo para inserir elemento.
      * @param x Elemento a ser inserido.
      * @throws Exception Se o elemento existir.
      */
    public void inserir(Personagem x) throws Exception {
        raiz = inserir(x, raiz);
    }
 
     /**
      * Metodo privado recursivo para inserir elemento.
      * @param x Elemento a ser inserido.
      * @param i No em analise.
      * @return No em analise, alterado ou nao.
      * @throws Exception Se o elemento existir.
      */
    private No inserir(Personagem x, No i) throws Exception {
        if (i == null) {
          i = new No(x);
        } else if (x.getNome().compareTo(i.elemento.getNome()) < 0) {
            i.esq = inserir(x, i.esq);
    
        } else if (x.getNome().compareTo(i.elemento.getNome()) > 0) {
            i.dir = inserir(x, i.dir);
    
        } else {
            throw new Exception("Erro ao inserir!");
        }
         return i;
    }
 
     /**
      * Metodo publico para inserir elemento.
      * @param x Elemento a ser inserido.
      * @throws Exception Se o elemento existir.
      */
    public void inserirPai(Personagem x) throws Exception {
        if(raiz == null){
            raiz = new No(x);
        } else if(x.getNome().compareTo(raiz.elemento.getNome()) < 0){
            inserirPai(x, raiz.esq, raiz);
        } else if(x.getNome().compareTo(raiz.elemento.getNome()) > 0){
            inserirPai(x, raiz.dir, raiz);
        } else {
            throw new Exception("Erro ao inserirPai!");
        }
    }
 
     /**
      * Metodo privado recursivo para inserirPai elemento.
      * @param x Elemento a ser inserido.
      * @param i No em analise.
      * @param pai No superior ao em analise.
      * @throws Exception Se o elemento existir.
      */
    private void inserirPai(Personagem x, No i, No pai) throws Exception {
        if (i == null) {
            if(x.getNome().compareTo(pai.elemento.getNome()) < 0){
                pai.esq = new No(x);
            } else {
                pai.dir = new No(x);
            }
        } else if (x.getNome().compareTo(i.elemento.getNome()) < 0) {
            inserirPai(x, i.esq, i);
        } else if (x.getNome().compareTo(i.elemento.getNome()) > 0) {
            inserirPai(x, i.dir, i);
        } else {
            throw new Exception("Erro ao inserirPai!");
        }
    }
 
 
     /**
      * Metodo publico iterativo para remover elemento.
      * @param x Elemento a ser removido.
      * @throws Exception Se nao encontrar elemento.
      */
    public void remover(String x) throws Exception {
        raiz = remover(x, raiz);
    }
 
     /**
      * Metodo privado recursivo para remover elemento.
      * @param x Elemento a ser removido.
      * @param i No em analise.
      * @return No em analise, alterado ou nao.
      * @throws Exception Se nao encontrar elemento.
      */
    private No remover(String x, No i) throws Exception {
 
        if (i == null) {
            //throw new Exception("Erro ao remover!");
        } else if (x.compareTo(i.elemento.getNome()) < 0) {
            i.esq = remover(x, i.esq);
        } else if (x.compareTo(i.elemento.getNome()) > 0) {
            i.dir = remover(x, i.dir);
        // Sem no a direita.
        } else if (i.dir == null) {
            i = i.esq;
    
        // Sem no a esquerda.
        } else if (i.esq == null) {
            i = i.dir;
        // No a esquerda e no a direita.
        } else {
            i.esq = maiorEsq(i, i.esq);
        }
 
        return i;
    }
 
     /**
      * Metodo para trocar o elemento "removido" pelo maior da esquerda.
      * @param i No que teve o elemento removido.
      * @param j No da subarvore esquerda.
      * @return No em analise, alterado ou nao.
      */
    private No maiorEsq(No i, No j) {
 
       // Encontrou o maximo da subarvore esquerda.
        if (j.dir == null) {
            i.elemento = j.elemento; // Substitui i por j.
            j = j.esq; // Substitui j por j.ESQ.
        } else {
            j.dir = maiorEsq(i, j.dir);
        }
        return j;
    }
 
     /**
      * Metodo que retorna a altura da árvore
      * @return int altura da árvore
      */
    public int getAltura(){
       return getAltura(raiz, 0);
    }
 
 
     /**
      * Metodo que retorna a altura da árvore
      * @return int altura da árvore
      */
    public int getAltura(No i, int altura){
       if(i == null){
          altura--;
       } else {
          int alturaEsq = getAltura(i.esq, altura + 1);
          int alturaDir = getAltura(i.dir, altura + 1);
          altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
       }
       return altura;
    }
 
 
     /**
      * Metodo publico iterativo para remover elemento.
      * @param x Elemento a ser removido.
      * @throws Exception Se nao encontrar elemento.
      */
    public void remover2(Personagem x) throws Exception {
        if (raiz == null) {
            throw new Exception("Erro ao remover2!");
        } else if(x.getNome().compareTo(raiz.elemento.getNome()) < 0){
            remover2(x, raiz.esq, raiz);
        } else if (x.getNome().compareTo(raiz.elemento.getNome()) > 0){
            remover2(x, raiz.dir, raiz);
        } else if (raiz.dir == null) {
            raiz = raiz.esq;
        } else if (raiz.esq == null) {
            raiz = raiz.dir;
        } else {
            raiz.esq = maiorEsq(raiz, raiz.esq);
        }
    }
 
     /**
      * Metodo privado recursivo para remover elemento.
      * @param x Elemento a ser removido.
      * @param i No em analise.
      * @param pai do No em analise.
      * @throws Exception Se nao encontrar elemento.
      */
    private void remover2(Personagem x, No i, No pai) throws Exception {
        if (i == null) {
            throw new Exception("Erro ao remover2!");
        } else if (x.getNome().compareTo(raiz.elemento.getNome()) < 0) {
            remover2(x, i.esq, i);
        } else if (x.getNome().compareTo(raiz.elemento.getNome()) > 0) {
            remover2(x, i.dir, i);
        } else if (i.dir == null) {
            pai = i.esq;
        } else if (i.esq == null) {
            pai = i.dir;
        } else {
          i.esq = maiorEsq(i, i.esq);
        }
    }
 
    public Personagem getRaiz() throws Exception {
       return raiz.elemento;
    }
 
    public static boolean igual (ArvoreBinaria a1, ArvoreBinaria a2){
       return igual(a1.raiz, a2.raiz);
    }
 
    private static boolean igual (No i1, No i2){
       boolean resp;
       if(i1 != null && i2 != null){
          resp = (i1.elemento == i2.elemento) && igual(i1.esq, i2.esq) && igual(i1.dir, i2.dir);
       } else if(i1 == null && i2 == null){
          resp = true;
       } else {
          resp = false; 
       }
       return resp;
    }
    
 }

public class questao1{

    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

	public static void main(String[] args) throws Exception {
        String[] entrada = new String[1000];
        int numentrada = 0;
        String aux2 = "";
        Scanner entrada1 = new Scanner(System.in);
        
        do {
            entrada[numentrada] = entrada1.nextLine();
            aux2 = entrada[numentrada];
            numentrada++;
        } while (isFim(aux2) == false);
        numentrada--;
        ArvoreBinaria arvorebin = new ArvoreBinaria();
        for(int i=0;i<numentrada;i++){
           Personagem aux = new Personagem();
			try {
				aux.ler(entrada[i]);
				arvorebin.inserir(aux);
			} catch (Exception e) {
			}
        }
        numentrada++;
  
        String entrada2 = "";
        boolean saida = false; 
        do{
            entrada2 = entrada1.nextLine();
            if(isFim(entrada2) == false){
                System.out.print(entrada2+" ");
                saida = arvorebin.pesquisar(entrada2);
                if(saida == false){
                    System.out.println("NÃO");
                }else{
                    System.out.println("SIM"); 
                }
            }
        }while(isFim(entrada2) == false);
        
        entrada1.close();
	}
}
