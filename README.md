# Spring Boot For ELK Stack(Elasticsearch, Logstash, Kibana)



### Warning!
>
>`spring-data-elasticsearch` 의존성을 사용할 경우 4.x 버전 이전과 이후의 사용방법에서의 차이점이 존재함.
>이 프로젝트는 4.x 버전 이전의 사용방법을 기준으로 작성되었음.
>
>가장 크게 다른점은 아래와 같다.
> 
>> + 설정
>> - 4.x 이후: RestClient 및 RestHighLevelClient 를 사용하여 설정해야함
>> 
>> - 동적쿼리 (Dynamic Query) 사용시
>>    - 4.x 이전: spring-data-es 의 CriteriaQuery 를 사용하여 동적쿼리를 작성할 수 있음
>>        - RestClient 및 RestHighLevelClient 를 사용하여 설정 하고 + `spring-data-jpa` 의존성을 혼용하여, 
>>        동적쿼리와, data-jpa의 Repository 기능을 혼용하여 사용할 수 있음
>>        * 이경우 설정은 `추가 설정 방법` 항목에서 확인 가능
>>        
>>    - 4.x 이후: BoolQueryBuilder, NativeQuery, QueryBuilder 등을 사용하여 동적쿼리를 작성해야함
>    
>  
 
>+ #### 추가 설정 방법 
>> 'spring-data-jpa', 'spring-data-elasticsearch' 의존성을 혼용하여 사용할 경우
>> `@EnableJpaRepositores`(Main Class), `@EnableElasticsearchRepositories`(Config 파일내부) 2가지를 사용하게 되는데 이때 Exception 발생함.
>> 이를 해결하기 위해 `@EnableJpaRepositories` 에 `excludeFilters` 를 사용하여 `ElasticsearchRepository` 를 제외시켜야함
>>> ```java
>>> @SpringBootApplication
>>> @EnableJpaRepositories(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ElasticsearchRepository.class))
>>> public class Application { ... }
>>> ```
>>> ** 이때, 특정 Class 만 제외가 아닌 모든 ElasticsearchRepository 제외 시켜야 하기때문에 
>>> FilterType.REGEX 를 사용하거나, JPA 를 사용하는 Class 를 따로 패키지 분리하여 사용하는 방법이 있음
