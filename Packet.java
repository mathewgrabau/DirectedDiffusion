import java.sql.Date;

public class Packet
{
  private char packetType;
  private int senderID;
  private double seqNumber;
  private Date interval;

  public Packet(char packetType, int senderID, double seqNumber, Date interval)
  {
    this.packetType = packetType;
    this.senderID = senderID;
    this.seqNumber = seqNumber;
    this.interval = interval;
  }

  public char getPacketType(){
    return packetType;
  }

  public int getSenderID(){
    return senderID;
  }

  public double getSeqNum(){
    return seqNumber;
  }

  public Date getInterval(){
    return interval;
  }
}
