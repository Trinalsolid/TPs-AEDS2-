#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * @author Bernardo Marques Fernandes - 774119
 */

// ==================================== Definitions ================================== //

#define N_JSONS 86

typedef struct SWCharacter_s SWCharacter;

// ==================================== Prototypes ================================== //

size_t str_len(const char* const str);
short str_equals(const char* const str, const char* const other);
char* str_substring(const char* const str, const size_t start, const size_t end);
char* str_clone(const char* const str);
void str_remove_all(char* const str, const char c);
void file_to_string(const char* const path, char* const str);
char* json_substring(const char* const str, const char* const key, size_t* const index);
int parse_int(const char* const str);
double parse_double(const char* str);
short isFim(const char* const str);

SWCharacter SWCharacter_new();
SWCharacter SWCharacter_from_file(const char* const path);
SWCharacter swc_clone(const SWCharacter* const self);
void swc_print(const SWCharacter* const self);
void swc_free(SWCharacter* const self, const size_t len);

// ==================================== Utils ================================== //

size_t str_len(const char* const str) {
    size_t len = 0;

    while(str[len])
        len++;

    return len;
}

short str_equals(const char* const str, const char* const other) {
    size_t i = 0;
    short equals = 1;
    
    while(equals && str[i] && other[i]) {
        equals = str[i] == other[i];
        i++;
    }

    return equals && !(str[i] || other[i]);
}

char* str_substring(const char* const str, const size_t start, const size_t end) {
    size_t i = 0;
    char* substr = (char*)malloc((end - start + 1) * sizeof(char));

    while(i < end - start) {
        substr[i] = str[start + i];
        substr[++i] = '\0';
    }

    return substr;
}

char* str_clone(const char* const str) {
    size_t len = str_len(str) + 1;
    char* copy = (char*)malloc(len * sizeof(char));
    for(size_t i = 0; i < len; i++) copy[i] = str[i];
    return copy;
}

void str_remove_all(char* const str, const char c) {
    size_t len = str_len(str);
    size_t i = 0;

    while(i < len && str[i] != c) i++;
    
    for(size_t j = i; j < len; i++, j++) {
        if(str[i] == c) j++;
        str[i] = str[j];
    }

    str[i] = '\0';
}

void file_to_string(const char* const path, char* const str) {
    FILE* f = fopen(path, "r");
    fscanf(f, " %1000[^\n]", str);
    fclose(f);
}

char* json_substring(const char* const str, const char* const key, size_t* const index) {
    size_t start = *index + str_len(key) + 4;
    *index = start;
    while(str[++*index] != '\'');
    *index += 4;
    return str_substring(str, start, *index - 4);
}

int parse_int(const char* const str) {
    int a = 0;
    int b = 1;

    for(int i = str_len(str) - 1; i >= 0; i--, b *= 10)
        a += (str[i] - 48) * b;

    return a;
}

double parse_double(const char* str) {
    double value = 0;
    int dot = 0;
    double scale = 1;
    int negative = 0; 

    if(*str == '-'){
        str++;
        negative = 1;
    }
    
    while(*str) {
        if(dot) {
            scale = scale / 10;
            value = value + (*str - 48) * scale;
        } else {
            if(*str == '.') dot++;
            else value = value * 10.0 + (*str - 48);
        }
        
	    str++;
    }
    
    return negative ? -value : value;
}

short isFim(const char* const str) {
    return str_equals(str, "FIM");
}

// ==================================== Structs ================================== //

struct SWCharacter_s {
    char* name;
    int height;
    double mass;
    char* hair_color;
    char* skin_color;
    char* eye_color;
    char* birth_year;
    char* gender;
    char* homeworld;
};

// ==================================== Constructors ================================== //

SWCharacter SWCharacter_new() {
    SWCharacter self;
    
    self.name = NULL;
    self.height = 0;
    self.mass = 0;
    self.hair_color = NULL;
    self.skin_color = NULL;
    self.eye_color = NULL;
    self.birth_year = NULL;
    self.gender = NULL;
    self.homeworld = NULL;

    return self;
}

