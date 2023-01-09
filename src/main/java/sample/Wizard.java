package sample;

import me.fatimaezzahra.annotations.ChildClass;

import java.util.ArrayList;
import java.util.Collection;

@ChildClass(parentClass = "Player")
public class Wizard implements Player{
    @Override
    public double getHealth() {
        return 400;
    }

    @Override
    public double getDamage() {
        return 150;
    }

    @Override
    public Collection<String> getAbilities() {
        return new ArrayList<>();
    }

    public void attack() {

    }
}
