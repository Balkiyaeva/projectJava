Application is created for Online Store, where Customer can See Products, Brands, Purchase Products, See Orders, Search Products by classifications.
For purchasing products it is necessary to authorise. 
All SQL statements were created in java. 
There is 1 interface and 6 classes for creating tables; 3 classes of tables; 1 class for DB connection; 1 class for Menu; 1 class of Main. (=12 classes+1 interface)
All actions are going in Menu. There are several methods. At first, start() method runs. It is created for all Customers to See Brands and Products, Search and to Purchase. When Customer wants to Buy Products, he/she goes through authorisation by Signing In/Up. After authorisation, more options are available: See Order, Purchase Products. There is a delivery service. Therefore, Customer has to input the information about his/her addresses. 

Interface is Called Commands and all 6 classes of creating tables and 3 classes of tables implement it. Commands contains 2 methods: createTables(), insertValues();
3 classes are constant: createProducts, createBrands, and createClassifications.
3 classes of Tables: Delivery, Customers, and Orders are inserting values during the application's running.
