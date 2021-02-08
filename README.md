# BankTransferApi
Api services to manage transactions between accounts

Used the spring boot framework and hibernate to manage backend and DI

Used h2 database for in memory database

Used lombok framework to autogenerate my constructors/getters/setters for my entity classes. For intelij please also install the lombok plug in
 
## Architecture
To make the code readable and maintainable I structured it in modularised layers with each having their own function (business layer, persistence layer and storage)
 
## Error handling
Designed custom error handling logic and Exception class to better capture the issues and present them to the client in a user friendly way.
 
## Testing
Wrote junit tests to test the functionality of my endpoints and the error handling
