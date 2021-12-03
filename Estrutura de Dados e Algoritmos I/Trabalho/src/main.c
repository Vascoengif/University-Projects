#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#include <wchar.h>
#include <locale.h>
#include "hashtable.c"
#include "LinkedListWords.c"
#define BILLION 1000000000L;


void createTypeOfWord(char* typeOfWord, wchar_t* word){  //this function create the type of word (Ex: type of "água" is 2482) 

  for(int i = 0; i < wcslen(word); i++){
    
    if(word[i] == L'a' || word[i] == L'b' || word[i] == L'c' || word[i] == L'á' || word[i] == L'à' || word[i] == L'â' || word[i] == L'ã' || word[i] == L'ç' || word[i] == L'A' || word[i] == L'B' || word[i] == L'C' || word[i] == L'Á' || word[i] == L'À' || word[i] == L'Â' || word[i] == L'Ã' || word[i] == L'Ç'){
      strcat(typeOfWord, "2");
    }
    else if(word[i] == L'd' || word[i] == L'e' || word[i] == L'f' || word[i] == L'é' || word[i] == L'ê' || word[i] == L'D' || word[i] == L'E' || word[i] == L'F' || word[i] == L'É' || word[i] == L'Ê'){
      strcat(typeOfWord, "3");
    }
    else if(word[i] == L'g' || word[i] == L'h' || word[i] == L'i' || word[i] == L'í' || word[i] == L'G' || word[i] == L'H' || word[i] == L'I' || word[i] == L'Í'){
      strcat(typeOfWord, "4");
    }
    else if(word[i] == L'j' || word[i] == L'k' || word[i] == L'l' || word[i] == L'J' || word[i] == L'K' || word[i] == L'L'){
      strcat(typeOfWord, "5");
    }
    else if(word[i] == L'm' || word[i] == L'n' || word[i] == L'o' || word[i] == L'ó' || word[i] == L'ô' || word[i] == L'õ' || word[i] == L'M' || word[i] == L'N' || word[i] == L'O' || word[i] == L'Ó' || word[i] == L'Ô' || word[i] == L'Õ'){
      strcat(typeOfWord, "6");
    }
    else if(word[i] == L'p' || word[i] == L'q' || word[i] == L'r' || word[i] == L's' || word[i] == L'P' || word[i] == L'Q' || word[i] == L'R' || word[i] == L'S'){
      strcat(typeOfWord, "7");
    }
    else if(word[i] == L't' || word[i] == L'u' || word[i] == L'v' || word[i] == L'ú' || word[i] == L'T' || word[i] == L'U' || word[i] == L'V' || word[i] == L'Ú'){
      strcat(typeOfWord, "8");
    }
    else if(word[i] == L'w' || word[i] == L'x' || word[i] == L'y' || word[i] == L'z' || word[i] == L'W' || word[i] == L'X' || word[i] == L'Y' || word[i] == L'Z'){
      strcat(typeOfWord, "9");
    }
    else if(word[i] == L'-' || word[i] == L'\''){   //if a word contains "-" or "'", the code ignores it
      continue;
    }
  }
}


void processData(HashTable* hashtable, char* argv){  //in this function all the dictionary will be read and all the words will be
  struct timespec start, stop;                       //alocated in the wrigth spot in the hashtable
  double total;

  clock_gettime(CLOCK_REALTIME, &start);             //iniciate the clock to present to the user how many seconds does the program take
                                                     //to process the data and save it in the hashtable
  FILE* fp;
  char* filename = argv;   //file specified in the input

  fp = fopen(filename, "r");     //read file only

  if (fp == NULL){
    printf("Could not open file %s\n",filename);     //if file doesn't exist, this message is presented to the user and the 
    exit(1);                                         //application ends.
  }

  wchar_t message[40] = L"";                        
  setlocale(LC_ALL, "");

  while(fwscanf(fp, L"%ls\n", message) != EOF)  //reads the dictionary line by line and saves the word in "message"
  {
    char type[40] = "";                         //type of the word in "message"
    createTypeOfWord(type, message);            //create the type of the word
    Insert(hashtable, message, type);           //insert that word in the specific spot in the hashtable
  }

  clock_gettime(CLOCK_REALTIME, &stop);         //stop the clock

  total = (stop.tv_sec - start.tv_sec) + (stop.tv_nsec - start.tv_nsec) / (double)BILLION;

  wprintf(L"Time taken to process the data: %f seconds\n\n", total);    //returning the time that was spent during the process of the data
}



