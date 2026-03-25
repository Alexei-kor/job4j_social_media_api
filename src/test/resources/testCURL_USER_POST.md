1. CREATE USER
   curl -i -H "Content-Type:application/json" \
   -u admin:secret123 \
   -X POST \
   -d "{ \
   \"name\":\"student250326\", \
   \"email\":\"123@ya.ru\", \
   \"password\":\"123\"}" \
   http://localhost:8080/api/user

2. CREATE POST
   curl -i -H "Content-Type:application/json" \
   -u admin:secret123 \
   -X POST \
   -d "{\"head\":\"head from CURL 25_03\", \"period\":\"2026-03-25T14:01:01\", \"text\":\"Text from CURL\"}" \
   http://localhost:8080/api/post/1

3. GET USER
   curl -u admin:secret123 -i http://localhost:8080/api/user/1

4. GET POST
   curl -u admin:secret123 -i http://localhost:8080/api/post/1

5. UPDATE USER
   curl -i -H "Content-Type:application/json" \
   -u admin:secret123 \
   -X PUT \
   -d "{ \
   \"id\":7, \
   \"name\":\"student250327\", \
   \"email\":\"1234@ya.ru\", \
   \"password\":\"1234\"}" \
   http://localhost:8080/api/user

6. UPDATE POST
   curl -i -H "Content-Type:application/json" \
   -u admin:secret123 \
   -X PUT \
   -d "{ \
   \"id\":7, \
   \"head\":\"head from CURL 25_03_27\", \
   \"period\":\"2027-03-25T14:01:01\", \
   \"text\":\"Text from CURL_2027\"}" \
   http://localhost:8080/api/post


7. DELETE USER
   curl -u admin:secret123 -i -X DELETE http://localhost:8080/api/user/7

8. DELETE POST
   curl -u admin:secret123 -i -X DELETE http://localhost:8080/api/post/7

