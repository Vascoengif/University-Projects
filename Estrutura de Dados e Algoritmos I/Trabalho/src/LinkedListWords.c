#include "LinkedListWords.h"

struct nodeWord{
    wchar_t word[40];
    char idType[40];
    NodeWord* next;
}; 

struct linkedlistWord{
    NodeWord* head;
};

NodeWord* new_nodeWord(wchar_t* value, char* id){       //this function creates a new node with a word and the respective id(type)
    NodeWord* node = malloc(sizeof(NodeWord));
    wcscpy(node->word, value);
    strcpy(node->idType, id);
    node->next = NULL;
    return node;
}

LinkedListWord* new_linkedListWord(){                    //this function creates a new empty linkedlist
    LinkedListWord* list = malloc(sizeof(LinkedListWord));
    list->head = NULL;
    return list;
}

int list_insertWord(LinkedListWord* list, wchar_t* value, char* id){      //this function is responsable to insert a word in a specific list
    NodeWord* node = list->head;
    
    if(node == NULL){
        list->head = new_nodeWord(value, id);
        return 1;
    }       
    else{
        while(node->next != NULL){
            node = node->next;
        }
        node->next = new_nodeWord(value, id);
        return 1;
    }
    return 0;
}

int checkIfIdExists(LinkedListWord* list, char* id){       //this function checks if that list received as an argument saves words of
    NodeWord* node = list->head;                           //that specific id(type)
    if(strcmp(node->idType, id) == 0){
        return 1;
    }
    return 0;
}

wchar_t* find_valueWord(LinkedListWord* list, int position){     //this function find and returns the word from a specific list, of 
    NodeWord* node = list->head;                                 //a specific position in the list

    if(position < list_lengthWord(list)){
        if(position == 0){
            return node->word;
        }
        else{
            while(position != 0){
                node = node->next;
                position--;
            }
            return node->word;
        }
    }
    return L"1";  //means that there are no more words to suggest, it's the end of that list
}

bool is_emptyWord(LinkedListWord* list){           //this function checks if that list is empty or not
    NodeWord* node = list->head;
    if(node == NULL){
        return true;
    }
    return false;
}

int list_lengthWord(LinkedListWord* list){         //this function returns the size of that list
    int size = 0;
    NodeWord* node = list->head;
    if(node == NULL){
        return size;
    }
    else{
        while(node->next != NULL){
            size++;
            node = node->next;
        }
        return size+1;
    }
}