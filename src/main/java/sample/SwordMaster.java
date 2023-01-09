package sample;

import me.fatimaezzahra.annotations.ChildClass;

import java.util.ArrayList;
import java.util.Collection;

@ChildClass(parentClass = "Player")
public class SwordMaster implements Player {
    @Override
    public double getHealth() {
        return 500;
    }

    @Override
    public double getDamage() {
        return 100;
    }

    @Override
    public Collection<String> getAbilities() {
        return new ArrayList<>();
    }

    public void attack() {

    }
}
