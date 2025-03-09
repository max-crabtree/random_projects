FILE_NAME = encrypt_decrypt

output: ${FILE_NAME}.o ${FILE_NAME}.c
	gcc -pedantic -Wall -Wextra -Wwrite-strings -g3 ${FILE_NAME}.o -o output

${FILE_NAME}.o: ${FILE_NAME}.c
	gcc -c ${FILE_NAME}.c

run: output
	./output

clean:
	rm -f *.o output