SWCharacter SWCharacter_from_file(const char* const path) {
    char* json = (char*)malloc(1000 * sizeof(char));
    SWCharacter self;
    char* str = NULL;
    size_t index = 2;

    file_to_string(path, json);

    self.name = json_substring(json, "name", &index);
    
    str = json_substring(json, "height", &index);
    self.height = str_equals(str, "unknown") ? 0 : parse_int(str);
    free(str);
    
    str = json_substring(json, "mass", &index);
    str_remove_all(str, ',');
    self.mass = str_equals(str, "unknown") ? 0 : parse_double(str);
    free(str);
    
    self.hair_color = json_substring(json, "hair_color", &index);
    self.skin_color = json_substring(json, "skin_color", &index);
    self.eye_color = json_substring(json, "eye_color", &index);
    self.birth_year = json_substring(json, "birth_year", &index);
    self.gender = json_substring(json, "gender", &index);
    self.homeworld = json_substring(json, "homeworld", &index);

    free(json);

    return self;
}

// ==================================== SWCharacter Methods ================================== //

SWCharacter swc_clone(const SWCharacter* const self) {
    SWCharacter other;
    
    other.name = str_clone(self->name);
    other.height = self->height;
    other.mass = self->mass;
    other.hair_color = str_clone(self->hair_color);
    other.skin_color = str_clone(self->skin_color);
    other.eye_color = str_clone(self->eye_color);
    other.birth_year = str_clone(self->birth_year);
    other.gender = str_clone(self->gender);
    other.homeworld = str_clone(self->homeworld);

    return other;
}

void swc_print(const SWCharacter* const self) {
    printf(" ## ");
    printf("%s ## ", self->name);
    printf("%d ## ", self->height);
    
    if(self->mass == (int)self->mass) {
        printf("%d ## ", (int)self->mass);
    } else {
        printf("%.1lf ## ", self->mass);
    }

    printf("%s ## ", self->hair_color);
    printf("%s ## ", self->skin_color);
    printf("%s ## ", self->eye_color);
    printf("%s ## ", self->birth_year);
    printf("%s ## ", self->gender);
    printf("%s ## ", self->homeworld);
    printf("\n");
}

void swc_free(SWCharacter* const self, const size_t len) {
    if(self) {
        for(size_t i = 0; i < len; i++) {
            if(self[i].name) free(self[i].name);
            if(self[i].hair_color) free(self[i].hair_color);
            if(self[i].skin_color) free(self[i].skin_color);
            if(self[i].eye_color) free(self[i].eye_color);
            if(self[i].gender) free(self[i].gender);
            if(self[i].birth_year) free(self[i].birth_year);
            if(self[i].homeworld) free(self[i].homeworld);
        }
        free(self);
    }
}

// ==================================== LISTA ================================== //

#define MAXTAM    50
#define bool      short
#define true      1
#define false     0

SWCharacter lista[MAXTAM];   // Elementos da pilha 
int n;               // Quantidade de array.

/**
 * Inicializacoes
 */
void start(){
   n = 0;
}


/**
 * Insere um elemento na primeira posicao da lista e move os demais
 * elementos para o fim da 
 * @param x int elemento a ser inserido.
 */
void inserirInicio(SWCharacter x) {
    int i;

    //validar insercao
    if(n >= MAXTAM){
        printf("Erro ao inserir!");
        exit(1);
    } 

    //levar elementos para o fim do array
    for(i = n; i > 0; i--){
        lista[i] = lista[i-1];
    }

    lista[0] = x;
    n++;
}


/**
 * Insere um elemento na ultima posicao da 
 * @param x int elemento a ser inserido.
 */
void inserirFim(SWCharacter x) {

    //validar insercao
    if(n >= MAXTAM){
        printf("Erro ao inserir!");
        exit(1);
    }

    lista[n] = x;
    n++;
}


