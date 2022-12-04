package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//뷰 템플릿에 컨버터 적용하기
//이번에는 뷰 템플릿에 컨버터를 적용하는 방법을 알아보자.
//타임리프는 렌더링 시에 컨버터를 적용해서 렌더링 하는 방법을 편리하게 지원한다.
//이전까지는 문자를 객체로 변환했다면, 이번에는 그 반대로 객체를 문자로 변환하는 작업을 확인
@Controller
public class ConverterController {

    //컨버터가 자동으로 적용되는 케이스1
    @GetMapping("/converter-view")
    public String converterView(Model model) {
        model.addAttribute("number", 10000);
        model.addAttribute("ipPort", new IpPort("127.0.0.1", 8080));

        return "converter-view";
    }

    //컨버터가 자동으로 적용되는 케이스2
    @GetMapping("/converter/edit")
    public String converterForm(Model model) {
        //ip와 port가 들어있는 IpPort객체를 form에다가 넘겨 form을 모델에 담아 넘김
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        Form form = new Form(ipPort);
        model.addAttribute("form", form);

        //뷰 템플릿인 타임피는 문자를 출력하므로 객체를 문자로 바꿔줌 컨버터 적용, th:field는
        return "converter-form";
    }

    //값이 넘어오는, ipPoert가 들어있는 Form form가 넘어가는
    //@ModelAttribute 가 내부적으로 변환할 때 컨버전서비스 사용
    @PostMapping("/converter/edit")
    public String converterEdit(@ModelAttribute Form form, Model model) {
        //값을 가져옴, 입력폼에 있던 값이 문자이기 때문에 문자를 IpPort객체로 변환해야함
        IpPort ipPort = form.getIpPort();
        model.addAttribute("ipPort", ipPort);

        return "converter-view";
    }

    @Data
    static class Form {
        private IpPort ipPort;

        public Form(IpPort ipPort) {
            this.ipPort = ipPort;
        }
    }
}
