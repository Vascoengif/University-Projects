#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct nodeWord NodeWord;
typedef struct linkedlistWord LinkedListWord;

NodeWord* new_nodeWord(wchar_t* value, char* id);
LinkedListWord* new_linkedListWord();
int list_insertWord(LinkedListWord* list, wchar_t* value, char* id);
int checkIfIdExists(LinkedListWord* list, char* id);
wchar_t* find_valueWord(LinkedListWord* list, int position);
bool is_emptyWord(LinkedListWord* list);
int list_lengthWord(LinkedListWord* list);