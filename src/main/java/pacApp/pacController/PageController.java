package pacApp.pacController;

import java.net.Socket;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pacApp.pacSocket.SocketServer;
import pacModel.Temp;

@Controller
public class PageController {
	SocketServer ss = new SocketServer(); 
	
    @GetMapping("/")
    public String index(Model model) {
    	Temp t = new Temp(ss.getSocketTempValue());
    	if (t.getValue() > 39.00) {
    		model.addAttribute("message", "Ihr Körpertemperatur ist zu HOCH!");        	
    	}  else if(t.getValue() > 34.00)  {
    		model.addAttribute("message", "Ihr Körpertemperatur ist normal!");  
    	} else {
    		model.addAttribute("message", "Ihr Körpertemperatur ist zu niedrig!");  
    	}
    	if (t.getValue() < 26)
    		model.addAttribute("test", " - ");
    	else
    		model.addAttribute("test", t.toString());
    	return "index";
    }
    @GetMapping("/test")
    public String indextest(Model model) {
    	model.addAttribute("message", "hallo");
        return "index";
    }
   
}