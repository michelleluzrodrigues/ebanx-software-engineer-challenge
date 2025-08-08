# ebanx-software-engineer-challenge

Este projeto é uma API REST desenvolvida em Java com Spring Boot para o desafio técnico da EBANX.  
A API simula operações bancárias básicas em memória, sem uso de banco de dados.

---

## 📦 Funcionalidades

- `POST /event` — executa depósito, saque ou transferência
- `GET /balance?account_id={id}` — consulta saldo
- `POST /reset` — reseta o estado da aplicação (para testes)

---

## ⚙️ Como rodar localmente

### 🔧 Requisitos

- Java 17+
- Maven 3.8+ (ou use o `mvnw` incluído no projeto)

### ▶ Executando

```bash
  ./mvnw spring-boot:run
# ou
  mvn spring-boot:run
```

A API estará disponível em:
```
http://localhost:8080
```

---

## 🌐 Como expor sua API com Ngrok

A EBANX exige que a API esteja disponível publicamente. Isso pode ser feito com o [Ngrok](https://ngrok.com).

### 📥 Instalar Ngrok

Acesse o link abaixo e baixe a versão para seu sistema:

🔗 https://ngrok.com/download

Após o download:

- No Windows: extraia o ZIP e execute `ngrok.exe` no terminal
- No Linux/Mac: mova para `/usr/local/bin` e torne executável

### 🔐 Configurar o token de autenticação

1. Acesse: [https://dashboard.ngrok.com/get-started/your-authtoken](https://dashboard.ngrok.com/get-started/your-authtoken)
2. Copie o token gerado (exemplo: `1vRAbc123xyz...`)
3. No terminal, configure o token:

```bash
  ngrok config add-authtoken SEU_TOKEN_AQUI
```

### 🚀 Rodar o túnel para a porta 8080

Com sua aplicação Spring rodando em `localhost:8080`, execute:

```bash
  ngrok http 8080
```

Você verá algo assim:

```
Forwarding https://abcd1234.ngrok-free.app -> http://localhost:8080
```

Esse será o link público da sua API.

---

## ✅ Testar com a suíte automatizada da EBANX

1. Copie o link gerado pelo Ngrok (ex: `https://abcd1234.ngrok-free.app`)
2. Acesse: [https://ipkiss.pragmazero.com/](https://ipkiss.pragmazero.com/)
3. Cole o link no campo solicitado
4. Execute os testes
5. Aguarde a validação da API

> ⚠️ Lembre-se: a API **precisa estar rodando** localmente enquanto os testes estiverem sendo executados.

---

## 📁 Estrutura do projeto

```
src/
├── main/
│   ├── java/com.ebanx.challenge/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── model/
│   │   └── EbanxChallengeApplication.java
│   └── resources/
└── test/
```

---

## 🧑‍💻 Desenvolvido por

Michelle Luz Rodrigues  
