typedef struct queue Queue;

Queue* create_queue(int size);
void enqueue(Queue* fila, int value);
bool empty(Queue* fila);
int dequeue(Queue* fila);
void free_queue(Queue* fila);
int top(Queue* fila);
void print_queue(Queue* fila);
