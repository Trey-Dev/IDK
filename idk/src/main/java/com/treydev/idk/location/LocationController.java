package com.treydev.idk.location;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpSession;

@Controller
public class LocationController {
    @GetMapping("City")
    public String city(Model model, HttpSession session) {
        if (session.getAttribute("city") == null) {
            City c = City.getCity(1);
            session.setAttribute("city", c);
        }

        model.addAttribute("username", session.getAttribute("username"));

        City c = (City) session.getAttribute("city");
        model.addAttribute("cityName", c.getName());
        model.addAttribute("leader", c.getLeader());
        model.addAttribute("population", c.getPopulation());

        // Extract text and Id for Shops/Gyms/Paths and add to model
        HashMap<String, Integer> shopHash = new HashMap<String, Integer>();
        ArrayList<Shop> shops = c.getShops();
        shops.forEach(shop -> shopHash.put(shop.getName(), shop.getId()));
        model.addAttribute("shops", shopHash);

        HashMap<String, Integer> gymHash = new HashMap<String, Integer>();
        ArrayList<Gym> gyms = c.getGyms();
        gyms.forEach(gym -> gymHash.put(gym.getName(), gym.getId()));
        model.addAttribute("gyms", gymHash);

        HashMap<String, String> pathHash = new HashMap<String, String>();
        ArrayList<Path> paths = Path.getPaths(c);
        paths.forEach(path -> pathHash.put(path.getPathText(c), c.getId() + "." + path.getId()));
        model.addAttribute("paths", pathHash);

        dumpModel(model);
        return "City";
    }

    @GetMapping("/Gym/{id}")
    public String gym(Model model, HttpSession session, @PathVariable(value = "id") int id) {
        Gym g = Gym.getById(id);
        model.addAttribute("gymName", g.getName());
        model.addAttribute("trainerName", g.getOwner());
        model.addAttribute("critters", g.getCritters());
        return "Gym";
    }

    @GetMapping("/Shop/{id}")
    public String shop(Model model, HttpSession session, @PathVariable(value = "id") int id) {
        Shop s = Shop.getById(id);
        model.addAttribute("shopName", s.getName());
        return "Shop";
    }

    @GetMapping("/Path/{id}")
    public String path(Model model, HttpSession session, @PathVariable(value = "id") String id) {
        City city = (City) session.getAttribute("city");
        Path p = Path.getById(id);
        model.addAttribute("pathName", p.getPathText(city));

        StringBuilder sb = new StringBuilder();
        City destination = p.getOtherCity(city);
        sb.append("You walk towards ");
        sb.append(destination.getName());
        sb.append("... ");
        for (int i = 0; i < p.getDistance(); i++) {
            sb.append("and walk... ");
            //TODO: replace with function to check for random encounters
            /*
             * This code should check for random encounters, and if one is found, it should
             * break out of the loop and return the random encounter page, passing the distance
             * travelled into the session variables so that the player can return to the path
             * page and continue walking.
             */
        }
        sb.append("and arrive at ");
        sb.append(p.getOtherCity(city).getName());
        sb.append("!");
        model.addAttribute("pathText", sb.toString());

        model.addAttribute("otherVityName", session.getAttribute("username"));
        session.setAttribute("city", destination);

        return "Path";
    }

    private void dumpModel(Model model) {
        for (String key : model.asMap().keySet()) {
            System.out.println(key + ": " + model.asMap().get(key));
        }
    }
}
