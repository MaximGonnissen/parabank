# GraphWalker tests

## Generating model interfaces

To generate model interfaces, run the generate-test-sources Maven goal in the project
directory: `mvn org.graphwalker:graphwalker-maven-plugin:4.3.2:generate-test-sources`

> [!NOTE]
>
> The generated model interfaces are located in the `target/generated-test-sources/graphwalker/org/graphwalker`
> directory.

## Model implementations

Implementation of the models can be found in the [modelimpl](modelimpl) directory.

## Running the tests

To run the tests, one can execute the tests in the `com.parasoft.parabank.graphwalker` package, in
[GraphWalkerIT](GraphWalkerIT.java).