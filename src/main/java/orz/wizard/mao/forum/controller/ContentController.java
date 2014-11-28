package orz.wizard.mao.forum.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/showcontent")
public class ContentController {
	
	@RequestMapping(value = {""}, method = RequestMethod.POST)
	public String showContent(@RequestParam(value = "content") String content, Map<String, Object> model){
		model.put("content", content);
		return "showcontent";
	}
}
