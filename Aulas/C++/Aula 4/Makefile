main: main.o departamento.o empresa.o
	g++ -std=c++17 -o main main.o departamento.o empresa.o

main.o: main.cpp departamento.h empresa.h
	g++ -std=c++17 -c -o main.o main.cpp

departamento.o: departamento.cpp departamento.h
	g++ -std=c++17 -c -o departamento.o departamento.cpp

empresa.o: empresa.cpp empresa.h departamento.h
	g++ -std=c++17 -c -o empresa.o empresa.cpp

clean:
	rm *.o
	rm main
