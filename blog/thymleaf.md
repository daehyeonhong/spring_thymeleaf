# Thymeleaf

## 1. 타임리프란?

타임리프는 백엔드 서버에서 `HTML`을 동적으로 렌더링 해주는 `SSR Template Engine`이다.<br/>
`JSP`와 같은 `Template Engine`이지만, `JSP`와는 다르게 `HTML`을 렌더링 해주기 때문에 `HTML`파일 자체를 실행하여 확인할 수 있다.<br/>
또한 `Thymeleaf`는 `Spring Boot`와 호환성이 좋고, `Spring Boot`에서 공식적으로 권장하는 `Template Engine`이다.
이렇게 `순수 HTML`을 그대로 유지하며 `ViewTemplate`도 사용할 수 있는 타임리프의 특징을 `Natural Template`이라고 한다.

```
SSR이란?
`SSR:Server Side Rendering`은 `HTML`을 서버에서 렌더링 해주는 것을 말한다.
(Ex. JSP, Thymeleaf, Freemarker)
반대로 클라이언트에서 렌더링 해주는 것을 `CSR: Client Side Rendering`이라고 한다.
(Ex. React, Vue)
```

## 2. `Text`: `text`, `utext`

`Thymeleaf`에서는 `HTML`을 렌더링 할 때, `HTML`의 `Text`를 그대로 출력하거나, `HTML`의 `Text`를 `HTML`로 인식하여 렌더링 할 수 있다.<br/>
먼저 "안녕하세요 ><!!"라는 `HTML`을 렌더링 해본다 가정하면, `Thymeleaf`에서는 다음과 같이 렌더링 된다.

```html
<!-- HTML -->
안녕하세요 &gt;&lt;&#33;&#33;
```

전달하는 값을 `HTML Entity`로 인식하여 렌더링하기 위해 `escape` 처리하여 출력한다.<br/>
`escape` 처리를 하지 않으면 `HTML`의 `Text`를 `HTML`로 인식하여 렌더링하기 때문에, `HTML`의 `Text`를 그대로 출력하기 위해서는 `escape` 처리를 해야한다.<br/>
`Thymeleaf`에서는 `escape` 처리를 위해 `th:text`와 `th:utext`를 제공한다.<br/>
`th:text`는 `HTML`의 `Text`를 `HTML Entity`로 인식하여 렌더링하고, `th:utext`는 `HTML`의 `Text`를 `HTML`로 인식하여 렌더링한다.<br/>

### `HTML Entity`

`HTML`은 `Hyper Text Markup Language`의 약자로, `Hyper Text`를 `Markup`하는 언어이다.<br/>
`Markup`이란 `HTML`에서 사용하는 태그를 말하는데, `HTML`에서는 `
<태그명>`과 같이 `<`와 `>`로 감싸서 사용한다.<br/>
`HTML`에서 사용하는 마크업 태그와 다른 텍스트를 구분하기 위해 `HTML Entity`를 사용한다.<br/>

#### `HTML Entity`의 종류

| `HTML Entity` | `HTML` | `Description`  |
|:-------------:|:------:|:--------------:|
|    `&lt;`     |  `<`   |  `less than`   |
|    `&gt;`     |  `>`   | `greater than` |
|    `&amp;`    |  `&`   |  `ampersand`   |
|   `&quot;`    |  `"`   |  `quotation`   |
|   `&apos;`    |  `'`   |  `apostrophe`  |
|   `&nbsp;`    |  ` `   | `non-breaking` |
|   `&para;`    |  `¶`   |  `paragraph`   |
|   `&copy;`    |  `©`   |  `copyrigth`   |
|    `&reg;`    |  `®`   |  `registered`  |

