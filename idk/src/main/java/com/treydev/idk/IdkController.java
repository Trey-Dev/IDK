package com.treydev.idk;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import com.treydev.idk.location.*;

@Controller
public class IdkController {

    @GetMapping("/")
    public String login(Model model)
    {
        return "Login";
    }

    @PostMapping("Login")
    public String login(@RequestParam("name") String name, HttpSession session, Model model) {
        model.addAttribute("name", name);
        session.setAttribute("name", name);
        return "City";
    }

    @GetMapping("Game")
    @PostMapping("Game")
    public String game(@RequestParam("action") String action, Model model, HttpSession session)
    {
        ArrayList<String> gameLog = new ArrayList<String>();
        gameLog.add("LineOne");
        gameLog.add("LineTwo");
        gameLog.add("LineThree");
        gameLog.add("LineFour");
        gameLog.add(action);

        ArrayList<String> actionList = new ArrayList<String>();
        actionList.add("Action 1");
        actionList.add("Action 2");
        actionList.add("Action 3");
        actionList.add("Action 4");

        model.addAttribute("name", session.getAttribute("name"));
        model.addAttribute("gameLog", gameLog);
        model.addAttribute("actionList", actionList);
        model.addAttribute("name", session.getAttribute("name"));

        return "Game";
    }

    //!!! These are the persistent memory items for temporary stubs
    public static CombatHandler sessionCombatHandler; //TODO: replace with SESSION VARIABLES!!!
    public static ArrayList<Critter> PlayerParty;

    @GetMapping("Inventory")
    public String menu(Model model, HttpSession session)
    {
        model.addAttribute("name", session.getAttribute("name"));
        model.addAttribute("TableName", "Inventory");
        if(Item.inventory == null)
        {
            Item.inventory = new ArrayList<Item>();
            Item.inventory.add(new Item("Red Potion", (short) 15, "Smells kinda fishy"));
            Item.inventory.add(new Item("Pink Potion", (short) 11, "Smells kinda beefy"));
        }
        model.addAttribute("Table", Item.inventory);
        return "Inventory";
    }

    @PostMapping("Inventory")
    public String consume(@RequestParam("name") String name,Model model, HttpSession session)
    {
        model.addAttribute("name", session.getAttribute("name"));
        model.addAttribute("TableName", "Inventory");
        Item.InitializeStubbedInventory();

        String output = "";
        if(!name.isEmpty())
            output += Item.consume(name);

        output += "\nWhat would you like to do?";
        model.addAttribute("output", output);
        model.addAttribute("Table", Item.inventory);
        return "Inventory";
    }

    @GetMapping("EnterCombat")
    public String combat(Model model, HttpSession session)
    {
        if(PlayerParty == null)
            {
                PlayerParty = Critter.initializeStubbedParty(); //TODO: replace with non stubbed party eventually
            }
        if(sessionCombatHandler == null)
            {
                sessionCombatHandler = CombatHandler.initializeStubbed();
            }
        //sessionCombatHandler.updateCombatOptions();
       
        model.addAttribute("name", session.getAttribute("name"));
        model.addAttribute("CombatOutput", sessionCombatHandler.outputs);
        model.addAttribute("FightOptions", sessionCombatHandler.CombatOptions);
        return "Combat";
    }
    
    @PostMapping("EnterCombat")
    public String combat(@RequestParam("Option") String Option, Model model, HttpSession session)
    {
        sessionCombatHandler.takeAction(Option);
        model.addAttribute("CombatOutput", sessionCombatHandler.outputs);
        model.addAttribute("FightOptions", sessionCombatHandler.CombatOptions);
        model.addAttribute("name", session.getAttribute("name"));

        return "Combat";
    }
    
    @GetMapping("City")
    public String city(Model model, HttpSession session)
    {
        if (session.getAttribute("city") == null)
        {
            City c = City.getCityByLevel(1);
            session.setAttribute("city", c);
        }

        model.addAttribute("name", session.getAttribute("name"));

        City c = (City)session.getAttribute("city");
        model.addAttribute("cityName", c.getName());

        // Extract names for Shops, Gyms and Paths and place into the model
        model.addAttribute("shops", c.getShops());
        model.addAttribute("gyms", c.getGyms());

        ArrayList<Path> paths = Path.getPaths(c);
        ArrayList<String> p = new ArrayList<String>(paths.size());
        for( Path path : paths ) {
            p.add( path.getPathText(c) );
        }
        model.addAttribute("paths", p.toArray());

        dumpModel(model);
        return "City";
    }

    @GetMapping("Gym")
    public String Gym(Model model, HttpSession session)
    {
        // TO DO: Get ID from the passed in info... but for now, just use the first gym
        Gym g = ((City)session.getAttribute("city")).getGyms().get(0);
        model.addAttribute("gymName", g.getName());
        return "Gym";
    }

    @GetMapping("Shop")
    public String shop(Model model, HttpSession session)
    {
        // TO DO: Get ID from the passed in info... but for now, just use the first shop
        Shop s = ((City)session.getAttribute("city")).getShops().get(0);
        model.addAttribute("shopName", s.getName());
        return "Shop";
    }

    @GetMapping("Path")
    public String path(Model model, HttpSession session)
    {
        // TO DO: Get ID from the passed in info... but for now, just use the first shop
        City city = (City)session.getAttribute("city");
        ArrayList<Path> paths = Path.getPaths(city);
        Path p = paths.get(0);
        model.addAttribute("pathName", p.getPathText(city));
        return "Path";
    }

    private void dumpModel(Model model)
    {
        for(String key : model.asMap().keySet())
        {
            System.out.println(key + ": " + model.asMap().get(key));
        }
    }
}
