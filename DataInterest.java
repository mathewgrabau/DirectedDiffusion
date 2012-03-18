import java.util.ArrayList;

public class DataInterest
{
  private int type;   // There are 1 to n DataInterest types in a simulation
  private int interval;
  private int duration; // Not sure what is up with this one?
  private ArrayList<Gradient> gradients;

  public DataInterest(int type, int interval, int duration)
  {
    this.type = type;
    this.interval = interval;
    this.duration = duration;
    this.gradients = new ArrayList<Gradient>();
  }

  public int getType()
  {
    return type;
  }

  public int getInterval()
  {
    return interval;
  }

  public int getDuration()
  {
    return duration;
  }

  public void addGradient(Gradient g)
  {
  }

  public void addGradient(int sender, int rate)
  {
      Gradient g = new Gradient(sender, rate);
      if (gradients.contains(g))
      {
      }
  }
}
