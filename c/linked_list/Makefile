# Compiler and flags
CC = gcc
CFLAGS = -Wall -Wextra -Wpedantic -g

# Project files
TARGET = linked_list         # Name of the output binary
SRCS = main.c linked_list.c
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
