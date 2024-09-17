# Getting Started

### Rotas API ###

### Users ###
1. https://projetovivido-back-end.onrender.com/home/register (POST)


```
{
	"name": "Matheus",
	"login": "matheus",
	"password": "1234"
}
```

2. https://projetovivido-back-end.onrender.com/home/login (POST)

```
{
	"login": "matheus",
	"password": "1234"
}

```

3. https://projetovivido-back-end.onrender.com/home/edit (PUT)
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

4. https://projetovivido-back-end.onrender.com/home/get-data (GET)
   (Requer token)

```
{
	"id": 1,
	"name": "Matheus",
	"login": "matheus",
	"pass": "1234",
	"adress": "Rua Vivido",
	"phone": 1111111,
	"cep": 0
}
```

5. https://projetovivido-back-end.onrender.com/home/delete (DELETE)
(Requer token)

```
SEM JSON
```

6. https://projetovivido-back-end.onrender.com/home/getall (GET)

```
SEM JSON
```

7. https://projetovivido-back-end.onrender.com/home/continue/register (PUT)

```
{
	"cep": 6464,
	"phone": 333,
	"adress": "Rua teste"
}

```

### Guardião ###

1. https://projetovivido-back-end.onrender.com/guardian/create (POST)
(Requer Token)

````
{
	"name": "Eek-A-Mouse",
	"phone": "86994617674"
}
````
2. https://projetovivido-back-end.onrender.com/guardian/get-guardians (GET)
(Requer Token)

````
Sem Json
````

3. https://projetovivido-back-end.onrender.com/guardian/edit/guardian (PUT)

````
{
	"id": 1,
	"name": null,
	"phone": "86981352299"
}
````

4. https://projetovivido-back-end.onrender.com/guardian/delete/guardian (DELETE)

````
{
	"id": 2
}
````

### Guidance (Orientação) ###

1. https://projetovivido-back-end.onrender.com/guidance/form (GET)

````
(ESSE É O JSON DE SAÍDA DESSE END POINT, PARA EXIBIR OS DOIS TIPOS DE ATENDIMENTO DISPONÍVEIS)

[
	"NPJ",
	"PSICOLOGIA"
]
````

2. https://projetovivido-back-end.onrender.com/guidance/create (POST)
(Requer Token)

````
{
	"descricao": "TESTE",
	"type": "PSICOLOGIA"
}
````

3. https://projetovivido-back-end.onrender.com/guidance/filter/guidance (GET)
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
