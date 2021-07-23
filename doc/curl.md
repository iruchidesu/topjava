### getAll

curl -X GET "http://localhost:8080/topjava/rest/meals"

### get

curl -X GET "http://localhost:8080/topjava/rest/meals/100002"

### getBetween

curl -X
GET "http://localhost:8080/topjava/rest/meals/filter?startDate=2020-01-31&startTime=09:00&endDate=2020-01-31&endTime=13:30"

### create

curl -X POST -H "Content-Type: application/json" -d "{\"dateTime\":\"2020-02-01T18:00\",\"description\": \"Созданный
ужин\",\"calories\": 300}" "http://localhost:8080/topjava/rest/meals"

### update

curl -X PUT -H "Content-Type: application/json" -d "{\"id\": 100002,\"dateTime\":\"2020-01-30T10:00\",\"description\":
\"Обновленный завтрак\",\"calories\": 600}" "http://localhost:8080/topjava/rest/meals/100002"

### delete

curl -X DELETE "http://localhost:8080/topjava/rest/meals/100002"
