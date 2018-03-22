/**
 * This interface is common to both cds, dvds and vhs'
 * and allows them to rewind and fast forward, in their own ways.
 * 
 * @ Kate Grabowski
 * @ Last Updated 17/08/06
 */
public interface isSkipTrackable
{
    int rewind(int trackNo, int i);
    int fastForward(int trackNo, int i);
}
