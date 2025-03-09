#include <stdio.h>
#include <SDL2/SDL.h>
#include <GL/gl.h>

#define WINDOW_WIDTH 720
#define WINDOW_HEIGHT 480

int main() {
    printf("Hello, World!");

    SDL_InitSubSystem(SDL_INIT_VIDEO);
    SDL_Window* window = SDL_CreateWindow("Spirograph Generator", SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED, WINDOW_WIDTH, WINDOW_HEIGHT, SDL_WINDOW_OPENGL);

    if (!window) { printf("Could not initialise window: %s\n", SDL_GetError()); return; }

    while (1) {
        SDL_Event e;
        if (SDL_WaitEvent(&e)) {
            if (e.type == SDL_QUIT) {
                break;
            }
        }
    }

    SDL_DestroyWindow(window);
    SDL_Quit();
}