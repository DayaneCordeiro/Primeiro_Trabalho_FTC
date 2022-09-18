<h1 align="center">Conversor AFN para AFD 💻</h1>

<p align="center">
  <img alt="Github top language" src="https://img.shields.io/github/languages/top/DayaneCordeiro/primeiro_trabalho_ftc?color=56BEB8">
  <img alt="Github language count" src="https://img.shields.io/github/languages/count/DayaneCordeiro/primeiro_trabalho_ftc?color=56BEB8">
  <img alt="Repository size" src="https://img.shields.io/github/repo-size/DayaneCordeiro/primeiro_trabalho_ftc?color=56BEB8">
  <img alt="License" src="https://img.shields.io/github/license/DayaneCordeiro/primeiro_trabalho_ftc?color=56BEB8">
</p>

<p align="center">
  <a href="#dart-sobre">Sobre</a> &#xa0; | &#xa0; 
  <a href="#warning-importante">Importante</a> &#xa0; | &#xa0; 
  <a href="#sparkles-funcionamento">Funcionamento</a> &#xa0; | &#xa0;
  <a href="#rocket-tecnologias">Tecnologias</a> &#xa0; | &#xa0;
  <a href="#white_check_mark-requisitos">Requisitos</a> &#xa0; | &#xa0;
  <a href="#memo-licença">Licença</a> &#xa0; | &#xa0;
  <a href="#memo-autora">Autora</a> &#xa0; 
</p>

<br>

## :dart: Sobre ##

<div align="justify">
Desenvolvimento do primeiro trabalho acadêmico da disciplina Fundamentos Teóricos da Computação. Lecionada no curso de Engenharia de computação na Pontifícia Universidade Católica de Minas Gerais.
<br><br>
O objetivo deste trabalho é realizar a conversão de Automatos Finitos Não Determinísticos (AFN) em Automatos Finitos Determinísticos (AFD) equivalentes.
</div>

## :warning: Importante ##

<div align="justify">
<li> O software não consegue manipular AFN-λ ou outros tipos de automatos que não sejam nem AFD e nem AFN.</li>
<li> O alfabeto de entrada do automato deve ser apenas {0, 1}. Não sendo identificados outros caracteres.</li>
</div>

## :sparkles: Funcionamento ##

<div align="justify">
A parte inicial do trabalho, deu-se na leitura de um arquivo de entrada com a extenção `.jff` gerado pelo software JFLAP versão 7.0 encontrado no endereço: https://www.jflap.org/jflaptmp/.
<br><br>
O arquivo foi manipulado a fim de coletar as informações dos estados e transições do automato descrito em formato XML. Após a coleta destes dados, foi feita uma verificação para confirmar que a entrada do usuário é uma AFN. Caso seja um AFD, o arquivo de entrada é apenas copiado para o arquivo de saída que foi inserido no diretório `output/AFD.jff`. Neste caso, nenhuma lógica de conversão é utilizada.
<br><br>
Caso o arquivo de entrada seja realmente uma AFN, é iniciado o processo de conversão. Finalizado esse processo, a descrição XML do automato convertido equivalente irá se encontrar no arquivo AFD.jff dentro do diretório <b>output</b> na raiz do projeto.
<br><br>
A seguir, foi incluído um exemplo do funcionamento do conversor, utilizando-se do JFLAP para ferramenta de conferência. Do lado esquerdo da primeira imagem, verifica-se o AFN e do lado direito o AFD que o próprio JFLAP converteu.


