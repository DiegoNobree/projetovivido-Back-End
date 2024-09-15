# Getting Started

### Rotas API

### Users ###
1. localhost:8080/home/register


```
{
	"name": "Matheus",
	"login": "matheus",
	"password": "1234",
	"cep": "1111111",
	"adress": "Rua Vivido",
	"phone": "00000"
}
```

2. localhost:8080/home/login

```
{
	"login": "matheus",
	"password": "1234"
}

```

3. localhost:8080/home/edit
(Requer token)

```
{
	"name": "Jorge JAH",
	"oldpass": "1234",
	"password": "4321",
	"passcompare": "4321",
	"cep": null,
	"adress": null,
	"phone": "333"
}
```
4. localhost:8080/home/delete
(Requer token)

```
SEM JSON
```

5. localhost:8080/home/getall

```
SEM JSON
```

### Guardião ###

1. localhost:8080/guardian/create
(Requer Token)

````
{
	"name": "Eek-A-Mouse",
	"phone": "86994617674"
}
````
2. localhost:8080/guardian/get-guardians
(Requer Token)

````
Sem Json
````

3. localhost:8080/guardian/edit/guardian

````
{
	"id": 1,
	"name": null,
	"phone": "86981352299"
}
````

4. localhost:8080/guardian/delete/guardian

````
{
	"id": 2
}
````

### Guidance (Orientação) ###

1. localhost:8080/guidance/form

````
(ESSE É O JSON DE SAÍDA DESSE END POINT, PARA EXIBIR OS DOIS TIPOS DE ATENDIMENTO DISPONÍVEIS)

[
	"NPJ",
	"PSICOLOGIA"
]
````

2. localhost:8080/guidance/create
(Requer Token)

````
{
	"descricao": "TESTE",
	"type": "PSICOLOGIA"
}
````

3. localhost:8080/guidance/filter/guidance
(Requer Token. End-point para funcionários)

````
(ESSE É O JSON DE SAÍDA DESSE END POINT)

[
	{
		"id": 3,
		"name": "Diego",
		"adress": "Rua Vivido",
		"phone": 0,
		"type": "PSICOLOGIA"
	}
]
````
