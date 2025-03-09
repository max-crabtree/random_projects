FILE_NAME = rps

output: ${FILE_NAME}.o ${FILE_NAME}.cpp
	g++ -pedantic -Wall -Wextra -Wwrite-strings -g3 ${FILE_NAME}.o -o output

${FILE_NAME}.o: ${FILE_NAME}.cpp
	g++ -c ${FILE_NAME}.cpp

run: output
	./output

clean:
	rm -f *.o output
