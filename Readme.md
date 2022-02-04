# Api Alerta

## Documentação

Documetnação da api > /documentação/postman

## Deploy da API

### 1. Gerar pacote
**Alterar a versão da API**
> Alterar versão da Api em *src/main/java/com/alerta/dc/controller/VersionController.java*
```json
{
	"version", "dev-1.0.2",
	"datetime", "2021-11-17 09:00:00"
}
```
> Comandos separados:
``` sh
$ ./mvnw clean 
$ ./mvnw package -DskipTests
```
> Comando inteiro:
``` sh
$ ./mvnw clean package -DskipTests
```
### 2. Mover arquivo 

Na pasta mover o arquivo gerado da pasta **/target** para deploy

### 3. Renomear pacote

Renomear o arquivo .war gerado para **api-alerta-0.0.1.war**

### 4. Preparar arquivos para envio 

Comprimir a pasta deploy para .zip e enviar conteúdo para o suporte.

### 5. Procedimento completo via terminal
``` sh
./mvnw clean package -DskipTests && mv target/api-alerta-0.0.1.war deploy/api-alerta-0.0.1.war && tar -zcvf api-alerta-dev-1.0.3.tar.gz deploy/ && rm deploy/api-alerta-0.0.1.war
```
### Via Script
``` sh
./generate_package [name_version]
```
