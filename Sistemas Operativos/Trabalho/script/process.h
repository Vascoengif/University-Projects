typedef struct process Process;

Process* new_process(int size);
Process* organizar_processos(int array[], int inicio, int fim);
int find_PID(int PID, Process* array_processos[], int k);
