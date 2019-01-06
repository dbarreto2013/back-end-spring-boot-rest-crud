# SRM Asset - Back-End Rest(Spring-Boot) - Cadastro de Cliente

Este é um teste aplicado em um processo seletivo.

## Pré-Requisitos

Banco de Dados MongoDB 3.x ou utilizar o Firebase que já encontra-se configurado na aplicação em modo default.

Para alterar para o MongoDB, tenha ele instalado e rodando, e na sequência altere a classe com.srm.asset.service.impl.ClienteServiceImpl onde todos os serviços que utiliza a variável `clienteRepositoryFirebase` trocar para `clienteRepository`.

JDK 8(Oracle).

Maven 3.6.0.

## Compilar
Compilar via linha de comando o projeto conforme exemplo:
`C:\pathProject>mvn clean install`

## Rodar Projeto

Se for rodar com o MongoDB inicie o mesmo conforme exemplo:
`"C:\Program Files\MongoDB\Server\3.6\bin\mongod.exe" --dbpath="C:\pathProject"`

Iniciando a aplicação, conforme exemplo:
`C:\pathProject>java -jar target\srm-asset-backend-server-1.0.0.jar`

Operações REST da aplicação:

Recuperar todos os clientes: #GET `http://localhost/cliente/clientes`
Adiciona um novo cliente: #POST `http://localhost/cliente/add`
Deletar cliente: #DELETE `http://localhost/cliente/delete/{id}`
Atualizar cliente: #PUT `http://localhost/cliente/{id}`

## Eventuais dúvidas

Entre em contato via e-mail d.barreto.santos@gmail.com ou Linkedin https://www.linkedin.com/in/daniel-barreto-7a795728/

