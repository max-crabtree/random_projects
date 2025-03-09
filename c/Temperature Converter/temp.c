#include <stdio.h>

/* Convert Fahrenheit to Celcius */
float toCelcius(float temp) {
    float C = (temp - 32) * 0.555555;
    return C;
}

/* Convert Celcius to Fahrenheit */
float toFahrenheit(float temp) {
    float F = (temp * 1.8) + 32;
    return F;
}

int main() {
    /* Inputted Temperature */
    float temp;

    /* Inputted Temperature Conversion Type */
    char convert;

    printf("Temperate Converter\n");
    printf("Temp: ");
    scanf("%f%c", &temp, &convert);
    if (convert == 'C') {
        printf("Result is : %.2fF\n", toFahrenheit(temp));
    }
    else if (convert == 'F') {
        printf("Result is : %.2fC\n", toCelcius(temp));
    }
    else {
        printf("Invalid conversion option!\n");
    }
    return 0;
}