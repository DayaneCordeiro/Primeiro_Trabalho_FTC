** mudar a função que ve se o estado está entre os finais pq tem que ser pela label pra não dar erro.

Algoritmo:

-> Joga o nome do primeiro estado para currentStateControll
-> Joga o primeiro estado do automato atual para o final com a label = 0
-> Preenche o currentStateId com zero
-> Preenche o newStateIndex = 1
-> isFinal = false

-> Enquanto currentStateControll for diferente de null
-> está entre os finais?
-> sim: isFinal = true

-> onde cada estado de currentStateControll vai com 0 (transições)? arrayDasTransiçõesDoZero
-> concatena o id de cada uma com a labelToZero

-> onde cada estado de currentStateControll vai com 1 (transições)? arrayDasTransiçõesDoUm
-> concatena o id de cada uma com a labelToOne

-> insere o id de cada uma no controle de labelsToZero
-> insere o id de cada uma no controle de labelsToOne

-> CRIAR A ESTADO SE NÃO EXISTIR
-> o automato final possui um estado com essa label 0?
-> Sim
-> salva o id na destinyStateId
-> Cria a transição com 0 se existir:
-> from: currentStateId
-> to: destinyStateId
-> read: 0
-> currentStateControll.remove(posição em que esse estado se encontra);
-> Não
-> Cria um novo estado:
-> id: newStateIndex
-> name: "q" + newStateIndex
-> isInitial: false
-> isFinal: isFinal
-> label: labelToOne
-> destinyStateId = newStateIndex
-> currentStateControll.add(name do novo estado criado);
-> Cria a transição com 0 se existir:
-> from: currentStateId
-> to: destinyStateId
-> read: 0
-> newStateIndex++;

-> o automato final possui um estado com essa label 1?
-> Sim
-> salva o id na destinyStateId
-> Cria a transição com 1 se existir:
-> from: currentStateId
-> to: destinyStateId
-> read: 1
-> currentStateControll.remove(posição em que esse estado se encontra);
-> Não
-> Cria um novo estado:
-> id: newStateIndex
-> name: "q" + newStateIndex
-> isInitial: false
-> isFinal: isFinal
-> label: labelToOne
-> destinyStateId = newStateIndex
-> currentStateControll.add(name do novo estado criado);
-> Cria a transição com 1 se existir:
-> from: currentStateId
-> to: destinyStateId
-> read: 1
-> newStateIndex++;	

1. Pega o primeiro estado do automato lido, muda a label dele para 0, escreve ele no automato final antes de entrar no loop
2. Entra em loop lendo os estados do automato final
3. Verifica se ele possui transição com o 0
   1. Ve onde ele pode ir com o zero. Salva isso em um array. Salva isso na label.
   2. Verifica no automato novo se possui algum estado com a label do zero e pega o id dele.
      1. Sim: cria uma transição nova.
         - Origem: estado atual.
         - Destino: estado de destino do zero.
         - valor: 0
      2. Não:
          1. Cria um estado novo:
              - id: stateIndexControl
              - name: "q" + stateIndexControl
              - isInitial: false
              - isFinal: isFinal
              - label: label do um
          2. Cria a transição:
              - Origem: estado atual.
              - Destino: estado de destino do zero.
              - valor: 0
4. Verifica se ele possui transição com 1
   1. Vê onde ele pode ir com um. Salva no array e na label.
   2. Verifica no automato novo se possui algum estado com a label do zero e pega o id dele.
      1. Sim: cria uma transição nova.
          - Origem: estado atual.
          - Destino: estado de destino do zero.
          - valor: 1
      2. Não:
         1. Cria um estado novo no automato final:
            - id: stateIndexControl
            - name: "q" + stateIndexControl
            - isInitial: false
            - isFinal: isFinal
            - label: label do um
         2. Atualiza o estado de destino do 1
         3. Cria a transição:
            - Origem: estado atual.
            - Destino: estado destino do um
            - valor: 1
----
1. Lê o array de labels e faz um loop com cada posição.
2. Verifica se ele possui transição com o 0
    1. Ve onde ele pode ir com o zero. Salva isso em um array. Salva isso na label.
    2. Verifica no automato novo se possui algum estado com a label do zero e pega o id dele.
        1. Sim: cria uma transição nova.
            - Origem: estado atual.
            - Destino: estado de destino do zero.
            - valor: 0
        2. Não:
            1. Cria um estado novo:
                - id: stateIndexControl
                - name: "q" + stateIndexControl
                - isInitial: false
                - isFinal: isFinal
                - label: label do um
            2. Cria a transição:
                - Origem: estado atual.
                - Destino: estado de destino do zero.
                - valor: 0
3. Verifica se ele possui transição com 1
    1. Vê onde ele pode ir com um. Salva no array e na label.
    2. Verifica no automato novo se possui algum estado com a label do zero e pega o id dele.
        1. Sim: cria uma transição nova.
            - Origem: estado atual.
            - Destino: estado de destino do zero.
            - valor: 1
        2. Não:
            1. Cria um estado novo no automato final:
                - id: stateIndexControl
                - name: "q" + stateIndexControl
                - isInitial: false
                - isFinal: isFinal
                - label: label do um
            2. Atualiza o estado de destino do 1
            3. Cria a transição:
                - Origem: estado atual.
                - Destino: estado destino do um
                - valor: 1