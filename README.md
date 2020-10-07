# omni-4-demo-jaeger-tracing

Для подключения трассировки в клиентский сервис необходимо:
- Подключить зависимости (см. dependencies)
- Сконфигурировать трассировку для приложения через application.yml
- Соблюдать требования архитектуры

Для запуска инфраструктуры необходимы следующие сервисы:
- jaeger-agent (сбор данных о трассировке из приложения)
- jaeger-collector (получение данных от jaeger-agent и отправка в kafka)
- zookeeper (для kafka)
- kafka (буффер трассировок)
- jaeger-ingester (чтение сообщений из kafka и отправка в elasticsearch)
- elasticsearch (бд трассировок)
- jaeger-query (запрос данных из elasticsearch, встроен jaeger-UI)

# Конфигурация удалённого сервиса
Необходимо заполнить значения окружения jaeger-tracing-demo-client для указания адреса вызываемого удалённого сервиса 

    remote:
      service:
        host: ${REMOTE_SERVER_URL}
        port: ${REMOTE_SERVER_PORT}
        path: ${REMOTE_SERVER_PATH}

# Конфигурация трассировки клиентского сервиса
Необходимо указать адрес агента, на который будут отправляться данные по трассировке

    opentracing:
      jaeger:
        # Имя, с которым сервис будет отображаться на UI для jaeger-query
        service-name: jaeger-tracing-demo-client
        enabled: true
        udp-sender:
          host: jaeger-agent
          port: 6831
        # Рекомендации архитектуры:
        log-spans: true
      # Согласно требованиям информационной безопасности должно быть отключено трассирование SQL-запросов:
      spring:
        cloud:
          jdbc:
            enabled: false
     
# Требования по именованию приложений
Каждому приложению, согласно требований архитектуры должно быть выдано имя

    spring:
      application:
        name: jaeger-tracing-demo-client

# Требования по содержанию трассировок
Требование архитектуры: в трассировках не допускается любая персональная информация

# Предварительное создание topic-а в kafka
В kafka в промышленной эксплуатации не будет доступно автоматическое создание topic-ов, поэтому соответствующий
topic должен быть предварительно создан до запуска сервисов.

# RestTemplate
RestTemplate должен быть поднят как Bean для того, чтобы к нему могла быть добавлена трассировка jaeger-ом

# Dependencies
Для клиентского приложения должны быть добавлены следующие зависимости для подключения трассировки.

        <!-- Jaeger -->
        <dependency>
            <groupId>io.jaegertracing</groupId>
            <artifactId>jaeger-client</artifactId>
            <version>${jaeger-client.version}</version>
        </dependency>
        <dependency>
            <groupId>io.opentracing.contrib</groupId>
            <artifactId>opentracing-spring-jaeger-cloud-starter</artifactId>
            <version>${opentracing-spring-jaeger-cloud-starter.version}</version>
        </dependency>
        
   или
        
        implementation 'io.jaegertracing:jaeger-client:${jaeger-client.version}'
        implementation 'io.opentracing.contrib:opentracing-spring-jaeger-cloud-starter:${opentracing.jaeger.version}'
        
# Архив презентации, показанной на демо
Добавлен в корне архив: `Jaeger-tracing-demo-presentation.pptx.zip`