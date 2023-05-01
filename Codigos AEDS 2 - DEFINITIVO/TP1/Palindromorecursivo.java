class Palindromorecursivo{
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static Boolean palindromorec(String original){
        return palindromorec(original, 0, (original.length()-1));
    }

    public static Boolean palindromorec(String original, int i, int j){
        
        boolean resp = true;

        if(j >= i){
            if(original.charAt(i) != original.charAt(j)){
                resp = false;
            }
            else{
                resp = palindromorec(original, i+1, j-1);
            }
        }

        return resp;
    }

    public static void main(String[] args) {
        String[] entrada = new String[1000];
        int numEntrada = 0;

        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--; // Desconsiderar ultima linha contendo a palavra FIM

        for(int i = 0; i < numEntrada; i++){

            if(palindromorec(entrada[i]) == true){
                MyIO.println("SIM");
            }else{
                MyIO.println("NAO");
            }
            
        }
    
    }


}
