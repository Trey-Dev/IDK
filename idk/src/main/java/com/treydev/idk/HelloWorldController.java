package com.treydev.idk;

import java.util.ArrayList;
// import java.util.List;

// import com.treydev.myClass;

// import org.springframework.session.Session;
// import org.springframework.boot.builder.SpringApplicationBuilder;
// import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {
    //@RequestMapping(value="/")
    //@ResponseBody
    @GetMapping("/")
    public String helloWorld(Model model)
    {
        return "MainPage";
        //TODO: STORE PARAMETER Uname INTO SESSION VARIABLES
    }

    @PostMapping("game")
    public String game(@RequestParam("name") String name, @RequestParam("action") String action, Model model)
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

        model.addAttribute("gameLog", gameLog);
        model.addAttribute("actionList", actionList);
        model.addAttribute("name", name);

        return "Game";
    }

    @GetMapping("Inventory")
    public String menu(Model model)
    {
        //TODO: in actual implementation, something to get menu type
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
    public String consume(@RequestParam("name") String name,Model model)
    {
        //TODO: in actual implementation, something to get menu type
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
    public String combat(Model model)
    {
       //TODO replace stubbed code with actual implementation.
        if(CombatHandler.CombatOptions == null)
            CombatHandler.initializeStubbed();
        
        model.addAttribute("CombatOutput", CombatHandler.outputs);
        model.addAttribute("FightOptions", CombatHandler.CombatOptions);
        return "Combat";
    }
    @PostMapping("EnterCombat")
    public String combat(@RequestParam("Option") String Option, Model model)
    {
       //TODO replace stubbed code with actual implementation.
        // ArrayList<String> combatOutput = new ArrayList<String>();
        // //combatOutput.add("A wild something attacks.");
        // combatOutput.add("You decide to "+ Option);
        CombatHandler.takeAction(Option);
       model.addAttribute("CombatOutput", CombatHandler.outputs);
       
    //    ArrayList<String> FightOptions = new ArrayList<String>();
    //    FightOptions.add("Just fricking punch him, I guess.");
       model.addAttribute("FightOptions", CombatHandler.CombatOptions);

       
        return "Combat";
    }
    

}
