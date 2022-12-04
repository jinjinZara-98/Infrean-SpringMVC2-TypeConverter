package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

//@ModelAttribute 타입 변환 예시

//@ModelAttribute UserData data
//class UserData {
// Integer data;
//}

//@RequestParam 와 같이, 문자 data=10 을 숫자 10으로 받을 수 있다.

//@PathVariable 타입 변환 예시
///users/{userId}
//@PathVariable("data") Integer data
//URL 경로는 문자다. /users/10 여기서 10도 숫자 10이 아니라 그냥 문자 "10"이다.
//data를 Integer타입으로 받을 수 있는 것도 스프링이 타입 변환을 해주기 때문

//스프링의 타입 변환 적용 예
//스프링 MVC 요청 파라미터
//@RequestParam , @ModelAttribute , @PathVariable
//@Value 등으로 YML 정보 읽기
//XML에 넣은 스프링 빈 정보를 변환
//뷰를 렌더링 할 때
@RestController
public class HelloController {

    @GetMapping("/hello-v")
    public String helloV(HttpServletRequest request) {
        String data = request.getParameter("data");//문자 타입 조회

        //HTTP 요청 파라미터는 모두 문자로 처리된다. 따라서 요청 파라미터를 자바에서 다른 타입으로 변환해서
        //사용하고 싶으면 다음과 같이 숫자 타입으로 변환하는 과정을 거쳐야 한다.

        Integer intValue = Integer.valueOf(data); //숫자 타입으로 변경
        System.out.println("intValue = " + intValue);

        return "ok";
    }

    //스프링 MVC으로 쓸 때

    //앞서 보았듯이 HTTP 쿼리 스트링으로 전달하는 data=10 부분에서 10은 숫자 10이 아니라 문자 10이다.
    //스프링이 제공하는 @RequestParam 을 사용하면 이 문자 10을 Integer 타입의 숫자 10으로 편리하게 받을 수 있다.
    //이것은 스프링이 중간에서 타입을 변환

    //컨트롤러 호출하기 전에 타입에 맞는 컨버터 호출해서 타입 변환해줌, webconfig에 컨버터를 추가해서

    //StringToIntegerConverter 를 등록하기 전에도 이 코드는 잘 수행되었다.
    //그것은 스프링이 내부에서 수 많은 기본 컨버터들을 제공하기 때문이다.
    //컨버터를 추가하면 추가한 컨버터가 기본컨버터 보다 높은 우선순위를 가진다.
    //data=10,000 넘어오면 포맷터 적용, 문자를 숫자로

    //"10000" > 10000  "10, 000" > 10000
    @GetMapping("/hello-v1")
    public String helloV1(@RequestParam Integer data) {
        System.out.println("data = " + data);
        return "ok";
    }

    @Data
    class UserData {
        Integer data;
    }

    @GetMapping("/hello-v2")
    public String helloV2(@ModelAttribute UserData data) {
        System.out.println("data = " + data);
        return "ok";
    }

    @GetMapping("/hello-v3/{data}")
    public String helloV3(@PathVariable("data") Integer data) {
        System.out.println("data = " + data);
        return "ok";
    }

    //직접 만든 컨버터가 잘 동작하기 위해 테스트
    //컨버터가 컨버팅을 해서 컨트롤러 호출 할 때 IpPort객체 만들어
    //컨트롤러 호출 될 때 넣어준다
    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort ipPort) {
        System.out.println("ipPort IP = " + ipPort.getIp());
        System.out.println("ipPort PORT = " + ipPort.getPort());
        return "ok";
    }
}
//스프링이 중간에 타입 변환기를 사용해서 타입을 String Integer 로 변환해주었기 때문에 개발자는 편리하게 해당 타입을 바로 받을 수 있다.
//앞에서는 문자를 숫자로 변경하는 예시를 들었지만, 반대로 숫자를 문자로 변경하는 것도 가능하고, Boolean 타입을 숫자로 변경하는 것도 가능하다.
//만약 개발자가 새로운 타입을 만들어서 변환하고 싶으면 conveter패키지의 컨버터들처럼 직접 컨버터 만듬