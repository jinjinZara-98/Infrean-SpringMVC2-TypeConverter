package hello.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

//스프링이 중간에 타입 변환기를 사용해서 타입을 String Integer 로 변환해주었기 때문에 개발자는
//편리하게 해당 타입을 바로 받을 수 있다. 앞에서는 문자를 숫자로 변경하는 예시를 들었지만, 반대로 숫자를
//문자로 변경하는 것도 가능하고, Boolean 타입을 숫자로 변경하는 것도 가능하다.
// 만약 개발자가 새로운 타입을 만들어서 변환하고 싶으면 어떻게

//스프링은 확장 가능한 컨버터 인터페이스를 제공한다.
//개발자는 스프링에 추가적인 타입 변환이 필요하면 이 컨버터 인터페이스를 구현해서 등록하면 된다.
//이 컨버터 인터페이스는 모든 타입에 적용할 수 있다. 필요하면 X Y 타입으로 변환하는 컨버터
//인터페이스를 만들고, 또 Y X 타입으로 변환하는 컨버터 인터페이스를 만들어서 등록하면 된다.
//예를 들어서 문자로 "true" 가 오면 Boolean 타입으로 받고 싶으면 String Boolean 타입으로 변환되도록 컨버터 인터페이스를 만들어서 등록하고,
// 반대로 적용하고 싶으면 Boolean String 타입으로 변환되도록 컨버터를 추가로 만들어서 등록하면 된다.
//> 참고
//> 과거에는 PropertyEditor 라는 것으로 타입을 변환했다. PropertyEditor 는 동시성 문제가 있어서
//타입을 변환할 때 마다 객체를 계속 생성해야 하는 단점이 있다. 지금은 Converter 의 등장으로 해당
//문제들이 해결되었고, 기능 확장이 필요하면 Converter 를 사용

//Converter 라는 이름의 인터페이스가 많으니 조심해야 한다.
//org.springframework.core.convert.converter.Converter 를 사용

//문자를 숫자로 변환하는 타입 컨버터
@Slf4j
public class StringToIntegerConverter implements Converter<String, Integer> {

    @Override
    public Integer convert(String source) {
        log.info("convert source={}", source);

        //문자를 숫자로 바꾼 값을 반환환
       return Integer.valueOf(source);
    }
}