```html
<!-- HTML Entity -->
`&lt;` : `<`
`&gt;` : `>`
`&amp;` : `&`
`&quot;` : `"`
`&apos;` : `'`
`&nbsp;` : ` `
`&para;` : `¶`
`&copy;` : `©`
`&reg;` : `®`
```

### *주의!*

실제 서비스에서는 `HTML`의 `Text`를 `HTML`로 인식하여 렌더링하는 것은 보안상 위험하다.<br/>
기본적으로는 `th:text`를 사용하여 `HTML`의 `Text`를 `HTML Entity`로 인식하여 렌더링하는 것이 좋고,<br/> `HTML`의 `Text`를 `HTML`로 인식하여 렌더링해야하는
경우에만 `th:utext`를 사용하는 것이 좋다.

## 3. `Expression`

### 3.1. `Variable Expression`: `${...}`

`Thymeleaf`에서는 `Expression`을 사용하여 `HTML`을 렌더링 할 수 있다.<br/>
`Expression`은 `Thymeleaf`에서 제공하는 `Object`를 사용하여 `HTML`을 렌더링 할 수 있도록 해준다.<br/>
`Expression`은 다음과 같이 사용할 수 있다.

```html
<!-- HTML -->
<span th:text="${name}"></span>
```

`SpEL`과 함께 사용하여 `Map`, `List`와 같은 `Collection`을 사용할 수 있다.

```html
<!-- HTML -->
<span th:text="${user['username']}"></span>
<span th:text="${user.username}"></span>
<span th:text="${users[0]}"></span>
<span th:text="${users[0].username}"></span>
```

### 3.2. `기본 객체`

`Thymeleaf`에서는 기본적으로 `HttpServletRequest`, `HttpServletResponse`, `HttpSession`, `ServletContext`와 같은 `Object`를 제공
했었지만, `3.1`버전부터 사용할 수 없게 되었다.
[참조링크](https://www.thymeleaf.org/doc/articles/thymeleaf31whatsnew.html)<br/>
이외에도 여러가지 편의 객체를 제공한다.

|   `Object`   |                          `Description`                          |
|:------------:|:---------------------------------------------------------------:|
|   `param`    | `HttpServletRequest`의 `getParameter()`를 사용하여 `Parameter`를 가져온다. |
|  `session`   |              `HttpSession`을 사용하여 `Session`을 가져온다.               |
| `@beansName` |                    `Spring`에서 `Bean`을 가져온다.                     |

### 3.3 `Utility Object`

`Thymeleaf`에서는 다양한 `Utility Object`를 제공하는데, 그 중 `#temporals`를 사용하여 `Date`를 다양한 형식으로 변환할 수 있다.

### 3.4. `URL Expression`: `@{...}`

|                             `URL Expression`                              |                   `Description`                   |
|:-------------------------------------------------------------------------:|:-------------------------------------------------:|
|                                `@{/path}`                                 |                  `Context Path`                   |
|              `@{/path(param1=${param1}, param2=${param2})}`               |         `Context Path`, `QueryParameter`          |
| `@{/path/{param1}(param1=${param1}, param2=${param2}, param3=${param3})}` | `Context Path`, `Path Variable`, `QueryParameter` |

### 3.5 `Literal`: `''`

`Thymeleaf`에서 `Literal`은 항상 `''`을 사용하여 감싸줘야 한다.

```html
<!-- HTML -->
<span th:text="${'Hello'}"></span>
```

하지만 예외적으로 공백 없이 쭉 이어진다면, 의미있는 하나의 토큰으로 인지하여 `''`를 사용하지 않아도 된다.<br/>
예시: `A-Z`, `a-z`, `0-9`, `_`, `[]` 등

**오류**

```html
<!-- HTML -->
<span th:text="${Hello, World!}"></span>
```

### 3.6 `Expression`

|  `Expression`  |           `Description`            |
|:--------------:|:----------------------------------:|
|    `산술 연산자`    |      `+`, `-`, `*`, `/`, `%`       |
|  `비교 연산자 기호`   |  `>`, `<`, `>=`, `<=`, `==`, `!=`  |
|  `비교 연산자 키워드`  | `gt`, `lt`, `ge`, `le`, `eq`, `ne` |
|    `논리 연산자`    |            `and`, `or`             |
|    `조건 연산자`    |               `? :`                |
|   `엘비스 연산자`    |                `?:`                |
| `No-Operation` |                `_`                 |
|    `연결 연산자`    |                `+`                 |

## 4. `Thymeleaf`의 `Attribute`

### 4.1 `th:`

`Thymeleaf`의 `Attribute`는 `th:`를 사용하여 정의한다.<br/>
`th:` 태그에 할당된 값이 `HTML` 기본 태그를 덮어쓰게 된다.

### 4.2 `th:attrappend`, `th:attrprepend`, `th:classappend`

