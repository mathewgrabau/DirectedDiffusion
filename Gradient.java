

public class Gradient implements Comparable<Gradient>
{
  private int sender; // ID of the sender
  private int rate;

  public Gradient(int sender, int rate)
  {
    this.sender = sender;
    this.rate = rate;
  }

  public int getSender()
  {
    return sender;
  }

  public int getRate()
  {
    return rate;
  }

  public boolean equals(Object other)
  {
    boolean isEqual = false;
    Gradient g;

    if (other != null && other instanceof Gradient)
    {
      g = (Gradient)other;
      isEqual = (sender == g.sender) && (rate == g.rate);
    }

    return isEqual;
  }

  // required by the Comparable interface
  public int compareTo(Gradient g)
  {
    if (equals(g))
    {
      return 0;
    }
    else
    {
      // perform the required 
      if (sender < g.sender)
      {
        return -1;
      }
      else if (sender > g.sender)
      {
        return 1;
      }
      else
      {
        // only getting here when the senders are the same.
        // check the rate
        if (rate < g.rate)
        {
          return -1;
        }
        else if (rate > g.rate)
        {
          return 1;
        }
        else
        {
          return 0;
        }
      }
    }
  }
}
