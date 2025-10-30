/**
 * Class for getting time since object Timer created
 */
public class Timer 
{
    private double startTime;
    
    public Timer() 
    {
        this.startTime = System.nanoTime();
    }
    /**
     * Get diffrence in time
     */
    public int timeInt()
    {
        return (int)getCurrentTime();
    }
    public double timeDouble()
    {
        return getCurrentTime();
    }
    /**
     * Get time as hours minutes and seconds
     */
    public String timeHrMinSec()
    {
        double elapsedTime = getCurrentTime();
        //convert nano time to seconds
        double timeSec = elapsedTime / 1000000000.0;
        //convert seconds to hours/minuts/seconds
        double timeMin = timeSec / 60;
        timeSec %= 60;
        double timeHr = timeMin / 60;
        timeMin %= 60;
        return (int)timeHr + ":" + (int)timeMin + ":" + (int)timeSec; 
        
    }
    /**
     * Get amount of time that has elapsed
     */
    private double getCurrentTime()
    {
        return System.nanoTime() - this.startTime;
    }
}