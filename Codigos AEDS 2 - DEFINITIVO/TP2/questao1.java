import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Scanner;

class Personagem{

    private String nome;
    private int altura;
    private double peso;
    private String corDoCabelo;
    private String codDaPele;
    private String corDosOlhos;
    private String anoNascimento;
    private String genero;
    private String homeworld;

    public Personagem() {
        
    }

    public Personagem(String nome,int altura,double peso,String corDoCabelo,String codDaPele,String corDosOlhos,String anoNascimento, String genero ,String homeworld ) {
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.corDoCabelo = corDoCabelo;
        this.codDaPele = codDaPele;
        this.corDosOlhos = corDosOlhos;
        this.anoNascimento = anoNascimento;
        this.genero = genero;
        this.homeworld = homeworld;
    }

    // Gets

    public String getNome() {
        return nome;
    }
    public int getAltura() {
        return altura;
    }
    public double getPeso() {
        return peso;
    }
    public String getCorDoCabelo() {
        return corDoCabelo;
    }
    public String getCodDaPele() {
        return codDaPele;
    }
    public String getCorDosOlhos() {
        return corDosOlhos;
    }
    public String getAnoNascimento() {
        return anoNascimento;
    }
    public String getGenero() {
        return genero;
    }
    public String getHomeworld() {
        return homeworld;
    }

    //sets

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setAltura(int altura) {
        this.altura = altura;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
    public void setCorDoCabelo(String corDoCabelo) {
        this.corDoCabelo = corDoCabelo;
    }
    public void setCodDaPele(String codDaPele) {
        this.codDaPele = codDaPele;
    }
    public void setCorDosOlhos(String corDosOlhos) {
        this.corDosOlhos = corDosOlhos;
    }
    public void setAnoNascimento(String anoNascimento) {
        this.anoNascimento = anoNascimento;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public Personagem clone() {
        Personagem clone = new Personagem();

        clone.nome = nome;
        clone.altura = altura;
        clone.peso = peso;
        clone.corDoCabelo = corDoCabelo;
        clone.codDaPele = codDaPele;
        clone.corDosOlhos = corDosOlhos;
        clone.anoNascimento = anoNascimento;
        clone.genero = genero;
        clone.homeworld = homeworld;

        return clone;

    }

    public void imprimir(){
        System.out.println(nome + " " + altura + " " + peso + " " + corDoCabelo + " " + codDaPele+ " " + corDosOlhos + " " +anoNascimento + " " + genero + " " + homeworld);
    }

    public void ler(String nomeArq){
        String path = "D:\\RPG\\Filmes\\" + nomeArq;
        //D:\\RPG\\Filmes\\ /tmp/filmes/
        leitura(path);
    }

    public void leitura(String path){
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"))) {

            
        } catch (Exception e) {
            
        }
        
    }

    public void ler()throws Exception{
        String caminho = "D:\\RPG\\Filmes\\";
        String nome = new String();

        Scanner entrada = new Scanner(System.in);

    
        try {
            
            File arquivo = new File(caminho+nome);

        } catch (Exception e) {
        }


    }
}

public class questao1 {
 
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) {
        
    }

    
}
