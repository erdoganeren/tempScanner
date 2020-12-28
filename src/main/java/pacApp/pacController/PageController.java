package pacApp.pacController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pacApp.pacDao.IMeasurement;
import pacApp.pacModel.Measurement;
import pacApp.pacModel.Scan;
import pacApp.pacSocket.SocketServer;

@Controller
public class PageController {
	SocketServer ss = new SocketServer(); 
	@Autowired
	private IMeasurement measurementDao; 
	private String tmpValue = "0";
	
    @GetMapping("/monitor")
    public String index(Model model) {
    	Scan t = new Scan(ss.getSocketTempValue()); 
    	if (t.getTempValue() > 38.7) {
    		model.addAttribute("message", "Ihr Körpertemperatur ist zu HOCH!");        	
    	}  else if(t.getTempValue() > 34.00)  {
    		model.addAttribute("message", "Ihr Körpertemperatur ist normal!");  
    	} else {
    		model.addAttribute("message", "Ihr Körpertemperatur ist zu niedrig!");  
    	}
    	if (t.getTempValue() < 26)
    		model.addAttribute("tmpValue", " - ");
    	else
    		model.addAttribute("tmpValue", t.getTempValueString());
    	tmpValue = t.getTempValueString();
    	measurementDao.save(new Measurement(t.getTempValue(), t.getDistanceValue()));
    	return "monitor";
    }
    
    @GetMapping("/uebersicht")
    public String uebersichtPage(Model model) {
    	model.addAttribute("measurementList", measurementDao.findAll());
    	model.addAttribute("tmpValue", tmpValue);
    	model.addAttribute("risikoCount", getRisikoMeasurementList().size());
    	return "uebersicht";
    }
    
    @GetMapping("/resetMesurements")
    public String resetMesurements(Model model) {
    	measurementDao.deleteAll();
    	model.addAttribute("measurementList", measurementDao.findAll());
    	model.addAttribute("tmpValue", tmpValue);
    	model.addAttribute("risikoCount", getRisikoMeasurementList().size());
        return "uebersicht";
    }
    
    
    @GetMapping("/test")
    public String indextest(Model model) {
    	model.addAttribute("test", "Hallo Welt");
        return "index";
    }
    
    
    
    private List<Measurement> getRisikoMeasurementList(){
    	return measurementDao.findAll().stream().filter(e -> e.getRisk() == Boolean.TRUE).collect(Collectors.toList());
    }
   
}