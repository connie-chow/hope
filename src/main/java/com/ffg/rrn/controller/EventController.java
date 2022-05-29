/**
 * 
 */
package com.ffg.rrn.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.security.Principal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.ffg.rrn.dao.DashboardDao;
import com.ffg.rrn.model.Dashboard;
import com.ffg.rrn.model.Event;
import com.ffg.rrn.model.Property;
import com.ffg.rrn.model.Resident;
import com.ffg.rrn.service.ResidentServiceImpl;

/**
 * @author FFGRRNTeam
 *
 */
@Controller
public class EventController extends BaseController {
	
	private CopyOnWriteArrayList<Event> allEvents = new CopyOnWriteArrayList<>();

	@Autowired
	private ResidentServiceImpl residentService;
	
	//@Autowired
	//private EventDao eventDao;

	@RequestMapping(value = "/events", method = { RequestMethod.GET, RequestMethod.POST })
	public String goToEvents(Model model, Principal principal) throws Exception {

		// (1) (en)
		// After user login successfully.
		String serviceCoord = null;
		if (principal != null) {
			serviceCoord = populateSCinModel(model, principal);
		}

		//allEvents = eventService.getAllEvents();
		
		//Event a = new Event();
		//Event b = new Event();
		//allEvents.add(a);
		//allEvents.add(b);
		
		List<Event> eventList = residentService.getAllEvents();
		System.out.println("eventList= " + eventList.toString());
		model.addAttribute("eventList", eventList);

		return "events";
	}
	
	
	//@RequestMapping(value = "/addEvent", method = { RequestMethod.GET, RequestMethod.POST })
	@RequestMapping(value = "/addEvent")
	public String addEvent(@ModelAttribute Event e, Model model, Principal principal) throws Exception {

		System.out.println("addEvent called, model = " + model.toString());
		// (1) (en)
		// After user login successfully.
		String serviceCoord = null;
		if (principal != null) {
			serviceCoord = populateSCinModel(model, principal);
		}

	
		//allEvents = eventService.getAllEvents();
		//residentService.saveEvent(e);
		allEvents.add(e);
		model.addAttribute("eventList", allEvents);
		//model.addAttribute("event", e);
		
		//model.addAttribute("event", new Event());

		return "addEvent";
	}

	
	//@RequestMapping(value = "/saveEvent", method = { RequestMethod.GET, RequestMethod.POST })
	//@PostMapping("/saveEvent")
	@RequestMapping(value="/saveEvent", method=RequestMethod.POST)
	public String saveEvent(@ModelAttribute Event e, Model model, BindingResult bindingResult) throws Exception  {
		System.out.println("saveEvent called");
		// (1) (en)
		// After user login successfully.
		//String serviceCoord = null;
		//if (principal != null) {
		//	serviceCoord = populateSCinModel(model, principal);
		//}
		
//model.addAttribute("event", e);
		//allEvents.add(e);
		//model.addAttribute("eventList", allEvents);
		//System.out.println("e passed = " + e.getName() + " " + e.getDescription());
		residentService.saveEvent(e);
		
		List<Event> eventList = residentService.getAllEvents();
		System.out.println("eventList= " + eventList.toString());
		model.addAttribute("eventList", eventList);
		
		return "events";
	}
	
	
	
	@RequestMapping(value="/updateEvent", method=RequestMethod.POST)
	public String updateEvent(@ModelAttribute Event e, Model model, BindingResult bindingResult) throws Exception  {
		System.out.println("updateEvent called");
		// (1) (en)
		// After user login successfully.
		//String serviceCoord = null;
		//if (principal != null) {
		//	serviceCoord = populateSCinModel(model, principal);
		//}
		
//model.addAttribute("event", e);
		//allEvents.add(e);
		//model.addAttribute("eventList", allEvents);
		//System.out.println("e passed = " + e.getName() + " " + e.getDescription());
		System.out.println("updateEvent e = " + e.toString());
		residentService.saveEvent(e);
		
		List<Event> eventList = residentService.getAllEvents();
		//System.out.println("eventList= " + eventList.toString());
		System.out.println("residentList = " + e.getResidentList());
		model.addAttribute("eventList", eventList);
		
		return "events";
	}
	
	

	
	@RequestMapping(value = "/goToEventDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public String goToEventDetails(@RequestParam("id") Long eventId, Model model, Principal principal) throws Exception {

			// (1) (en)
			// After user login successfully.
			String serviceCoord = null;
			if (principal != null) {
				serviceCoord = populateSCinModel(model, principal);
			}

		// Grants will never be null - either "All" or some Property
		
		List<Resident> residentList = residentService.getAllResident();
		model.addAttribute("allResidents", residentList);
		
		Event e = residentService.getEventById(eventId);
		model.addAttribute("event", e);
		
		//Event e = residentService.getEventById(eventId);
		
		System.out.println("goToEventDetails - id = " + eventId);

		return "event_details";

	}


}
