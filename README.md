## Run

    > ./gradlew bootRun

## Helpful Commands

### Format Responses

If you install the linux command line tool `jq` you can pipe all the responses to that tool and have the JSON formatted properly.

#### Before

    > curl --silent -H"Accept: application/json" http://localhost:8080/users
    {"content":[],"pageable":{"sort":{"empty":true,"sorted":false,"unsorted":true},"offset":0,"pageNumber":0,"pageSize":20,"paged":true,"unpaged":false}...}

#### After

    > curl --silent -H"Accept: application/json" http://localhost:8080/users | jq
    {
      "content": [],
      "pageable": {
        "sort": {
          "empty": true,
          "sorted": false,
          "unsorted": true
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 20,
        "paged": true,
        "unpaged": false
      },
      ...
    }



### List Users

    curl --silent -H"Accept: application/json" http://localhost:8080/users

### Create User

    curl --silent -H"Accept: application/json" -H"Content-Type: application/json" -XPOST --data-raw '{"username":"dustin","password":"apple"}' http://localhost:8080/users

### Authenticate

    curl --silent -H"Accept: application/json" -H"Content-Type: application/json" -XPOST --data-raw '{"username":"dustin","password":"apple"}' http://localhost:8080/users/authenticate