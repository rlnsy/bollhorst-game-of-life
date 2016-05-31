package src;

import src.game_world_elements.*;

public class Unlocker
{
    public void process(World world) {
        for(WorldElement elemA : world.getElements()) {
            for(WorldElement elemB : world.getElements()) {
                if(elemA.isTouching(elemB))
                    processCombo(elemA,elemB);
            }
            for(WorldElement elemB : world.getSecondaryElements()) {
                if(elemA.isTouching(elemB))
                    processCombo(elemA,elemB);
            }
        }
    }
    
    public void processCombo(WorldElement elemA, WorldElement elemB) {
    }
}
