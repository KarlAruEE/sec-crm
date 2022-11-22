

```
docker build -t karlaru/sec-crm .
```

```
docker network create --driver=bridge --subnet=10.0.0.0/28 secret-network
```

```
docker run -d --name postgres --network secret-network --ip 10.0.0.2 -e POSTGRES_PASSWORD=secretpassword -e POSTGRES_DB=contacts -p 5432:5432 postgres
```

```
docker run -d --network secret-network -p 8080:8080 karlaru/sec-crm
```

```
curl -X POST -H "Content-Type: application/json" -d '{"name": "karl", "codeName": "coden", "phone": "5534"}' http://localhost:8080/api/contacts
```

```
curl -X GET http://localhost:8080/api/contacts
```

```
curl -X GET http://localhost:8080/api/contacts?s=karl
```
