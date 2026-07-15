## Spring Testing and CI/CD.

### Disclaimer

This is a repository intended for learning CI/CD and testing with Spring.

### To-Do

- [x] Builds and pushes an Image with Actions.

- [x] Added a teeny tiny repository unit test. 

- [x] [Unit test examples](https://github.com/k0st1e/cicd-demo/tree/master/src/test/java/dev/kostie/cicddemo/cats/repository) of _Arrange_, _Act_ and, _Assert_ in a `@Repository` layer.

- [x] Testing [examples](https://github.com/k0st1e/cicd-demo/tree/master/src/test/java/dev/kostie/cicddemo/todo) of libraries:

    * JUnit
    * AssertJ
    * Hamcrest
    * JSONAssert/JsonPath
    * Mockito with MockMvc

- [x] `RestTestClient` [examples](https://github.com/k0st1e/cicd-demo/tree/master/src/test/java/dev/kostie/cicddemo/rest):

    * `.bindToController`.
    * `.bindToController` w/ `TodoService` mocked-out.
    * `.bindTo(mockMvc)` w/ `TodoService` mocked-out. Some App Context.
    * `.bindToApplicationContext` w/ full _App Context_ except Server. `TodoService` is not mocked.
    * `.bindToServer()` to show server binding with an API.

- [x] Testcontainer [example](https://github.com/k0st1e/cicd-demo/tree/master/src/test/java/dev/kostie/cicddemo/containers):

    * Separate test application properties for Hibernate to create and drop.
    * Spins a PostgreSQL container and runs an integration test.
    * REST Assured library used.