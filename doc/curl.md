## curl samples

### Get аll meals for the current user

curl -X GET "http://localhost:8080/topjava/rest/meals"

### Get meal with id 100002

curl -X GET "http://localhost:8080/topjava/rest/meals/100002"

### Filtered meals for the current user between dates 2020 01 31 09:00 and 2020 01 31 13:30

curl -X
GET "http://localhost:8080/topjava/rest/meals/filter?startDate=2020-01-31&startTime=09:00&endDate=2020-01-31&endTime=13:30"

### Create meal for the current user

curl -X POST -H "Content-Type: application/json" -d "{\"dateTime\":\"2020-02-01T18:00\",\"description\": \"Созданный
ужин\",\"calories\": 300}" "http://localhost:8080/topjava/rest/meals"

### Update meal with id 100002

curl -X PUT -H "Content-Type: application/json" -d "{\"id\": 100002,\"dateTime\":\"2020-01-30T10:00\",\"description\":
\"Обновленный завтрак\",\"calories\": 600}" "http://localhost:8080/topjava/rest/meals/100002"

### Delete meal with id 100002

curl -X DELETE "http://localhost:8080/topjava/rest/meals/100002"

### Get current user with meals

curl -X GET "http://localhost:8080/topjava/rest/profile/with-meals"

### Get user with id 100001 with meals for admin

curl -X GET "http://localhost:8080/topjava/rest/admin/users/100001/with-meals"