`th:attrappend`, `th:attrprepend`, `th:classappend`는 `HTML`의 `Attribute`를 추가할 때 사용한다.<br/>
`th:attrappend`와 `th:attrprepend`는 할당된 값을 그대로 추가하는 반면, `th:classappend`는 `HTML`의 `Attribute`에 할당된 값과 `Thymeleaf`
의 `Attribute`에 할당된 값을 공백을 두고 추가한다.

### 4.3 `th:checked`

`HTML`에서는 `check` 태그의 값과 상관 없이 태그 자체가 존재하면 `checked`가 된다.<br/>
하지만 `Thymeleaf`에서는 `check` 태그의 값에 따라 `checked`가 되도록 할 수 있다.

```html
<!-- HTML -->
<input type="checkbox" th:checked="${user.enabled}"/>
```

### 4.4 `th:each`

`th:each`는 `Collection`을 순회할 때 사용한다.
반복하고 있는 상태를 알려주는 기능도 제공한다.

```html

<tr th:each="user, userStat : ${users}">
    <td th:text="${userStat.count}">username</td>
    <td th:text="${user.username}">username</td>
    <td th:text="${user.age}">0</td>
    <td>
        index = <span th:text="${userStat.index}"></span>
        count = <span th:text="${userStat.count}"></span>
        size = <span th:text="${userStat.size}"></span>
        even? = <span th:text="${userStat.even}"></span>
        odd? = <span th:text="${userStat.odd}"></span>
        first? = <span th:text="${userStat.first}"></span>
        last? = <span th:text="${userStat.last}"></span>
        current = <span th:text="${userStat.current}"></span>
    </td>
</tr>
```

반복시 오른쪽 컬렉션`${users}`의 `user`를 왼쪽의 `user`에 할당한다.<br/>
반복의 상태를 알려주는 `userStat`를 사용할 수 있는데, 이는 생략하여 사용할 수 있다.<br/>
다만 생략하면 지정한 변수명`${fieldNameStat}`으로 자동 지정된다.

### 4.5 `th:if`, `th:unless`

`th:if`는 조건이 `true`일 때, `th:unless`는 조건이 `false`일 때 실행된다.

### 4.6 `comment`

`Thymeleaf`에서는 `HTML` `<!-- -->`를 사용하여 주석을 작성할 수 있다.<br/>
더불어 파서 주석과, 프로세서 주석을 사용할 수 있다.

```html

<!--/* [[${data}]] */-->

<!--/*-->
<span th:text="${data}">html data</span>
<!--*/-->
<!--/*/
<span th:text="${data}">html data</span>
/*/-->
```

`<!--/* [[${data}]] */-->`는 `Thymeleaf`의 파서 주석이다.<br/>
`<!--/*`와 `*/-->` 사이에 작성된 내용은 `Thymeleaf`가 파싱하지 않는다.<br/>
`<!--/*/ /*/-->`는 `Thymeleaf`의 프로세서 주석이다.<br/>
`<!--/*/`와 `/*/-->` 사이에 작성된 내용은 `HTML` 파일에서는 주석처리되지만, `Thymeleaf`가 파싱하여 실행한다.

### 4.7 `th:block`

`th:block`은 `HTML`의 `div` 태그와 같은 역할을 한다.<br/>
기본적인 `th:each`의 기능으로 해결할 수 없는 경우, `th:block`을 사용하여 해결할 수 있다.

```html

<th:block th:each="user : ${users}">
    <span th:text="${user.username}">username</span>
    <span th:text="${user.age}">0</span>
</th:block>
```

### 4.8 `th:inline`

`th:inline="javascript"`는 `Thymeleaf`의 `Expression`을 `JavaScript`로 사용할 수 있게 해준다.<br/>
기본적으로 변수의 타입에 맞게 `JavaScript`의 타입이 지정된다.<br/>
뿐만아니라 `Thymeleaf`의 `Natural Template` 기능도 사용할 수 있다.<br/>
반복문을 사용해야하는 경우, 아래와 같은 코드를 사용해야 한다.

```thymeleafexpressions
<script th:inline="javascript">
    [# th:each="user, stat : ${users}"]
    const user[[${stat.count]] = [[${user}]];
    [/]
</script>
```
