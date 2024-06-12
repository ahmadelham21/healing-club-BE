# Hotel Backend Management System
The Hotel Backend Management System is a backend application designed to manage the operations and data related to hotels. This system provides a RESTful API interface, enabling integration with frontend applications or other systems. By leveraging Spring Boot as the main framework and PostgreSQL as the database, the application offers high performance, scalability, and security in managing hotel data. Additionally, ImageKit is used to handle the storage and management of images related to the hotel.

## Technologies Used
- **Programming Language:** Java
- **Framework:** Spring Boot
- **Database:** PostgreSQL
- **Image Storage Service:** ImageKit (REST client)
# API Reference

## Facility
### Create New Facility

```http
  POST /api/v1/facilities
```

#### Body

```json
{
  "name": "kolam renang"
}
```

#### Response

```json
{
  "id": 1,
  "name": "kolam renang"
}
```

### Get Facility By Id

```http
  GET /api/v1/facilities/{id}
```


#### Path Variable
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | **Required**. Id of item to fetch |




#### Response

```json
{
  "id": 1,
  "name": "kolam renang"
}
```


### Get All Facility

```http
  GET /api/v1/facilities
```

#### Response

```json
[
  {
    "id": 1,
    "name": "kolam renang"
  },
  {
    "id": 2,
    "name": "Gym"
  }
]
```



### Update Facility

```http
  PUT /api/v1/facilities
```
#### Body
```json
{       
  "id": 1,
  "name": "Kolam Ikan"
 }
```

#### Response

```json
{
  "id": 1,
  "name": "Kolam Ikan"
}
```

### Delete Facility By Id

```http
  DELETE /api/v1/facilities/{id}
```
#### Path Variable
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | **Required**. Id of item to fetch |


#### Response

```json
{
  "message":"Successfully delete data"
}
```

## Hotel


### Create Hotel
```http
  POST /api/v1/hotels
```


#### Request Part
| Parameter       | Type   | Description                                    |
|:----------------|:-------|:-----------------------------------------------|
| `file`          | `File` | **Required**. Image for the hotel              |
| `hotel_request` | `Text` | **Required**. Hotel information for save hotel |

#### hotel_request

```json
{
  "name":"Hotel aston",
  "rating": 4.8,
  "address" : "Jl.Marbot",
  "facilityId": [1,2]
}
```

#### Response
```json
{
    "statusCode": 200,
    "message": "Successfully create data",
    "data": {
        "id": "afc893c0-a3b5-4ce5-83b7-d78a46f00511",
        "name": "Hotel aston",
        "rating": 4.8,
        "address": "Jl.Marbot",
        "pictures": [
            {
                "id": "95daac43-0dc0-40e5-874c-6438f8950098",
                "url": "https://ik.imagekit.io/soeauotwj/ec9c4cb8-7460-4d7b-9ca0-e053e80bdb68_Screenshot_from_2024-06-06_15-15-18_DXMX5EZQf.png"
            }
        ],
        "hotelFacilities": [
            {
                "id": "f546cec2-3e22-448f-acd2-cf1b357df476",
                "facility": {
                    "id": 1,
                    "name": "kolam renang"
                }
            },
            {
                "id": "5c78b987-5fac-44d1-8b1b-1cc62bd3eb80",
                "facility": {
                    "id": 2,
                    "name": "Gym"
                }
            }
        ]
    },
    "paging": null
}
```

### Get By Id Hotel

```http
  GET /api/v1/hotels/{id}
```
#### Path Variable
| Parameter | Type     | Description                       |
| :-------- |:---------| :-------------------------------- |
| `id`      | `String` | **Required**. Id of item to fetch |


#### Response
```json
{
  "statusCode": 200,
  "message": "Successfully get data",
  "data": {
    "id": "e9065d3d-fa3b-4128-badd-369b9d13576e",
    "name": "Hotel aston",
    "rating": 4.8,
    "address": "Jl.Marbot",
    "pictures": [
      {
        "id": "a54940b1-e4f6-413f-af0d-2f790a300cb3",
        "url": "https://ik.imagekit.io/soeauotwj/a79cbef3-53cd-4a07-971d-c8a270d87630_Screenshot_from_2024-06-06_15-15-18_8rP7tQgYC.png"
      }
    ],
    "hotelFacilities": [
      {
        "id": "7ac7d778-87d8-408d-84c9-51fb8c302124",
        "facility": {
          "id": 3,
          "name": "kolam renang"
        }
      }
    ]
  },
  "paging": null
}
```


### Get All Hotel

```http
  GET /api/v1/hotels
```

#### Response

```json
{
    "statusCode": 200,
    "message": "Successfully get data",
    "data": [
        {
            "id": "e9065d3d-fa3b-4128-badd-369b9d13576e",
            "name": "Hotel aston",
            "rating": 4.8,
            "address": "Jl.Marbot",
            "pictures": [
                {
                    "id": "a54940b1-e4f6-413f-af0d-2f790a300cb3",
                    "url": "https://ik.imagekit.io/soeauotwj/a79cbef3-53cd-4a07-971d-c8a270d87630_Screenshot_from_2024-06-06_15-15-18_8rP7tQgYC.png"
                }
            ],
            "hotelFacilities": [
                {
                    "id": "7ac7d778-87d8-408d-84c9-51fb8c302124",
                    "facility": {
                        "id": 3,
                        "name": "kolam renang"
                    }
                }
            ]
        },
        {
            "id": "afc893c0-a3b5-4ce5-83b7-d78a46f00511",
            "name": "Hotel aston",
            "rating": 4.8,
            "address": "Jl.Marbot",
            "pictures": [
                {
                    "id": "95daac43-0dc0-40e5-874c-6438f8950098",
                    "url": "https://ik.imagekit.io/soeauotwj/ec9c4cb8-7460-4d7b-9ca0-e053e80bdb68_Screenshot_from_2024-06-06_15-15-18_DXMX5EZQf.png"
                }
            ],
            "hotelFacilities": [
                {
                    "id": "f546cec2-3e22-448f-acd2-cf1b357df476",
                    "facility": {
                        "id": 3,
                        "name": "kolam renang"
                    }
                },
                {
                    "id": "5c78b987-5fac-44d1-8b1b-1cc62bd3eb80",
                    "facility": {
                        "id": 4,
                        "name": "kolam renang"
                    }
                }
            ]
        }
    ],
    "paging": null
}
```

### Delete hotel by id

```http
  DELETE /api/v1/hotels/{id}
```

#### Path Variable
| Parameter | Type     | Description                       |
| :-------- |:---------| :-------------------------------- |
| `id`      | `String` | **Required**. Id of item to fetch |


#### Response

```json
{
    "statusCode": 200,
    "message": "Successfully delete data",
    "data": null,
    "paging": null
}
```








