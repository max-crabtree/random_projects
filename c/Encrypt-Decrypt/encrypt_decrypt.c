/*
- Input a string and hit ENTER
- Input a letter (e, d, or c?)
- e for encrypt, d for decrypt, c for close
- If d hit and not encrypted, err... If e hit and encrypted, err...
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void flush() { //stackoverflow copy
    int c;
    while ((c = getchar()) != '\n' && c != EOF);
}

char decrypt(char *encrypted_text, int len, const int SHIFT, const int PASSES) {
    char current;
    char decrypted[len];

    for (int i = 0; i < PASSES; i++) {
        for (int j = 0; j < len; j++) {
            current = encrypted_text[j];
            if (current >= 'A' && current <= 'Z' || current >= 'a' && current <= 'z') {
                current = current - SHIFT;
                if (current > 'z' || (current > 'Z' && current < 'a')) {
                    current = current + 26;
                }
            }
            printf("%c", current);
            decrypted[j] = current;
            encrypted_text[j] = decrypted[j];
        }
    }
}

char encrypt(char *text) {
    const int PASSES = 26; //loops back @ 26 passes
    const int SHIFT = 3;
    char current;
    char decry;
    int len = strlen(text);
    char encrypted[len];

    for (int i = 0; i < PASSES; i++) {
        for (int j = 0; j < len; j++) {
            current = text[j];
            if (current >= 'A' && current <= 'Z' || current >= 'a' && current <= 'z') {
                current = current + SHIFT;
                if (current > 'z' || (current > 'Z' && current < 'a')) {
                    current = current - 26;
                }
            }
            printf("%c", current);
            encrypted[j] = current;
            text[j] = encrypted[j];
        }
    }
    printf("Decrypt?: ");
    scanf(" %c", &decry);
    switch (decry) {
        case 'y':
            decrypt(encrypted, len, SHIFT, PASSES);
            break;
    }
}

int main() {
    const int MAX_LENGTH = 30;
    char *message = malloc(MAX_LENGTH * sizeof(char));
    char command;

    printf("Simple Encrypter/Decrypter\n");

    while (1) {
        flush();
        printf("\nType a message: ");
        fgets(message, MAX_LENGTH, stdin);
        printf("> ");
        scanf(" %c", &command);
        switch (command) {
            case 'e':
                encrypt(message);
                break;
            case 'c':
                free(message);
                exit(EXIT_SUCCESS);
        }
    }
}
