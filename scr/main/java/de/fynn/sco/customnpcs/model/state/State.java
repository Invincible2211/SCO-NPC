package main.java.de.fynn.sco.customnpcs.model.state;

import main.java.de.fynn.sco.customnpcs.control.hook.quests.QuestState;
import net.minecraft.server.v1_16_R1.Position;

/**
 * @author Freddyblitz
 * @version 1.0
 */
public class State {

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private final Position position;

    private final QuestState questState;

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    /**
     * Der Konstruktor benoetigt beim Erstellen eines State Objekts einmal die Position, die durch diesen Zustand
     * eingenommen werden soll und er benoetigt einen Queststatus, der in diesem Zustand aktiv sein soll
     */
    public State(Position position, QuestState questState){
        this.position = position;
        this.questState = questState;
    }

    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/

    public Position getPosition() {
        return position;
    }

    public QuestState getQuestState() {
        return questState;
    }

}
