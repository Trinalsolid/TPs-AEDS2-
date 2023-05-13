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

class Celula {
	public Personagem elemento; // Elemento inserido na celula.
	public Celula prox; // Aponta a celula prox.

	/**
	 * Construtor da classe.
	 * @param elemento Elemento inserido na celula.
	 */
	Celula(Personagem elemento) {
		this.elemento = elemento;
		this.prox = null;
	}

	/**
	 * Construtor da classe.
	 * @param elemento Elemento inserido na celula.
	 * @param prox Aponta a celula prox.
	 */
	Celula(Personagem elemento, Celula prox) {
		this.elemento = elemento;
		this.prox = prox;
	}
}

/**
 * Lista dinamica simplesmente encadeada
 * @author Joao Paulo Domingos Silva
 * @version 1.1 02/2012
 */
class Lista {
	private Celula primeiro; // Primeira celula: SEM elemento valido.
	private Celula ultimo; // Ultima celula: COM elemento valido.

	/**
	 * Construtor da classe: Instancia uma celula (primeira e ultima).
	 */
	public Lista() {
		primeiro = new Celula(new Personagem());
		ultimo = primeiro;
	}

	/**
	 * Mostra os elementos separados por espacos.
	 */
	public void mostrar() {
		System.out.print("[ "); // Comeca a mostrar.
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}
		System.out.println("] "); // Termina de mostrar.
	}

	/**
	 * Procura um elemento e retorna se ele existe.
	 * @param x Elemento a pesquisar.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(String x) {
		boolean retorno = false;
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
			if(i.elemento.getNome().compareTo(x) == 0){
				retorno = true;
				i = ultimo;
			}
		}
		return retorno;
	}

	/**
	 * Insere um elemento na primeira posicao da sequencia.
	 * @param elemento Elemento a inserir.
	 */
	public void inserirInicio(Personagem elemento) {
		Celula tmp = new Celula(elemento);
      	tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if (primeiro == ultimo) {
			ultimo = tmp;
		}
      	tmp = null;
	}

	/**
	 * Insere um elemento na ultima posicao da sequencia.
	 * @param elemento Elemento a inserir.
	 */
	public void inserirFim(Personagem elemento) {
		Celula tmp = new Celula(elemento);
		ultimo.prox = tmp;
		ultimo = ultimo.prox;
      	tmp = null;
	}

	/**
	 * Insere elemento em um indice especifico.
	 * Considera que primeiro elemento esta no indice 0.
	 * @param x Elemento a inserir.
	 * @param posicao Meio da insercao.
	 * @throws Exception Se <code>posicao</code> for incorreta.
	 */
  	 public void inserirMeio(Personagem x, int posicao) throws Exception {
		Celula i;
		int cont;

		// Caminhar ate chegar na posicao anterior a insercao
      	for(i = primeiro, cont = 0; (i.prox != ultimo && cont < posicao); i = i.prox, cont++);
		
		// Se indice for incorreto:
		if (posicao < 0 || posicao > cont + 1) {
			throw new Exception("Erro ao inserir (posicao " + posicao + "(cont = " + cont + ") invalida)!");

		} else if (posicao == cont + 1) {
			inserirFim(x);
		}else{
			Celula tmp = new Celula(x);
			tmp.prox = i.prox;
			i.prox = tmp;
			tmp = i = null;
		}
   	}

	/**
	 * Remove um elemento da primeira posicao da sequencia.
	 * @return Elemento removido.
	 * @throws Exception Se a sequencia nao contiver elementos.
	 */
	public Personagem removerInicio() throws Exception {
		Personagem resp = new Personagem();

		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}else{
			primeiro = primeiro.prox;
			resp = primeiro.elemento;
		}

		return resp;
	}

	/**
	 * Remove um elemento da ultima posicao da sequencia.
	 * @return Elemento removido.
	 * @throws Exception Se a sequencia nao contiver elementos.
	 */
	public Personagem removerFim() throws Exception {
		Personagem resp =  new Personagem();
      	Celula i = null;

		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		} else {

         resp = ultimo.elemento;

		   // Caminhar ate a penultima celula:
         for(i = primeiro; i.prox != ultimo; i = i.prox);

         ultimo = i;
         i = ultimo.prox = null;
      }

		return resp;
	}

	/**
	 * Remove elemento de um indice especifico.
	 * Considera que primeiro elemento esta no indice 0.
	 * @param posicao Meio da remocao.
	 * @return Elemento removido.
	 * @throws Exception Se <code>posicao</code> for incorreta.
	 */
	public Personagem removerMeio(int posicao) throws Exception {
		Celula i;
		Personagem resp = new Personagem();

		int cont;

			if (primeiro == ultimo){
				throw new Exception("Erro ao remover (vazia)!");
		}else{

			// Caminhar ate chegar na posicao anterior a insercao
			for(i = primeiro, cont = 0; (i.prox != ultimo && cont < posicao); i = i.prox, cont++);

			// Se indice for incorreto:
			if (posicao < 0 || posicao > cont + 1) {
				throw new Exception("Erro ao remover (posicao " + posicao + " invalida)!");

			} else if (posicao == cont + 1) {
				resp = removerFim();
			}else{
				Celula tmp = i.prox;
				resp = tmp.elemento;
				i.prox = tmp.prox;
				tmp.prox = null;
				i = tmp = null;
			}
		}

		return resp;
	}
}


class HashIndiretoLista {
    Lista tabela[];
    int tamanho;
    final Personagem NULO = new Personagem();
 
    public HashIndiretoLista() {
       this(21);
    }
 
    public HashIndiretoLista(int tamanho) {
       this.tamanho = tamanho;
       tabela = new Lista[tamanho];
       for (int i = 0; i < tamanho; i++) {
          tabela[i] = new Lista();
       }
    }
 
    public int h(int elemento) {
       return elemento % tamanho;
    }
 
    boolean pesquisar(String elemento) {
		int soma = 0;
		for(int i = 0; i < elemento.length(); i++){
				soma = soma + (int) elemento.charAt(i);
		}
		int pos = h(soma);
		if(tabela[pos].pesquisar(elemento) == true){
			System.out.println("" + pos);
		}
       	return tabela[pos].pesquisar(elemento);
    }
 
    public void inserirInicio(Personagem elemento) {
        int soma = 0;
		for(int i = 0; i < elemento.getAltura(); i++){
			soma = soma + elemento.getAltura();
		}
		int pos = h(soma);
		tabela[pos].inserirInicio(elemento);
    }
 
}

public class questao7{

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
        HashIndiretoLista hashcomlista = new HashIndiretoLista();
        for(int i=0;i<numentrada;i++){
           Personagem aux = new Personagem();
			try {
				aux.ler(entrada[i]);
				hashcomlista.inserirInicio(aux);
			} catch (Exception e) {
			}
        }
        numentrada++;
  
        String entrada2 = "";
        boolean saida = false; 
        do{
            entrada2 = entrada1.nextLine();
            if(isFim(entrada2) == false){
                System.out.println(""+entrada2);
                saida = hashcomlista.pesquisar(entrada2);
                if(saida == false){
                    MyIO.println("NÃO");
                }else{
                    MyIO.println("SIM"); 
                }
             }
        }while(isFim(entrada2) == false);
        
        entrada1.close();
	}
}
