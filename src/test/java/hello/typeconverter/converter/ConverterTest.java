package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

//타입 컨버터 인터페이스가 단순해서 이해하기 어렵지 않을 것이다.
//그런데 이렇게 타입 컨버터를 하나하나 직접 사용하면, 개발자가 직접 컨버팅 하는 것과 큰 차이가 없다.
//타입 컨버터를 등록하고 관리하면서 편리하게 변환 기능을 제공하는 역할을 하는 무언가가 필요

//스프링은 용도에 따라 다양한 방식의 타입 컨버터를 제공한다.
//
//Converter 기본 타입 컨버터
//ConverterFactory 전체 클래스 계층 구조가 필요할 때
//GenericConverter 정교한 구현, 대상 필드의 애노테이션 정보 사용 가능
//ConditionalGenericConverter 특정 조건이 참인 경우에만 실행

//스프링은 문자, 숫자, 불린, Enum등 일반적인 타입에 대한 대부분의 컨버터를 기본으로 제공한다.
// IDE에서 Converter , ConverterFactory , GenericConverter 의 구현체를 찾아보면 수 많은 컨버터를 확인할 수 있다.
public class ConverterTest {

    @Test
    void stringToInteger() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer result = converter.convert("10");

        assertThat(result).isEqualTo(10);
    }

    @Test
    void IntegerToString() {
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String result = converter.convert(10);

        assertThat(result).isEqualTo("10");
    }

    //ip와 port를 문자열로
    @Test
    void stringToIpPort() {
        IpPortToStringConverter converter = new IpPortToStringConverter();
        IpPort source = new IpPort("127.0.0.1", 8080);
        String result = converter.convert(source);

        assertThat(result).isEqualTo("127.0.0.1:8080");
    }

    //문자열을 ip와 port로
    @Test
    void ipPortToString() {
        StringToIpPortConverter converter = new StringToIpPortConverter();
        String source = "127.0.0.1:8080";
        IpPort result = converter.convert(source);

//        @Equalshashcode로 참조값이 달라도 값만 같다면 동일한걸로 쳐줌
        assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080));
    }
}
