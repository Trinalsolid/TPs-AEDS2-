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

class Pilha {
    Personagem[] array;
    int n;
 
 
    /**
     * Construtor da classe.
     */
    public Pilha () {
       this(6);
    }
 
 
    /**
     * Construtor da classe.
     * @param tamanho Tamanho da Pilha.
     */
    public Pilha (int tamanho){
       array = new Personagem[tamanho];
       n = 0;
    }

    /**
     * Insere um elemento na ultima posicao da Pilha.
     * @param x int elemento a ser inserido.
     * @throws Exception Se a Pilha estiver cheia.
     */
    public void inserir(Personagem x) throws Exception {
 
       //validar insercao
       if(n >= array.length){
          throw new Exception("Erro ao inserir!");
       }
 
       array[n] = x;
       n++;
    }
 

 
    /**
     * Remove um elemento da ultima posicao da Pilha.
     * @return resp int elemento a ser removido.
     * @throws Exception Se a Pilha estiver vazia.
     */
    public Personagem remover() throws Exception {
 
       //validar remocao
       if (n == 0) {
          throw new Exception("Erro ao remover!");
       }
 
       return array[--n];
    }
 
 

    /**
     * Mostra os elementos da Pilha separados por espacos.
     */
    public void mostrar (){
       for(int i = 0; i < n; i++){
            System.out.print("["+i+"] ");
            array[i].imprimir();
       }
    }
 
}

public class questao5{

    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

	public static void opCode(String s, Pilha Pilha) throws Exception{
        String[] split = new String[3];
        split = s.split(" ",2);
        String op   = split[0];
        String entrada = "";
        //int posicao = 0;
        if(op.charAt(0)=='I'){
            entrada = split[1];
        }
        Personagem aux2 = new Personagem();
        //INSERIR
        if(op.charAt(0)=='I'){
            aux2.ler(entrada);
            try {
                Pilha.inserir(aux2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //REMOVER
        else if(op.charAt(0)=='R'){
            try {
                aux2=Pilha.remover();
            } catch (Exception e) {
                e.printStackTrace();
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
        Pilha pilha = new Pilha(100);
        for(int i=0;i<numEntrada;i++){
            Personagem aux = new Personagem();
            try {
                aux.ler(entrada[i]);
                pilha.inserir(aux);
            } catch (Exception e) {
            }
        }
        numEntrada++;
        
        entrada[numEntrada] = sc.nextLine();
        int tmp = numEntrada;
        int movimentacoes = Integer.parseInt(entrada[numEntrada]);
        do{
            entrada[numEntrada]=sc.nextLine();
            opCode(entrada[numEntrada], pilha);
        }while(numEntrada++ < (movimentacoes+tmp-1));
        pilha.mostrar();
        sc.close();
    }
}
