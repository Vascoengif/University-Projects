#define SIZE_USER_PASS 10

typedef struct cliente cliente_t;
cliente_t *new_cliente();
void registar(char userName[SIZE_USER_PASS], char password[SIZE_USER_PASS]);
cliente_t *find_cliente(cliente_t *cliente_v[], char userName[SIZE_USER_PASS]);
void erase_cliente(cliente_t *cliente_v[], char userName[SIZE_USER_PASS]);
void oper(cliente_t *cliente_v);
bool UserName_online(char userName[SIZE_USER_PASS]);
bool UserName_registado(char userName[SIZE_USER_PASS]);
bool UserName_operador(char userName[SIZE_USER_PASS]);
bool verifica_login(char userName[SIZE_USER_PASS], char password[SIZE_USER_PASS]);
void erase_OPER_User(char userName[SIZE_USER_PASS]);
void erase_User(cliente_t *cliente_v[], char userName[SIZE_USER_PASS]);
void erase_Online(cliente_t *cliente_v[], char userName[SIZE_USER_PASS]);