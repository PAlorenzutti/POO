CXX = g++
CXXFLAGS = -std=c++17 -Wall -Wextra -pedantic

all: main

main: main.o aluno.o turma.o
	$(CXX) $(CXXFLAGS) -o main main.o aluno.o turma.o

main.o: main.cpp aluno.hpp turma.hpp
	$(CXX) $(CXXFLAGS) -c main.cpp

aluno.o: aluno.cpp aluno.hpp
	$(CXX) $(CXXFLAGS) -c aluno.cpp

turma.o: turma.cpp turma.hpp aluno.hpp
	$(CXX) $(CXXFLAGS) -c turma.cpp

clean:
	rm -f *.o main