![Primeiro automato testado](https://github.com/DayaneCordeiro/Primeiro_Trabalho_FTC/blob/main/img/AFN.png)


O código XML gerado pelo JFLAP para este automato foi o seguinte:
</div>

```
<?xml version="1.0" encoding="UTF-8" standalone="no"?><!--Created with JFLAP 6.4.--><structure>&#13;
	<type>fa</type>&#13;
	<automaton>&#13;
		<!--The list of states.-->&#13;
		<state id="0" name="q0">&#13;
			<x>30.0</x>&#13;
			<y>98.0</y>&#13;
			<initial/>&#13;
		</state>&#13;
		<state id="1" name="q1">&#13;
			<x>134.0</x>&#13;
			<y>101.0</y>&#13;
		</state>&#13;
		<state id="2" name="q2">&#13;
			<x>244.0</x>&#13;
			<y>102.0</y>&#13;
		</state>&#13;
		<state id="3" name="q3">&#13;
			<x>319.0</x>&#13;
			<y>101.0</y>&#13;
			<final/>&#13;
		</state>&#13;
		<state id="4" name="q4">&#13;
			<x>231.0</x>&#13;
			<y>231.0</y>&#13;
		</state>&#13;
		<!--The list of transitions.-->&#13;
		<transition>&#13;
			<from>0</from>&#13;
			<to>0</to>&#13;
			<read>0</read>&#13;
		</transition>&#13;
		<transition>&#13;
			<from>2</from>&#13;
			<to>2</to>&#13;
			<read>0</read>&#13;
		</transition>&#13;
		<transition>&#13;
			<from>0</from>&#13;
			<to>0</to>&#13;
			<read>1</read>&#13;
		</transition>&#13;
		<transition>&#13;
			<from>4</from>&#13;
			<to>4</to>&#13;
			<read>1</read>&#13;
		</transition>&#13;
		<transition>&#13;
			<from>2</from>&#13;
			<to>3</to>&#13;
			<read>0</read>&#13;
		</transition>&#13;
		<transition>&#13;
			<from>2</from>&#13;
			<to>3</to>&#13;
			<read>1</read>&#13;
		</transition>&#13;
		<transition>&#13;
			<from>1</from>&#13;
			<to>2</to>&#13;
			<read>1</read>&#13;
		</transition>&#13;
		<transition>&#13;
			<from>1</from>&#13;
			<to>2</to>&#13;
			<read>0</read>&#13;
		</transition>&#13;
		<transition>&#13;
			<from>4</from>&#13;
			<to>3</to>&#13;
			<read>1</read>&#13;
		</transition>&#13;
		<transition>&#13;
			<from>0</from>&#13;
			<to>1</to>&#13;
			<read>1</read>&#13;
		</transition>&#13;
		<transition>&#13;
			<from>1</from>&#13;
			<to>4</to>&#13;
			<read>0</read>&#13;
		</transition>&#13;
	</automaton>&#13;
</structure>
```


O automato foi lido, manipulado e convertido pelo software e o resultado final encontra-se na imagem 2. Vale citar que a ordem das labels não é a mesma que a do JFLAP, porém a equivalencia não é afetada por este detalhe.


![Automato convertido](https://github.com/DayaneCordeiro/Primeiro_Trabalho_FTC/blob/main/img/AFD.png)


O código XML gerado pelo conversor no arquivo de saída, foi o seguinte:

```
<?xml version="1.0" encoding="UTF-8" standalone="no"?><!--Created with JFLAP 6.4.--><structure>&#13;
    <type>fa</type>&#13;
    <automaton>&#13;
        <!--The list of states.-->&#13;
        <state id="0" name="q0">&#13;
            <x>0.0</x>&#13;
            <y>0.0</y>&#13;
            <label>0</label>&#13;
            <initial/>&#13;
        </state>&#13;
        <state id="1" name="q1">&#13;
            <x>0.0</x>&#13;
            <y>0.0</y>&#13;
            <label>1,0</label>&#13;
        </state>&#13;
        <state id="2" name="q2">&#13;
            <x>0.0</x>&#13;
            <y>0.0</y>&#13;
            <label>4,2,0</label>&#13;
        </state>&#13;
        <state id="3" name="q3">&#13;
            <x>0.0</x>&#13;
            <y>0.0</y>&#13;
            <label>2,1,0</label>&#13;
        </state>&#13;
        <state id="4" name="q4">&#13;
            <x>0.0</x>&#13;
            <y>0.0</y>&#13;
            <label>3,2,0</label>&#13;
            <final/>&#13;
        </state>&#13;
        <state id="5" name="q5">&#13;
            <x>0.0</x>&#13;
            <y>0.0</y>&#13;
            <label>4,3,1,0</label>&#13;
            <final/>&#13;
        </state>&#13;
        <state id="6" name="q6">&#13;
            <x>0.0</x>&#13;
            <y>0.0</y>&#13;
            <label>4,3,2,0</label>&#13;
            <final/>&#13;
        </state>&#13;
        <state id="7" name="q7">&#13;
            <x>0.0</x>&#13;
            <y>0.0</y>&#13;
            <label>3,2,1,0</label>&#13;
            <final/>&#13;
        </state>&#13;
        <state id="8" name="q8">&#13;
            <x>0.0</x>&#13;
            <y>0.0</y>&#13;
            <label>3,1,0</label>&#13;
            <final/>&#13;
        </state>&#13;
        <state id="9" name="q9">&#13;
            <x>0.0</x>&#13;
            <y>0.0</y>&#13;
            <label>4,3,2,1,0</label>&#13;
            <final/>&#13;
        </state>&#13;
        <!--The list of transitions.-->&#13;
        <transition>&#13;
            <from>0</from>&#13;
            <to>0</to>&#13;
            <read>0</read>&#13;
        </transition>&#13;
        <transition>&#13;
            <from>0</from>&#13;
            <to>1</to>&#13;
            <read>1</read>&#13;
        </transition>&#13;
        <transition>&#13;
            <from>1</from>&#13;
            <to>2</to>&#13;
            <read>0</read>&#13;
        </transition>&#13;
        <transition>&#13;
            <from>1</from>&#13;
            <to>3</to>&#13;
            <read>1</read>&#13;
        </transition>&#13;
        <transition>&#13;
            <from>2</from>&#13;
            <to>4</to>&#13;
            <read>0</read>&#13;
        </transition>&#13;
        <transition>&#13;
            <from>2</from>&#13;
            <to>5</to>&#13;
            <read>1</read>&#13;
        </transition>&#13;
        <transition>&#13;
            <from>3</from>&#13;
            <to>6</to>&#13;
            <read>0</read>&#13;
        </transition>&#13;
        <transition>&#13;
            <from>3</from>&#13;
            <to>7</to>&#13;
            <read>1</read>&#13;
        </transition>&#13;
        <transition>&#13;
            <from>4</from>&#13;
            <to>4</to>&#13;
            <read>0</read>&#13;
        </transition>&#13;
        <transition>&#13;
            <from>4</from>&#13;
            <to>8</to>&#13;
            <read>1</read>&#13;
        </transition>&#13;
        <transition>&#13;
            <from>5</from>&#13;
            <to>2</to>&#13;
            <read>0</read>&#13;
        </transition>&#13;
        <transition>&#13;
            <from>5</from>&#13;
            <to>9</to>&#13;
            <read>1</read>&#13;
        </transition>&#13;
        <transition>&#13;
            <from>6</from>&#13;
            <to>4</to>&#13;
            <read>0</read>&#13;
        </transition>&#13;
        <transition>&#13;
            <from>6</from>&#13;
            <to>5</to>&#13;
            <read>1</read>&#13;
        </transition>&#13;
        <transition>&#13;
            <from>7</from>&#13;
            <to>6</to>&#13;
            <read>0</read>&#13;
        </transition>&#13;
        <transition>&#13;
            <from>7</from>&#13;
            <to>7</to>&#13;
            <read>1</read>&#13;
        </transition>&#13;
        <transition>&#13;
            <from>8</from>&#13;
            <to>2</to>&#13;
            <read>0</read>&#13;
        </transition>&#13;
        <transition>&#13;
            <from>8</from>&#13;
            <to>3</to>&#13;
            <read>1</read>&#13;
        </transition>&#13;
        <transition>&#13;
            <from>9</from>&#13;
            <to>6</to>&#13;
            <read>0</read>&#13;
        </transition>&#13;
        <transition>&#13;
            <from>9</from>&#13;
            <to>9</to>&#13;
            <read>1</read>&#13;
        </transition>&#13;
    </automaton>&#13;
</structure>
</code>
```

## :rocket: Tecnologias ##

As seguintes ferramentas foram utilizadas neste projeto:

- [Java](https://www.java.com/pt-BR/download/manual.jsp)
- [JFLAP](http://www.jflap.org/)

## :white_check_mark: Requisitos ##

<div align="justify">

1. Antes de iniciar, é necessário ter o Java instalado na máquina para executar o JFLAP.
2. Para testar um automato, é necessário incluir o arquivo .jff na raiz do projeto e modificar a constante `INPUT_PATH` no arquivo FileHelper.java com o nome do novo arquivo.

![Constante INPUT_PATH](https://github.com/DayaneCordeiro/Primeiro_Trabalho_FTC/blob/main/img/input_path.png)

3. Após isso, basta executar o arquivo Main.java que o arquivo de saída será gerado. 
4. Abrir o JFLAP, na aba File selecionar a opção Open e abrir o arquivo AFD.jff que está dentro da pasta output na raiz deste projeto.
</div>

## :memo: Licença ##

Este projeto está sob a licença MIT. Para mais detalhes, veja o arquivo [LICENSE](LICENSE).


## :memo: Autora ##
<a href="https://github.com/DayaneCordeiro">
    <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/50596100?v=4" width="150px;" alt=""/>
    <br />
    <sub><b>Dayane Cordeiro</b></sub>
</a>

Made with ❤️ by Dayane Cordeiro!

✔ Computer Engineering student at PUC Minas<br>
✔ PHP Developer<br>
✔ Passionate about software development, computer architecture and learning.<br>

[![Linkedin Badge](https://img.shields.io/badge/-Dayane-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/dayane-cordeiro-1b761318b/)](https://www.linkedin.com/in/dayane-cordeiro-1b761318b/)
[![Gmail Badge](https://img.shields.io/badge/-dayane.cordeirogs@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:dayane.cordeirogs@gmail.com)](mailto:dayane.cordeirogs@gmail.com)

