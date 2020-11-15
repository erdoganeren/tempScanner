package pacApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.ModelAndView;
@SpringBootApplication
public class StartMachine {

	public static void main(String[] args) {
		SpringApplication.run(StartMachine.class, args);	
		 ModelAndView mav = new ModelAndView("index");
		 mav.addObject("test", 1);
	}

}
