package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

//문자를 ipport로 바꾸는
@Slf4j
public class StringToIpPortConverter implements Converter<String, IpPort> {

    @Override
    public IpPort convert(String source) {
        log.info("convert source={}", source);

        //"127.0.0.1:8080" -> IpPort 객체
        String[] split = source.split(":");

        String ip = split[0];
        int port = Integer.parseInt(split[1]);

        return new IpPort(ip, port);
    }
}
//디버그 브레이크 포인트를 걸어서 확인
//서버를 띄우고 상단 우측에 디버거 모드로 실행해서 웹에서 호출하면 콘솔에 디버거를 보면 됨
