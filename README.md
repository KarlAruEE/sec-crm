
## Super Secret Contact Management System

### Run with Docker Compse

Start
```
docker-compose up -d
```

#### Simple JavaScript Front-end at [localhost:8080](http://localhost:8080)

Stop
```
docker-compose down
```

---

### Manual

(optional) Build docker image after building Maven package (.jar file)
```
docker build -t karlaru/sec-crm .
```

Create subnet for main app and PostgresSQL
```
docker network create --driver=bridge --subnet=10.0.0.0/28 secret-network
```

Run Postgres database
```
docker run -d --name postgres --network secret-network --ip 10.0.0.2 -e POSTGRES_PASSWORD=secretpassword -e POSTGRES_DB=contacts -p 5432:5432 postgres
```

Run Main app [karlaru/sec-crm (from DockerHub)](https://hub.docker.com/repository/docker/karlaru/sec-crm)
```
docker run -d --network secret-network -p 8080:8080 karlaru/sec-crm
```

#### Simple JavaScript Front-end at [localhost:8080](http://localhost:8080)

--- 

#### Direct API access

POST a contact
```
curl -X POST -H "Content-Type: application/json" -d '{"name": "karl", "codeName": "coden", "phone": "5534"}' http://localhost:8080/api/v1/contacts
```

GET all contacts
```
curl -X GET http://localhost:8080/api/v1/contacts
```

Search contact by string (over all fields)
```
curl -X GET http://localhost:8080/api/v1/contacts/search/{search_string}
```

### SQL Dump
```
docker exec sec-crm_PostgreSQL_1 pg_dump -U postgres postgres --no-owner  > db.sql
```