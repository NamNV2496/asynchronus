# asynchronous


## Enable async config

`@EnableAsync`

create a bean with special name: `asyncExecutor`

![img_4.png](img_4.png)

with data:

![img_1.png](img_1.png)


Call async custom config by:

![img_9.png](img_9.png)

    GET http://localhost:8080/test2 

![img_3.png](img_3.png)

# Use EAAsync

![img_6.png](img_6.png)

to run with async and handle the return value. We use `thenApply()`

![img_7.png](img_7.png)

    GET http://localhost:8080/eaAsync

![img_8.png](img_8.png)

Ref: https://viblo.asia/p/lap-trinh-da-luong-voi-completablefuture-trong-java-8-6J3ZgBMLKmB