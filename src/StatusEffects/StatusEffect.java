
package StatusEffects;

import Model.GameFigure;

/**
 *
 * @author Garrett A. Clement
 */
public abstract class StatusEffect {

protected GameFigure gameFigure;
protected int damage;
protected long timeApplied;
protected int duration;
protected boolean isFinished = false;

public StatusEffect(GameFigure gameFigure)
{
    this.gameFigure = gameFigure;
    this.damage = 0;
    this.timeApplied = System.currentTimeMillis();
}

public StatusEffect(GameFigure gameFigure, int damage)
{
    this.gameFigure = gameFigure;
    this.damage = damage;
    this.timeApplied = System.currentTimeMillis();
}

public StatusEffect(GameFigure gameFigure, int damage, int duration)
{
    this.gameFigure = gameFigure;
    this.duration = duration;
    this.damage = damage;
    this.timeApplied = System.currentTimeMillis();
}

public abstract void applyEffect(GameFigure target);

public abstract boolean isFinished();    

public abstract StatusEffect clone();

public StatusEffect clone(StatusEffect clone){
    clone.damage = this.damage;
    clone.isFinished = isFinished;
    clone.duration = this.duration;
    clone.gameFigure = this.gameFigure;
    clone.timeApplied = System.currentTimeMillis();
    return clone;
}

}
  