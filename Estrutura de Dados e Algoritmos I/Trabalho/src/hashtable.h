typedef struct HashTbl HashTable;

HashTable* InitializeTable();
int HashCode(char* id);
wchar_t* Find(HashTable* H, char* id, int position);
void Insert(HashTable* H, wchar_t* word, char* id);
