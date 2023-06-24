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

class Lista {
    Personagem[] array;
    int n;
 
 
    /**
     * Construtor da classe.
     */
    public Lista () {
       this(6);
    }
 
 
    /**
     * Construtor da classe.
     * @param tamanho Tamanho da lista.
     */
    public Lista (int tamanho){
       array = new Personagem[tamanho];
       n = 0;
    }
 
 
    /**
     * Insere um elemento na primeira posicao da lista e move os demais
     * elementos para o fim da lista.
     * @param x int elemento a ser inserido.
     * @throws Exception Se a lista estiver cheia.
     */
    public void inserirInicio(Personagem x) throws Exception {
 
       //validar insercao
       if(n >= array.length){
          throw new Exception("Erro ao inserir!");
       } 
 
       //levar elementos para o fim do array
       for(int i = n; i > 0; i--){
          array[i] = array[i-1];
       }
 
       array[0] = x;
       n++;
    }
 
 
    /**
     * Insere um elemento na ultima posicao da lista.
     * @param x int elemento a ser inserido.
     * @throws Exception Se a lista estiver cheia.
     */
    public void inserirFim(Personagem x) throws Exception {
 
       //validar insercao
       if(n >= array.length){
          throw new Exception("Erro ao inserir!");
       }
 
       array[n] = x;
       n++;
    }
 
 
    /**
     * Insere um elemento em uma posicao especifica e move os demais
     * elementos para o fim da lista.
     * @param x int elemento a ser inserido.
     * @param pos Posicao de insercao.
     * @throws Exception Se a lista estiver cheia ou a posicao invalida.
     */
    public void inserir(Personagem x, int pos) throws Exception {
 
       //validar insercao
       if(n >= array.length || pos < 0 || pos > n){
          throw new Exception("Erro ao inserir!");
       }
 
       //levar elementos para o fim do array
       for(int i = n; i > pos; i--){
          array[i] = array[i-1];
       }
 
       array[pos] = x;
       n++;
    }
 
 
    /**
     * Remove um elemento da primeira posicao da lista e movimenta 
     * os demais elementos para o inicio da mesma.
     * @return resp int elemento a ser removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public Personagem removerInicio() throws Exception {
 
       //validar remocao
       if (n == 0) {
          throw new Exception("Erro ao remover!");
       }
 
       Personagem resp = array[0];
       n--;
 
       for(int i = 0; i < n; i++){
          array[i] = array[i+1];
       }
 
       return resp;
    }
 
 
    /**
     * Remove um elemento da ultima posicao da lista.
     * @return resp int elemento a ser removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public Personagem removerFim() throws Exception {
 
       //validar remocao
       if (n == 0) {
          throw new Exception("Erro ao remover!");
       }
 
       return array[--n];
    }
 
 
    /**
     * Remove um elemento de uma posicao especifica da lista e 
     * movimenta os demais elementos para o inicio da mesma.
     * @param pos Posicao de remocao.
     * @return resp int elemento a ser removido.
     * @throws Exception Se a lista estiver vazia ou a posicao for invalida.
     */
    public Personagem remover(int pos) throws Exception {
 
       //validar remocao
       if (n == 0 || pos < 0 || pos >= n) {
          throw new Exception("Erro ao remover!");
       }
 
       Personagem resp = array[pos];
       n--;
 
       for(int i = pos; i < n; i++){
          array[i] = array[i+1];
       }
 
       return resp;
    }
 
 
    /**
     * Mostra os elementos da lista separados por espacos.
     */
    public void mostrar (){
       for(int i = 0; i < n; i++){
            System.out.print("["+i+"] ");
            array[i].imprimir();
       }
    }
 
}

public class questao3{

    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

	public static void opCode(String s, Lista lista) throws Exception{
        String[] split = new String[3];
        split = s.split(" ",3);
        String op   = split[0];
        int posicao = 0;
        String entrada = " ";
        
        if(op.charAt(0)=='I'){
            if(op.charAt(1)=='*'){
                //op[0]
                posicao = Integer.parseInt(split[1]);
                entrada = split[2];
            }
            else{
                //op[0]
                split = s.split(" ",2);
                entrada = split[1];
            }
        }
        else if(op.charAt(0)=='R'){
            if(op.charAt(1)=='*'){
                posicao = Integer.parseInt(split[1]);
            }
        }
        /*resolve a operacoes */
        Personagem aux2 = new Personagem();
        //INSERIR
        if(op.charAt(0)=='I'){
            //INICIO
            aux2.ler(entrada);
            if(op.charAt(1)=='I'){
                try {
                    lista.inserirInicio(aux2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //FIM
            else if(op.charAt(1)=='F'){
                try {
                    lista.inserirFim(aux2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //POSICAO
            else if(op.charAt(1)=='*'){
                try {
                    lista.inserir(aux2, posicao);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //REMOVER
        else if(op.charAt(0)=='R'){
            //INICIO
            if(op.charAt(1)=='I'){
                try {
                    aux2=lista.removerInicio();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //FIM
            else if(op.charAt(1)=='F'){
                try {
                    aux2=lista.removerFim();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //POSICAO
            else if(op.charAt(1)=='*'){
                try {
                    aux2=lista.remover(posicao);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("(R) "+aux2.getNome());
        }
        
    }

	public static void main(String[] args) throws Exception {
        String[] entrada = new String[1000];
        int numEntrada = 0;
        Scanner sc = new Scanner(System.in);
        do{
            entrada[numEntrada] = sc.nextLine();
        }while(isFim(entrada[numEntrada++]) == false);
        
        numEntrada--;
        Lista lista = new Lista(100);
        for(int i=0;i<numEntrada;i++){
            Personagem aux = new Personagem();
            try {
                aux.ler(entrada[i]);
                lista.inserirFim(aux);
            } catch (Exception e) {
            }
        }
        numEntrada++;
        
        entrada[numEntrada] = sc.nextLine();
        int tmp = numEntrada;
        int movimentacoes = Integer.parseInt(entrada[numEntrada]);
        do{
            entrada[numEntrada]=sc.nextLine();
            opCode(entrada[numEntrada], lista);
        }while(numEntrada++ < (movimentacoes+tmp-1));
        lista.mostrar();
        sc.close();
    }
}