int main(int argc, char* argv[])       //main function, which contains the cicle of interactions with the user
{

  HashTable* hashtable = InitializeTable();     //creating the hashtable

  processData(hashtable, argv[1]);              //processing the data
  
  wprintf(L"Type a string of numbers that matches the type of word you want:\n");
  wprintf(L"If the word that you want contains an hyphen(-) or an apostrophe ('), ignore that character.\n" );

  wprintf(L"\n2: a b c á à â ã ç");                //table so the user can choose the numbers to create a type of word
  wprintf(L"\n3: d e f é ê");
  wprintf(L"\n4: g h i í");
  wprintf(L"\n5: j k l");
  wprintf(L"\n6: m n o ó ô õ");
  wprintf(L"\n7: p q r s");
  wprintf(L"\n8: t u v ú");
  wprintf(L"\n9: w x y z");

  wprintf(L"\n\n");

  char typeOfword[40];
  wchar_t final_message[500] = L"";          //the final message will be saved here
  bool getOut = false;

  wprintf(L"** Write your message **\n");

  while(getOut == false)                     //while the user don't want to shut down...
  {
    scanf("%s", typeOfword);                 //typeOfword saves the input given by the user

    if(!strcmp(typeOfword, "1"))             //if the user type 1, the program shows him the updated message
    {
      wprintf(L"Message: %ls \n", final_message);
    }
    else if(!strcmp(typeOfword, "0"))        //if the user type 0, the program will ask if he wants to exit or not
    {
      char decision[] = " ";
         
      wprintf(L"Do you want to exit (s/n)? \n");
      scanf("%s", decision);

      while(strcmp(decision, "n") && strcmp(decision, "N") && strcmp(decision, "s") && strcmp(decision, "S"))  //until the user doesn't type
      {                                                                                                        //a valid input...
        wprintf(L"Incorrect code. Please type again. Do you want to exit (s/n)?\n");
        scanf("%s", decision);
      }

      if(!strcmp(decision, "s") || !strcmp(decision, "S"))     //if the answer is S or s(yes), the program will shut down
      {
        getOut = true;        
      }
    }
    else      //if the user doesn't type 1 or 0...it's because he worte the type of word he wants
    {
      int position = 0;             //position in the list (the list of words of that type) where the program will catch the word
      char acceptedScan[] = " ";    //this position will be incremented as long as the user refuse the words
      bool accepted = false;        //variable to check if the user accepts the suggested word or not

      wchar_t wordSuggested[40] = L"";      //this will save the suggested word

      while(wordSuggested[0] != L'0' && wordSuggested[0] != L'1' && accepted == false){
                                                                                          //L'0' means that there are no words with 
                                                                                          //the type given by the user.
                                                                                          //L'1' means that the user spent all the 
                                                                                          //suggestions
        wcscpy(wordSuggested, Find(hashtable, typeOfword, position));                     //function Find() finds a word corresponding
                                                                                          //to the type given by the user and the word is saved in "wordSuggested"
        if(wordSuggested[0] == L'0'){
          wprintf(L"There are no words with that type. Please type again.\n");
        }
        else if(wordSuggested[0] == L'1'){
          wprintf(L"There are no more suggestions; enter the word from the keyboard.\n");
          char newtypeOfWord[40] = "";
          wchar_t newWord[40];                                              //the user types a new word if there are no more suggestions
          scanf("%ls", newWord);
          
          createTypeOfWord(newtypeOfWord, newWord);                         //his type will be created
          Insert(hashtable, newWord, newtypeOfWord);                        //the new word will be saved in the hashtable

          wcscat(final_message, newWord);                                   //and will be added to the message
          wcscat(final_message, L" ");
        }
        else{
          wprintf(L"Suggestion: %ls, accept (s/n)?", wordSuggested);        //the word that was found will be presented to the user
                                                                            //the user needs to type if he accepts the word or not
          scanf("%s", acceptedScan);

          while(strcmp(acceptedScan, "s") && strcmp(acceptedScan, "S") && strcmp(acceptedScan, "n") && strcmp(acceptedScan, "N")){
            wprintf(L"Incorrect code. Please type again.\n");                      //while the user input an unvalid code...
            scanf("%s", acceptedScan);
          }

          if(!strcmp(acceptedScan, "s") || !strcmp(acceptedScan, "S")){
            wprintf(L"The word was successfully added to the message. Specify the next one.\n");
            wcscat(final_message, wordSuggested);                       //if the user accepts the word, this one will be added to
            wcscat(final_message, L" ");                                //the message and the user can immediately specify the new type
            accepted = true;                                            //of word, or exit or show the message...
          }
          else if(!strcmp(acceptedScan, "n") || !strcmp(acceptedScan, "N")){
            position++;                                                 //if the user doens't accept the suggested word, this "position"
            accepted = false;                                           //will be incremented so the program can go pick the next word
          }                                                             //of that type
        }
      }
    }
  }
  return 0;
}