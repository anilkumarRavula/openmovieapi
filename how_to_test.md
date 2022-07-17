# How to test omdb-api requests

## Steps to test API(S)

###Generating API key : 
All the requests required api key so generate key .
```
POST http://localhost:8080/api/keys
Request body : {"email":"anil.ravula@gmail.com"}
```
Sample response

```{"apiKey": "C2YLm15lITARQrz9v8fc4NKApgAhZKyZhRag7JESvP355aczlw=="}```


###Testing oscar omdb api

 GOTO post man, go to (GET http://localhost:8080/api/academy-awards?key=<generated in step1>&title=<>).
Note : encode api key before sending that it will be delivered asit is.
for example : 
 API key :  LKy9xs4ss7JeT+VVt4YEGw4z++Hm8rwhGkrQ5WiBDHgPCySYGA== 
 Title : Avatar
Sample Request :

```http://localhost:8080/api/academy-awards?key=LKy9xs4ss7JeT%2BVVt4YEGw4z%2B%2BHm8rwhGkrQ5WiBDHgPCySYGA%3D%3D&title=Avatar```

Sample Response : 

```[{"year":"2009 ","won":"NO","title":"Avatar"}]```