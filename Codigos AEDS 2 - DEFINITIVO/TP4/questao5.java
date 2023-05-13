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

class HashDiretoReserva {

    private Personagem[] pesoal;
    int m1, m2, m, reserva;
    private int n;
    
    public HashDiretoReserva(int m1, int m2) {
        this.m1 = m1;
        this.m2 = m2;
        this.m = m1 + m2;
        pesoal = new Personagem[m];
        for (int i = 0; i < m1; i++) {
            pesoal[i] = new Personagem();
        }
        reserva = 0;
        n = 0;
    }
    
    public HashDiretoReserva() {
        this(21, 9);
    }

    public int h(int elemento) {
        return elemento % m;
     }
    
    public int hash(String elemento) {
        int num = 0;
        for (int i = 0; i < elemento.length(); i++) {
            num += elemento.charAt(i);
        }
        return num % this.m1;
    }
    
    /**
     * Retorna o tamanho total da lista
     * @return
     */
    public int getTamanho() {
        return pesoal.length;
    }
    
    public boolean inserir(Personagem elemento) throws Exception {
        if (n >= this.m) {
            throw new Exception("ERRO");
        }
        boolean resp = false;
        if (elemento != null) {
            int pos = hash(elemento.getNome());
            if (pesoal[pos] == null) {
                pesoal[pos] = elemento;
                resp = true;
                n++;
            } else if (reserva < m2) {
                pesoal[m1 + reserva] = elemento;
                reserva++;
                resp = true;
                n++;
            }
        }
        return resp;
    }

    Personagem remover(Personagem elemento) throws Exception {
        if (n == 0) {
            throw new Exception("ERRO");
        }
        Personagem povo = pesoal[0];
        return povo;
    }

    public boolean pesquisar(String linha) {
        boolean resp = false;
        int pos = hash(linha);
    
        if(pesoal[pos].getNome().equals(linha)) {
            resp = true;
        } else if (pesoal[pos] != null) {
            for (int i = 0; i < reserva; i++) {
                if (pesoal[m1 + i].getNome().equals(linha)) {
                    resp = true;
                    i = reserva;
                }
            }
        }
        return resp;
    }
}

public class questao5{

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
        HashDiretoReserva tabelahash = new HashDiretoReserva();
        for(int i=0;i<numentrada;i++){
           Personagem aux = new Personagem();
			try {
				aux.ler(entrada[i]);
				tabelahash.inserir(aux);
			} catch (Exception e) {
			}
        }
        numentrada++;
  
        String entrada2 = "";
        boolean saida = false; 
        do{
            entrada2 = entrada1.nextLine();
            if(isFim(entrada2) == false){
                MyIO.println(""+entrada2);
                saida = tabelahash.pesquisar(entrada2);
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
