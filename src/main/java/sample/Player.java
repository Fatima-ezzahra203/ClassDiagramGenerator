package sample;

import me.fatimaezzahra.annotations.ParentClass;

import java.util.Collection;

@ParentClass()
public interface Player {

    double getHealth();

    double getDamage();

    Collection<String> getAbilities();

}
