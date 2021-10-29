# transactions-aggregator
Transactions Manager Interview Project

### INSTALLATION OF INTERVIEW TRANSACTION PROJECT by Marco A Keller ###

Requirements:
1. Java 11
2. Maven
3. Docker

Run: 
1. mvn clean install
2. docker-compose up --build

a. To update service: docker-compose up -d --no-deps --build <service_name>

All services exports the respective ports to localhost, follow are the URIs to access each Spring projects:

1. To access SWAGGER documentation for the services:
   <!--To Be Implemented-->

2. To access Spring Eureka server:
	<!--To Be Implemented-->

3. To access Zipkin Tracing server:
	 <!--To Be Implemented-->

4. To post a list of transactions:
	http://localhost:8082/transaction
```yaml
[
{
  "date":"11-12-2012",
  "type":"credit",
  "amount":"100.00"
},
{
  "date":"11-12-2012",
  "type":"credit",
  "amount":"200.00"
}
]
```

5. To get transaction by type and date:
	http://localhost:8082/transaction/credit/11-12-2012

6. To access Spring Cloud Config Server:
	 <To Be Implemented>
	

