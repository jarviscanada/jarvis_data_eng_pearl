# Introduction
In this project, I learned how to use JDBC by implementing a Data Access Object (DAO) that selects rows from joined tables. This project established a solid understanding to the design patterns of JDBC. I understood how DAO pattern leverages Data Transfer Object (DTO) to transfer the data between different classes and performs CRUD (create, read, update, delete) operations on DTOs. 


# ER Diagram
![Image of ERDiagram](https://raw.githubusercontent.com/jarviscanada/jarvis_data_eng_pearl/feature/dataAccess/core_java/jdbc/assets/ERDiagram.png)


# Design Patterns

Data access object (DAO) is an object that provides an abstract interface for a certain type of database or other persistence mechanism. By mapping application calls to the data source layer, DAO provides some specific data operations without exposing database details. 

Repository pattern is a kind of container where data access logic is stored. It hides the details of data access logic from business logic. In other words, we allow business logic to get access to the data object without having knowledge of underlying data access architecture.

Repository pattern allows you to store one piece of data in a separate database in order to speed up a single table look-up and then furthermore enhance global distributions. In this case, if you want to do complex table joins and your database is highly normalized, DAO will be your better choice.

