#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
    srand(time(NULL));

    int guess;
    int correct = rand() % 101;

    printf("Guessing Game\n");
    while (guess != correct) {
        printf("G: ");
        scanf("%d", &guess);
        printf("Guess is: %d\n", guess);
        if (guess > correct) {
            printf("Too high!\n");
        }
        if (guess < correct) {
            printf("Too low!\n");
        }
    }
    printf("You got it right!\n");
}