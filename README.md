# Http Client

Http Client для работы с api. <br>
Базой для клиента является <a href='http://hc.apache.org/httpclient-3.x/'>**Apache Http Client**</a>


##Подключение

Для того, чтобы начать использовать клиент, нужно в своем файле pom.xml добавить следующие строки:<br>

* в раздел properties(если такового не существует - добавить):
```xml
<properties>
    <http.client.version>latest_version</http.client.version>  
</properties>
<!--тут вместо latest-version нужно указать последуюю версию(версию можно увидеть в файле version.txt)-->
```
* в раздел dependencies(если такового не существует - добавить):


```xml
<dependencies>
    <dependency>
        <groupId>com.perfecttest.libs</groupId>
        <artifactId>http-client</artifactId>
        <version>${http.client.version}</version>
    </dependency>
</dependencies>
```

* в директории вашего проекта вызвать

        $ mvn clean install
    
Всё. Теперь вы можете использовать клиента.    

##Пример

* GET запрос:

```java
public class YourTest {
    public void someMethod(){
        HttpRequest
                        .build()
                        .withRequestLoggers(new ConsoleRequestLogger(), new AllureRequestLogger())
                        // добавляет логгирование запроса в консоль и 
                        // в аллюр отчет. Можно использовать по отдельности
                        .withResponseLoggers(new ConsoleResponseLogger(), new AllureResponseLogger()) 
                        // добавляет логгирование ответа в консоль и 
                        // в аллюр отчет. Можно использовать по отдельности
                        .setUrl("http://yandex.ru") //устанавливает адрес
                        .sendGet() //посылает запрос
                        .checkCode(HttpStatus.SC_OK); //проверяет код
    }
}
```

