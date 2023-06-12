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
    public int elemento; 
    public No esq; 
    public No dir; 
    public No2 outro;
    
    No(int elemento) {
        this.elemento = elemento;
        this.esq = this.dir = null;
        this.outro = null;
    }
    No(int elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
          this.outro = null;
    }
}

class No2 {
    public Personagem elemento; 
    public No2 esq; 
    public No2 dir; 
    
    No2(Personagem elemento) {
        this.elemento = elemento;
        this.esq = this.dir = null;
    }

    No2(Personagem elemento, No2 esq, No2 dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreArvore{
	private No raiz; // Raiz da arvore.
    public String caminho = "";

	/**
	 * Construtor da classe.
	 * @throws Exception
	 */
	public ArvoreArvore() throws Exception {
		raiz = null; // 7, 3, 11, 1, 5, 9, 12, 0, 2, 4, 6, 8, 10, 13 e 14
        inserir(7);
        inserir(3);
        inserir(11);
        inserir(1);
        inserir(5);
        inserir(9);
        inserir(12);
        inserir(0);
        inserir(2);
        inserir(4);
        inserir(6);
        inserir(8);
        inserir(10);
        inserir(13);
        inserir(14);
	}
    public void inserir(int x) throws Exception {
        raiz = inserir(x, raiz);
    }

    private No inserir(int x, No i) throws Exception {
        if (i == null) {
            i = new No(x);
        } else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);
        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);
        } else {
            throw new Exception("Erro ao inserir!");
        }
        return i;
    }

    public void inserir(Personagem s) throws Exception {
        inserir(s, raiz);
    }

    public void inserir(Personagem s, No i) throws Exception {
        if (i == null) {
            throw new Exception("Erro ao inserir: caractere invalido!");
        } else if (s.getAltura() % 15 < i.elemento) {
            inserir(s, i.esq);
        } else if (s.getAltura() % 15 > i.elemento) {
            inserir(s, i.dir);
        } else {
            i.outro = inserir(s, i.outro);
        }
    }

    private No2 inserir(Personagem s, No2 i) throws Exception {
        if (i == null) {
            i = new No2(s);
        } else if (s.getNome().compareTo(i.elemento.getNome()) < 0) {
            i.esq = inserir(s, i.esq);
        } else if (s.getNome().compareTo(i.elemento.getNome()) > 0) {
            i.dir = inserir(s, i.dir);
        } else {
            throw new Exception("Erro ao inserir");
        }
        return i;
    }
	
    public boolean pesquisar(Personagem elemento) {
		return pesquisar(raiz, elemento);
	}

	private boolean pesquisar(No no, Personagem x) {
		boolean resp;
		if (no == null) { 
			resp = false;
		} else if (x.getAltura() % 15 < no.elemento) { 
			resp = pesquisar(no.esq, x);
			System.out.println("dir "); 
		} else if (x.getAltura() % 15 > no.elemento) { 
			resp = pesquisar(no.dir, x);
			System.out.println("esq "); 
		} else { 
			resp = pesquisarSegundaArvore(no.outro, x); 
		}
		return resp;
	}

	private boolean pesquisarSegundaArvore(No2 no, Personagem x) {
		boolean resp;
		if (no == null) { 
			resp = false;
		} else if (x.getNome().compareTo(no.elemento.getNome()) < 0) { 
			resp = pesquisarSegundaArvore(no.esq, x); 
			System.out.println("ESQ ");
		} else if (x.getNome().compareTo(no.elemento.getNome()) > 0) { 
			resp = pesquisarSegundaArvore(no.dir, x);
			System.out.println("DIR "); 
		} else { 
			resp = true; 
		}
		return resp;
	}

}


public class questao2{

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
		ArvoreArvore arvorearvore = new ArvoreArvore();
        for(int i=0;i<numentrada;i++){
           Personagem aux = new Personagem();
			try {
				aux.ler(entrada[i]);
				arvorearvore.inserir(aux);
			} catch (Exception e) {
			}
        }
        numentrada++;
  
        String entrada2 = "";
        String saida = ""; 
        do{
            entrada2 = entrada1.nextLine();
            if(isFim(entrada2) == false){
                MyIO.println(""+entrada2);
                saida = arvorearvore.pesquisar(entrada2);
                System.out.println(saida);
            }
        }while(isFim(entrada2) == false);    
        
        entrada1.close();
	}
}
