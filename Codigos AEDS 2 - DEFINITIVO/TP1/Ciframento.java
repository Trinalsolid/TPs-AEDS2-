class Ciframento{
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String Ciframento(int Chave , String s) {
        StringBuilder Ciframento = new StringBuilder();
        int tamanho = s.length();

        //Conversao da string para int , nos limites do alfabeto

        for(int i = 0 ; i < tamanho ; i++){
            int StringCifrada = ((int)s.charAt(i)) + (Chave);

            while(StringCifrada > 164){
                StringCifrada -= 32;
            }
            
            if(isFim(s)){
                break;
            }
            Ciframento.append((char)StringCifrada);
        }

        return Ciframento.toString();
    }

    public static void main(String[] args) {

        String[] entrada = new String[1000];

        do {
            entrada[0] = MyIO.readLine();
            Ciframento(3, entrada[0]);
            MyIO.println("" + Ciframento(3, entrada[0]));
        } while (isFim(entrada[0]) == false);
           //Desconsiderar ultima linha contendo a palavra FIM
    }
}
