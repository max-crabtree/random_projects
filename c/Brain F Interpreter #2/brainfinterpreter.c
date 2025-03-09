#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdint.h>
#include <unistd.h>

#define AMOUNT_OF_CELLS 64
#define MAX_INPUT_LENGTH 100
#define TEMP_FILE_NAME "temp.bf"

struct Cell {
    int cell_number;
    int8_t value;
};


_Bool check_file(FILE *file) {
    int valid_symbols[8] = {'>', '<', '+', '-', '.', ',', '[', ']'};
    int c;

    while ((c = fgetc(file)) != EOF) {
        for (int j = 0; j <= sizeof valid_symbols; j++) {
            if (c != valid_symbols[j]) {
                printf("Error parsing document!\n");
                return 0;
            }
        }
    }
    printf("File has been validated!");
    return 1;
}


void save_script(FILE *temp_file, _Bool continuing, char edit_file_name[MAX_INPUT_LENGTH]) {
    FILE *script;
    char script_name[MAX_INPUT_LENGTH];
    int c;
    int overwrite_choice;

    temp_file = fopen(TEMP_FILE_NAME, "r");
    if (continuing == 0) {
        while (1) {
            printf("Type the file name for this program\n>> ");
            scanf("%s", script_name);
            strcat(script_name, ".bf");
            
            if (access(script_name, F_OK) == 0) {
                printf("Are you sure you want to overwrite %s?\n", script_name);
                printf("0 for no, 1 for yes\n");
                scanf("%d", &overwrite_choice);
                if (overwrite_choice == 0) {
                    continue;
                }
                else if (overwrite_choice == 1) {
                    break;
                }
                else {
                    printf("Invalid input!\n");
                    continue;
                }
            }
            break;
        }
    }

    if (continuing == 1) {
        for (int i = 0; i < strlen(edit_file_name); i++) {
            script_name[i] = edit_file_name[i];
        }
    }

    script = fopen(script_name, "w");

    while ((c = fgetc(temp_file)) != EOF) {
        fputc(c, script);
    }

    fclose(temp_file);
    fclose(script);
    remove(TEMP_FILE_NAME);
    fflush(NULL);
    return;
}


void write_script(_Bool continuing, FILE *editing_file, char edit_file_name[MAX_INPUT_LENGTH]) {
    char input[MAX_INPUT_LENGTH];
    FILE *temp_file;
    int c;

    if (continuing == 0) {
        printf("Start typing your commands below. Hit ENTER to start a new line.\n");
        printf("Type 's' to save to a file.\n");
    }

    temp_file = fopen(TEMP_FILE_NAME, "a");
    if (temp_file == NULL) {
        printf("Failed to open %s!", TEMP_FILE_NAME);
    }

    if (continuing == 1) {
        while((c = fgetc(editing_file)) != EOF) {
            fputc(c, temp_file);
            printf(" %c", c);
        }
    }

    while (1) {
        fgets(input, MAX_INPUT_LENGTH, stdin);

        //better way?
        if (input[0] == 's' && input[1] == '\n' && input[2] == '\0') {
            break;
        }

        fprintf(temp_file, "%s", input);
        fflush(NULL);
    }

    save_script(temp_file, continuing, edit_file_name);
    
}


void run_script() {
    char file_name[MAX_INPUT_LENGTH];
    FILE *file;

    printf("Please select a file for running\n");
    printf("Type the file name below (without extension)\n");
    while (1) {
        scanf("%s", file_name);
        strcat(file_name, ".bf");

        file = fopen(file_name, "r+");
        if (file == NULL) {
            printf("Error opening file '%s'!", file_name);
            fclose(file);
            continue;
        }
        break;
    }

    printf("Successfully opened '%s'\n", file_name);
    printf("Checking file...\n");
    check_file(file);
        //for (size_t i = 0; i < input_length; i++) {
        //    switch (input[i]) {
        //        case '>':
        //            printf("Increment\n");
        //            break;
        //        case '<':
        //            printf("Decrement\n");
        //            break;
        //        case '+':
        //            break;
        //        case '-':
        //            break;
        //        case '.':
        //            break;
        //        case ',':
        //            break;
        //        case '[':
        //            break;
        //        case ']':
        //            break;
        //        default:
        //            break;
        //    } 
        //}

}

void open_script() {
    FILE *file;
    char file_name[MAX_INPUT_LENGTH];
    int c;

    printf("Type the file name for opening (without extension)\n");
    while (1) {
        scanf("%s", file_name);
        strcat(file_name, ".bf");

        file = fopen(file_name, "w+");
        if (access(file_name, F_OK) == 1) {
            printf("File %s does not exist!\n", file_name);
            continue;
        }
        break;
    }

    printf("Successfully opened %s\n", file_name);

    write_script(1, file, file_name);

}


int main() {
    int mode;

    printf("BrainF Interpreter\n");
    printf("Would you like to write and save a script (1)\nRun a pre-written script (2)\nOr open a file for writing (3)?\n>> ");
    while (1) {
        scanf("%d", &mode);
        if (mode == 1) {
            write_script(0, NULL, NULL);
            break;
        }
        else if (mode == 2) {
            run_script();
            break;
        }
        else if (mode == 3) {
            open_script();
            break;
        }
        else {
            printf("Error! Try again!");
        }
    }
    return 0;
}
