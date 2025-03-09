# Compiler and flags
CC = gcc
CFLAGS = -Wall -Wextra -Wpedantic -g

# Project files
TARGET = neural_net           # Name of the output binary
SRCS = main.c matrix.c neuron.c activation_functions.c csv.c nn_math.c neural_network.c layer.c weights.c
OBJS = $(SRCS:.c=.o)          # Object files (derived from source files)

# Default target
all: $(TARGET)

# Link the object files to create the final executable
$(TARGET): $(OBJS)
	$(CC) $(CFLAGS) -o $@ $^

# Compile source files into object files
%.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

# Clean up build artifacts
clean:
	rm -f $(OBJS) $(TARGET)

# Phony targets
.PHONY: all clean
