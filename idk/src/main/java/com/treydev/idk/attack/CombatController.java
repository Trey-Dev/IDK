

@Controller
public class CombatController{
    
    @GetMapping("EnterCombat")
    public String combat(Model model, HttpSession session) {
        if (PlayerParty == null) {
            PlayerParty = Critter.initializeStubbedParty(); // TODO: replace with non stubbed party eventually
        }
        if (sessionCombatHandler == null) {
            sessionCombatHandler = CombatHandler.initializeStubbed();
            for (Critter creature : PlayerParty)
                creature.enterCombat();// TODO: call from combatHandler
        }
        // sessionCombatHandler.updateCombatOptions();

        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("CombatOutput", sessionCombatHandler.outputs);
        model.addAttribute("FightOptions", sessionCombatHandler.CombatOptions);
        return "Combat";
    }

    @PostMapping("EnterCombat")
    public String combat(@RequestParam("Option") String Option, Model model, HttpSession session) {
        sessionCombatHandler.takeAction(Option);
        model.addAttribute("CombatOutput", sessionCombatHandler.outputs);
        model.addAttribute("FightOptions", sessionCombatHandler.CombatOptions);
        model.addAttribute("username", session.getAttribute("username"));

        return "Combat";
    }



}