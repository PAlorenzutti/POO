compila:
	javac -d bin src/*.java

jar:
	jar cfm vereadores.jar manifest.txt -C bin .

run-ac-1392:
	java -jar vereadores.jar 1392 entradas/consulta_cand_2024/consulta_cand_2024_AC.csv entradas/votacao_secao_2024/votacao_secao_2024_AC.csv 06/10/2024 > saida.txt 2>&1

diff-ac-1392:
	meld saida.txt saidas/AC1392.txt

run-ac-1473:
	java -jar vereadores.jar 1473 entradas/consulta_cand_2024/consulta_cand_2024_AC.csv entradas/votacao_secao_2024/votacao_secao_2024_AC.csv 06/10/2024 > saida.txt 2>&1

diff-ac-1473:
	meld saida.txt saidas/AC1473.txt

run-es-57053:
	java -jar vereadores.jar 57053 entradas/consulta_cand_2024/consulta_cand_2024_ES.csv entradas/votacao_secao_2024/votacao_secao_2024_ES.csv 06/10/2024 > saida.txt 2>&1

diff-es-57053:
	meld saida.txt saidas/ES57053.txt

run-es-56472:
	java -jar vereadores.jar 56472 entradas/consulta_cand_2024/consulta_cand_2024_ES.csv entradas/votacao_secao_2024/votacao_secao_2024_ES.csv 06/10/2024 > saida.txt 2>&1

diff-es-56472:
	meld saida.txt saidas/ES56472.txt

run-pe-25810:
	java -jar vereadores.jar 25810 entradas/consulta_cand_2024/consulta_cand_2024_PE.csv entradas/votacao_secao_2024/votacao_secao_2024_PE.csv 06/10/2024 > saida.txt 2>&1

diff-pe-25810:
	meld saida.txt saidas/PE25810.txt

run-pe-26298:
	java -jar vereadores.jar 26298 entradas/consulta_cand_2024/consulta_cand_2024_PE.csv entradas/votacao_secao_2024/votacao_secao_2024_PE.csv 06/10/2024 > saida.txt 2>&1

diff-pe-26298:
	meld saida.txt saidas/PE26298.txt

run-pe-26310:
	java -jar vereadores.jar 26310 entradas/consulta_cand_2024/consulta_cand_2024_PE.csv entradas/votacao_secao_2024/votacao_secao_2024_PE.csv 06/10/2024 > saida.txt 2>&1

diff-pe-26310:
	meld saida.txt saidas/PE26310.txt

zip:
	zip -r vereadores-JoaoVitorSoaresDosSantos-PedroAlexandreSimoesLorenzutti.zip src lib bin build.xml relatorio.pdf Makefile

teste:
	unzip vereadores-JoaoVitorSoaresDosSantos-PedroAlexandreSimoesLorenzutti.zip
	ant compile
	ant jar
	java -jar vereadores.jar 1392 entradas/consulta_cand_2024/consulta_cand_2024_AC.csv entradas/votacao_secao_2024/votacao_secao_2024_AC.csv 06/10/2024

clean:
	rm -rf bin vereadores.jar saida.txt
	clear