/**
 * Insere um elemento em uma posicao especifica e move os demais
 * elementos para o fim da 
 * @param x int elemento a ser inserido.
 * @param pos Posicao de insercao.
 */
void inserir(SWCharacter x, int pos) {
    int i;

    //validar insercao
    if(n >= MAXTAM || pos < 0 || pos > n){
        printf("Erro ao inserir!");
        exit(1);
    }

    //levar elementos para o fim do array
    for(i = n; i > pos; i--){
        lista[i] = lista[i-1];
    }

    lista[pos] = x;
    n++;
}


/**
 * Remove um elemento da primeira posicao da lista e movimenta 
 * os demais elementos para o inicio da mesma.
 * @return resp int elemento a ser removido.
 */
SWCharacter removerInicio() {
    SWCharacter resp;

    //validar remocao
    if (n == 0) {
        printf("Erro ao remover!");
        exit(1);
    }

    resp = lista[0];
    n--;

    for(int i = 0; i < n; i++){
        lista[i] = lista[i+1];
    }

    return resp;
}


/**
 * Remove um elemento da ultima posicao da 
 * @return resp int elemento a ser removido.
 */
SWCharacter removerFim() {

    //validar remocao
    if (n == 0) {
        printf("Erro ao remover!");
        exit(1);
    }

    return lista[--n];
}


/**
 * Remove um elemento de uma posicao especifica da lista e 
 * movimenta os demais elementos para o inicio da mesma.
 * @param pos Posicao de remocao.
 * @return resp int elemento a ser removido.
 */
SWCharacter remover(int pos){
   SWCharacter resp;

   //validar remocao
    if (n == 0 || pos < 0 || pos >= n) {
        printf("Erro ao remover!");
        exit(1);
    }

    resp = lista[pos];
    n--;

    for(int i = pos; i < n; i++){
        lista[i] = lista[i+1];
    }

    return resp;
}


/**
 * Mostra os array separados por espacos.
 */
void mostrar (){
    for (int i = 0; i < n; i++){
        printf("## %s ## %d ## %.1f ## %s ## %s ## %s ## %s ## %s ## %s ##\n",
        lista[i].name,
        lista[i].height,
        lista[i].mass,
        lista[i].hair_color,
        lista[i].skin_color,
        lista[i].eye_color,
        lista[i].birth_year,
        lista[i].gender,
        lista[i].homeworld);
    }
}

// =============================== ORDENACAO POR SHELL ======================

void insercaoPorCor(int n, int cor, int h){
    for (int i = (h + cor); i < n; i+=h) {
        SWCharacter tmp = lista[i];
        int j = i - h;
        while ((j >= 0) && (lista[j].mass > tmp.mass)){
            lista[j + h] = lista[j];
            j-=h;
        }
        while ((j >= 0) && (lista[j].mass == tmp.mass) && (strcmp(lista[j].name, tmp.name) > 0)){
            lista[j + h] = lista[j];
            j-=h;
        }
        lista[j + h] = tmp;
    }
}

void shellsort() {
    int h = 1;

    do { h = (h * 3) + 1; } while (h < n);

    do {
        h /= 3;
        for(int cor = 0; cor < h; cor++){
            insercaoPorCor( n, cor, h);
        }
    } while (h != 1);
}

// ==================================== Main ================================== //

int main() {
    SWCharacter* personagem = (SWCharacter*)malloc(N_JSONS * sizeof(SWCharacter));
    size_t len = 0;
    char* str = (char*)malloc(100 * sizeof(char));
    scanf(" %100[^\n]", str);
    while(!isFim(str)) {
        personagem[len++] = SWCharacter_from_file(str);
        scanf(" %100[^\n]", str);
    }

    for (int i = 0; i < (int)len; i++){
        inserirFim(personagem[i]);
    }
    
    shellsort();
    mostrar();

    swc_free(personagem, len);
    free(str);
    return 0;
}
