# \DefaultAPI

All URIs are relative to *http://localhost:8182*

Method | HTTP request | Description
------------- | ------------- | -------------
[**BookUserIdTrainIdTravelClassTicketTypePost**](DefaultAPI.md#BookUserIdTrainIdTravelClassTicketTypePost) | **Post** /book/{userId}/{trainId}/{travelClass}/{ticketType} | Book a train
[**TrainsDepartureStationArrivalStationDepartureDateReturnDateNumTicketsTravelClassGet**](DefaultAPI.md#TrainsDepartureStationArrivalStationDepartureDateReturnDateNumTicketsTravelClassGet) | **Get** /trains/{departureStation}/{arrivalStation}/{departureDate}/{returnDate}/{numTickets}/{travelClass} | Get train information



## BookUserIdTrainIdTravelClassTicketTypePost

> BookUserIdTrainIdTravelClassTicketTypePost(ctx, userId, trainId, travelClass, ticketType).Execute()

Book a train



### Example

```go
package main

import (
	"context"
	"fmt"
	"os"
	openapiclient "github.com/GIT_USER_ID/GIT_REPO_ID"
)

func main() {
	userId := "userId_example" // string | User ID
	trainId := "trainId_example" // string | Train ID
	travelClass := "travelClass_example" // string | Travel class
	ticketType := "ticketType_example" // string | Ticket type

	configuration := openapiclient.NewConfiguration()
	apiClient := openapiclient.NewAPIClient(configuration)
	r, err := apiClient.DefaultAPI.BookUserIdTrainIdTravelClassTicketTypePost(context.Background(), userId, trainId, travelClass, ticketType).Execute()
	if err != nil {
		fmt.Fprintf(os.Stderr, "Error when calling `DefaultAPI.BookUserIdTrainIdTravelClassTicketTypePost``: %v\n", err)
		fmt.Fprintf(os.Stderr, "Full HTTP response: %v\n", r)
	}
}
```

### Path Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**userId** | **string** | User ID | 
**trainId** | **string** | Train ID | 
**travelClass** | **string** | Travel class | 
**ticketType** | **string** | Ticket type | 

### Other Parameters

Other parameters are passed through a pointer to a apiBookUserIdTrainIdTravelClassTicketTypePostRequest struct via the builder pattern


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------





### Return type

 (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## TrainsDepartureStationArrivalStationDepartureDateReturnDateNumTicketsTravelClassGet

> TrainsDepartureStationArrivalStationDepartureDateReturnDateNumTicketsTravelClassGet(ctx, departureStation, arrivalStation, departureDate, returnDate, numTickets, travelClass).Execute()

Get train information



### Example

```go
package main

import (
	"context"
	"fmt"
	"os"
    "time"
	openapiclient "github.com/GIT_USER_ID/GIT_REPO_ID"
)

func main() {
	departureStation := "departureStation_example" // string | Departure station code (default to "stop_area:SNCF:87182113")
	arrivalStation := "arrivalStation_example" // string | Arrival station code (default to "stop_area:SNCF:80359331")
	departureDate := time.Now() // string | Departure date (default to "Mon Jan 15 00:00:00 UTC 2024")
	returnDate := time.Now() // string | Return date (optional) (default to "Tue Jan 16 00:00:00 UTC 2024")
	numTickets := int32(56) // int32 | Number of tickets (default to 1)
	travelClass := "travelClass_example" // string | Travel class (default to "Second Class")

	configuration := openapiclient.NewConfiguration()
	apiClient := openapiclient.NewAPIClient(configuration)
	r, err := apiClient.DefaultAPI.TrainsDepartureStationArrivalStationDepartureDateReturnDateNumTicketsTravelClassGet(context.Background(), departureStation, arrivalStation, departureDate, returnDate, numTickets, travelClass).Execute()
	if err != nil {
		fmt.Fprintf(os.Stderr, "Error when calling `DefaultAPI.TrainsDepartureStationArrivalStationDepartureDateReturnDateNumTicketsTravelClassGet``: %v\n", err)
		fmt.Fprintf(os.Stderr, "Full HTTP response: %v\n", r)
	}
}
```

### Path Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**departureStation** | **string** | Departure station code | [default to &quot;stop_area:SNCF:87182113&quot;]
**arrivalStation** | **string** | Arrival station code | [default to &quot;stop_area:SNCF:80359331&quot;]
**departureDate** | **string** | Departure date | [default to &quot;Mon Jan 15 00:00:00 UTC 2024&quot;]
**returnDate** | **string** | Return date (optional) | [default to &quot;Tue Jan 16 00:00:00 UTC 2024&quot;]
**numTickets** | **int32** | Number of tickets | [default to 1]
**travelClass** | **string** | Travel class | [default to &quot;Second Class&quot;]

### Other Parameters

Other parameters are passed through a pointer to a apiTrainsDepartureStationArrivalStationDepartureDateReturnDateNumTicketsTravelClassGetRequest struct via the builder pattern


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------







### Return type

 (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)

