
## Super Secret Contact Management System

(optional) Builda after building Maven package (.jar file)
```
docker build -t karlaru/sec-crm .
```

Create subnet for main app and PostgresSQL
```
docker network create --driver=bridge --subnet=10.0.0.0/28 secret-network
```

Run Postgres
```
docker run -d --name postgres --network secret-network --ip 10.0.0.2 -e POSTGRES_PASSWORD=secretpassword -e POSTGRES_DB=contacts -p 5432:5432 postgres
```

Run Main app
```
docker run -d --network secret-network -p 8080:8080 karlaru/sec-crm
```

## Access frontend [localhost:8080](http://localhost:8080)

#### Direct API access

POST contact
```
curl -X POST -H "Content-Type: application/json" -d '{"name": "karl", "codeName": "coden", "phone": "5534"}' http://localhost:8080/api/contacts
```

GET contact
```
curl -X GET http://localhost:8080/api/contacts
```

Search
```
curl -X GET http://localhost:8080/api/contacts?s=karl
```
