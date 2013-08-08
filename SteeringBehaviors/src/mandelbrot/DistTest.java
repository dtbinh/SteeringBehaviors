package mandelbrot;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class DistTest
{
    //Scaling example for two ranges of numbers 

    public static void main(String args[])
    {
         double ubData = 1000;
         double lbData = 0;
         
         double ubScaled = 1;
         double lbScaled = -2.5;
        
        
        //Scale 0 - 1000 to -1 to 1
        for(float x = 0; x < ubData; x++){
            //The algorithm is (x - lower bound) / (upper bounds - lower bound)
            double scaled = ((ubScaled - lbScaled) * (x - lbData)) / (ubData-lbData) + lbScaled;
            System.out.println(scaled);
        }
    }
}
