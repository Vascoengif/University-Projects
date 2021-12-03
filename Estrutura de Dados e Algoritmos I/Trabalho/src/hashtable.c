#include "LinkedListWords.h"
#include "hashtable.h"
#include <stdlib.h>
#include <limits.h>
#define SIZEHASH 10000000        //size of the hashtable

struct HashTbl{
    unsigned int tableSize;
    LinkedListWord* bigArray[SIZEHASH];
};

HashTable* InitializeTable(){              //this function creates and returns the hashtable will empty lists in each position
    HashTable* H = malloc(sizeof(HashTable));

    H->tableSize = SIZEHASH;

    if(H == NULL){
        wprintf(L"Out of space!");
        exit(1);
    }else{
        for(int i = 0; i < SIZEHASH; i++)
		{
			H->bigArray[i] = new_linkedListWord();
		}
    }
	return H;
}

int HashCode(char* id){                  //this function generates the index where the words with a specific id will be storage
    unsigned int hashcode = 5381;
	int c;

	while ((c = *id++))
	{
		hashcode = ((hashcode << 5) + hashcode) + c;
	}
	return hashcode % SIZEHASH;
}

wchar_t* Find(HashTable* H, char* id, int position){        //this function find and returns the word from a specific position int the
    int key = HashCode(id);                                 //list of words of that type (id).
    wchar_t source[40];
    wchar_t* wordToReturn = L"0";  

    while(is_emptyWord(H->bigArray[key]) == false){
        if(checkIfIdExists(H->bigArray[key], id) == 1){                                //check if there is that key to access to those
            wordToReturn = wcscpy(source, find_valueWord(H->bigArray[key], position)); //words with that specific type
            return wordToReturn;     //returns the word
        }
        key++;                                                                         //if it's no that key, go check the next one, 
    }                                                                                  //until found the correct one
    return wordToReturn; //returns L"0", which means that there is no word with that type
}

void Insert(HashTable* H, wchar_t* word, char* id){         //this function insert a word in the correct list in the hashtable
    int key = HashCode(id);

    while(is_emptyWord(H->bigArray[key]) == false){
        if(checkIfIdExists(H->bigArray[key], id) == 1){
            list_insertWord(H->bigArray[key], word, id);
            return;
        }
        key++;
    }
    list_insertWord(H->bigArray[key], word, id);
}

