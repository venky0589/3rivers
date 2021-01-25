#	3Rivers MicroServices
## Kafka commands
##### Start zookeeper:
```bash
$ bin/zookeeper-server-start.sh config/zookeeper.properties
```

##### Start kafka server:
```bash
$ bin/kafka-server-start.sh config/server.properties
```

##### Create Topics:
```bash
$ bin/kafka-topics.sh --create --topic feed_transaction --zookeeper localhost:2181 --partitions 1 --replication-factor 1

$ bin/kafka-topics.sh --create --topic feed_account --zookeeper localhost:2181 --partitions 1 --replication-factor 1
```

##### List available topics:
```bash
$ bin/kafka-topics.sh --zookeeper localhost:2181 --list
```

##### Console producers:
```bash
$ bin/kafka-console-producer.sh --topic feed_transaction --bootstrap-server localhost:9092

$ bin/kafka-console-producer.sh --topic feed_account --bootstrap-server localhost:9092
```
##### `Transaction` kafka message format:
```
{"accountNumber": "abc", "transactionTs": "2021-01-22T10:15:18.385", "type": "DEPOSIT", "amount": 100.00}
```

##### `Account` kafka message format:
```
{"accountNumber": "abc", "balance": 0.0, "lastUpdateTimestamp": "2021-01-22T10:16:17.385"}
```
## Micro Services
Available Services:
- `threerivers-eureka-server`
- `threerivers-gateway-service`
- `threerivers-account-service`
- `threerivers-transaction-service`

##### threerivers-eureka-server
```bash
# Default port 8761
$ java -jar <jar-filename>
```

##### threerivers-gateway-service
```bash
# Default port 8080
$ java -jar <jar-filename>
```
##### threerivers-account-service
```bash
# Default port 8081
$ java -jar <jar-filename>
```

##### threerivers-transaction-service
```bash
# Default port 8082
$ java -jar <jar-filename>

```
### Account Service API
```bash
# Check account balance
$ curl http://localhost:8080/api/v1/account/check-balance/{accountNumber}
```

### Transaction Service API
```bash
# Get last 'n' DAYS or MONTHS or YEARS transactions of an account
# Here transactionType request parameter is optional
$ curl http://localhost:8080/api/v1/transaction/statement/{accountNumber}/{DAYS/MONTHS/YEARS}/{n}?transactionType={DEPOSIT/WITHDRAW}

# Get Transactions in given date range of an account
# Date format yyyyMMdd Ex: 20210125
# Here transactionType request parameter is optional
$ curl http://localhost:8080/api/v1/transaction/statement-range?accountNumber={accountNumber}&from={fromDate}&to={toDate}&transactionType={DEPOSIT/WITHDRAW}
```

