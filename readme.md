## Projeto backend de teste da vaga do picpay
### API Spring

https://github.com/PicPay/picpay-desafio-backend

---
- Insominia 
---
listarUsuários verbo get
http://localhost:8080/users

---
cadastrarUsuário verbo post
http://localhost:8080/users

{
	"firstName": "Ednaldo",
	"lastName": "Pereira",
	"password": "banido",
	"document": "777.777",
	"email": "ednaldoPereira@gmail.com",
	"userType": "COMUM",
	"balance": 10
}

---
realizarTransação verbo post

{
	"senderId": 2,
	"recieverId": 1,
	 "value": 10
}
