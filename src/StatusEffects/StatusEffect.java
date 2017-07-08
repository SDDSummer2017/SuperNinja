
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
protected long duration;
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

}
  