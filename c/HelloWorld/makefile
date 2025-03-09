FILE_NAME = hello_world

output: ${FILE_NAME}.o ${FILE_NAME}.c
	gcc ${FILE_NAME}.o -o output

${FILE_NAME}.o: ${FILE_NAME}.c
	gcc -c ${FILE_NAME}.c

run: output
	./output

clean:
	rm -f *.o output
