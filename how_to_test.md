# How to test omdb-api requests

## Steps to test API(S)



1. All the requests required api key so generate key .
```
POST http://localhost:8080/api/keys
Request body : {"email":"anil.ravula@gmail.com"}
```
Sample response

```{"apiKey": "C2YLm15lITARQrz9v8fc4NKApgAhZKyZhRag7JESvP355aczlw=="}```


Testing oscar winning api

* At opened browser, go to (GET http://localhost:8080/api/academy-awards?key=<generated in step1>&tittle=<>), 
* you'll see build list renders correctly with mock data.