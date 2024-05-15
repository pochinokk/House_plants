# Интернет-магазин домашних растений
Проект представляет собой интернет-магазин домашних растений, в котором присутствуют страницы:
* _Главная_
* _Авторизация_
* _Регистрация_
* _Каталог_
* _Создание заказа_
* _Личный кабинет пользователя_
* _Личный кабинет администратора_
* _О растениях_
* _Контактная информация_

В данном Web-приложении пользователь может зарегистрироваться, авторизоваться, сделать заказ, а также узнать информацию о растениях и контактную информацию. Для администратора создан личный кабинет, где он может с удобством управлять информацией из базы данных.

Зависимости проекта:
 ```
 dependencies{
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity6'
	compileOnly 'org.projectlombok:lombok'
	developmentOnly 'org.springframework.boot:spring-boot-devtools'
	runtimeOnly 'com.h2database:h2'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.security:spring-security-test'
}
 ```

Команда для запуска:
 ```
  java -jar jars/House_plants-1.0.jar
 ```
