# mvvm-learning-concepts
Uma aplicação que exibe uma lista com videos no YouTube, fazendo requisições através do retrofit com a arquitetura MVVM 

# Todo o meu entendimento sobre o porjeto e explicação de alguns conceitos
## LIVE #008 - Arquitetura MVVM no android com kotlin

Arquitetura MVVM - formas de codar o seu programa
Model - View - ViewModel

Model - Dados do app, classe da orientação a objeto 
 Em um exemplo com RecyclerView a data class fica no model 

 Criação da Repository(Design Pattern)
  Vamos usar essa padrão para lidar com os dados da API 
   Criamos esse package para caso algum dia mudarmos a biblioteca de requisição web, 
  a adaptação ocorrerá de uma forma muito mais simples 

View - onde vai ficar a MainActivity

ViewModel - responsabilidade de fazer os tratamentos de threads

a view ela não tem uma referência direta a model e sim a viewmodel através de um observável, porque facilita a utilização de testes unitários

Na configuração da classe ViewModel, precisamos criar uma classe e estender o ViewModel(que faz parte da biblioteca lifecycle)
É na classe ViewModel que fica a lógica de negócios 
No construtor da ViewModel precisamos precisamos passar o repositório de dados
que criamos com retrofit

Então, a ViewModel tem uma função com um request do serviço do retrofit, através do repositório. Nesse request tem um callback, que significa “executa isso quando você terminar”. Tanto é que temos que implementar dois métodos, um que é quando algo dá certo e outro quando dá errado.
	onResponse -> quando houver uma chamada 
	onFailure -> quando houver uma falha 

Estamos usando o live data para atualizar os dados para a UI
Sabendo que o live data respeita o ciclo de vida do android, isso significa que ele não invocará seu retorno de chamada do observador, a menos que a activity ou fragment tenha passado pelo onStart e não tenha passado pelo onStop
Além disso, o Live Data removerá automaticamente o observador quando seu host receber o onDestroy

Criando a ViewModelFactory
 Não podemos criar a ViewModel manualmente, precisamos do utilitário         ViewModelProviders do próprio android para fazer a criação da ViewMode
 O ViewModelProviders não pode receber a ViewModel com um construtor recebendo parâmetros 
 E quando temos um ViewModel que recebe argumentos precisamos criar uma factory
