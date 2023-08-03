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
