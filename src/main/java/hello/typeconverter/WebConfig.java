package hello.typeconverter;

import hello.typeconverter.converter.IntegerToStringConverter;
import hello.typeconverter.converter.IpPortToStringConverter;
import hello.typeconverter.converter.StringToIntegerConverter;
import hello.typeconverter.converter.StringToIpPortConverter;
import hello.typeconverter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//웹 애플리케이션에 Converter 를 적용

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //스프링은 내부에서 ConversionService 를 제공한다.
    //우리는 WebMvcConfigurer 가 제공하는 addFormatters() 를 사용해서 추가하고 싶은 컨버터를 등록하면 된다.
    //이렇게 하면 스프링은 내부에서 사용하는 ConversionService 에 컨버터를 추가

    //포맷터는 컨버터의 확장된 기능을 갖고있음
    @Override
    public void addFormatters(FormatterRegistry registry) {

        //포맷터를 쓰기 위해 주석처리
        //MyNumberFormatter도 숫자를 문자로, 문자를 숫자로 바꾸기 때문에 동일
        //컨버터가 우선순위 더 높기 때문에
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        //포맷터 추가, convertor-view 값에 쉼표 들어가있음
        registry.addFormatter(new MyNumberFormatter());
    }
}
