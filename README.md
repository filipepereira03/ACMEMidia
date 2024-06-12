# ACMEMidia

Este projeto é uma aplicação Java que gerencia uma midiateca, que é uma coleção de mídias. As mídias podem ser de dois tipos: vídeos e músicas. Cada mídia tem um código, título, ano e categoria. Além disso, os vídeos têm uma qualidade (em pixels) e as músicas têm uma duração (em minutos).

## Classes

O projeto é composto pelas seguintes classes:

- `Midia`: Classe abstrata que representa uma mídia genérica. Possui atributos comuns a todas as mídias, como código, título, ano e categoria.

- `Video` e `Musica`: São subclasses de `Midia` que representam, respectivamente, um vídeo e uma música. Além dos atributos herdados de `Midia`, `Video` possui um atributo de qualidade e `Musica` possui um atributo de duração.

- `Midiateca`: Classe que representa uma coleção de mídias. Possui métodos para adicionar, remover e consultar mídias.

- `ACMEMidia`: Classe principal do projeto. É responsável por interagir com o usuário, ler os dados de entrada, chamar os métodos apropriados da classe `Midiateca` e exibir os resultados.

## Funcionalidades

O projeto permite realizar as seguintes operações:

- Cadastrar vídeos e músicas na midiateca.
- Consultar uma mídia por código.
- Consultar todas as mídias de uma determinada categoria.
- Consultar todos os vídeos de uma determinada qualidade.
- Encontrar a música com a maior duração.
- Remover uma mídia por código.
- Calcular o somatório das locações.
- Encontrar a música mais próxima da média de locações.
- Encontrar a mídia mais nova.

## Como usar

Para usar o projeto, você precisa ter o Java instalado em seu computador. Em seguida, você pode compilar e executar o projeto usando um IDE de sua escolha, como o IntelliJ IDEA.


