#include <stdio.h>
#include <stdlib.h>
//use fgets!
void flush() { //stackoverflow copy
    int c;
    while ((c = getchar()) != '\n' && c != EOF);
}

char addTask(int index) {
    char description[50];
    char title[20];
    _Bool completed = 0; //false
    printf("Adding new task\n");
    printf("Write a title: ");
    flush();
    fgets(title, sizeof(title), stdin);
    printf("Write a description: ");
    fgets(description, sizeof(description), stdin);
    printf("Your task is:\n[%d] %s %s Done?: %d\n", index, title, description, completed);
}

int main() {
    char command[2]; //2 due to fgets?
    int amount = 1;
    _Bool active = 1; //true

    printf("To-Do List\n");

    while (active) {
        fgets(command, sizeof(command), stdin);
        switch (command[0]) {
            case 'a':
                addTask(amount);
                amount = amount + 1;
                break;
        }
    }
}

        //scanf(" %c", &command);

        //switch (command) {
        //case 'a':
        //    addTask(description, completed);
        //    break;
        //case 'r':
        //    removeTask();
        //    // removed task [return num]
        //    break;
        //case 'e':
        //    editTask();
        //    break;
        //case 'v':
        //    viewList();
        //    break;
        //}