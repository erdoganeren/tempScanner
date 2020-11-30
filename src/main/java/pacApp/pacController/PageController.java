package pacApp.pacController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    Temp tempEsp32;
    @GetMapping("/tempscan")
    public ResponseEntity<String> tempscan(@RequestParam String temperature, Model model) {
    	System.out.println(temperature);
    	Temp t = new Temp(temperature);
    	tempEsp32 = t;
    	return new ResponseEntity<>("index", HttpStatus.OK);
    }    
    @GetMapping("/index")
    public String indexEsp32(Model model) {
    	if (tempEsp32 != null) {
	    	if (tempEsp32.getValue() > 39.00) {
	    		model.addAttribute("message", "Ihr Körpertemperatur ist zu HOCH!");        	
	    	}  else if(tempEsp32.getValue() > 34.00)  {
	    		model.addAttribute("message", "Ihr Körpertemperatur ist normal!");  
	    	} else {
	    		model.addAttribute("message", "Ihr Körpertemperatur ist zu niedrig!");  
	    	} 
	    	if (tempEsp32.getValue() < 20) 
	    		model.addAttribute("test", " - ");
	    	else
	    		model.addAttribute("test", tempEsp32.toString());
    	}
    	return "index";
    }
    
    @GetMapping("/test")
    public String indextest(Model model) {
    	model.addAttribute("test", "Hallo Welt");
        return "index";
    }
   
}