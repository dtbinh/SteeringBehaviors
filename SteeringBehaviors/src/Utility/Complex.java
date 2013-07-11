package Utility;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class Complex
  {

    public double real;
    public double imaginary;

    public Complex()
      {
        real = 0;
        imaginary = 0;
      }

    public Complex(double real, double imag)
      {
        this.real = real;
        imaginary = imag;
      }

    public void setValues(double real, double imag)
      {
        this.real = real;
        imaginary = imag;
      }

    public double getRe()
      {
        return real;
      }

    public double getIm()
      {
        return imaginary;
      }

    public Complex times(Complex c)
      {
        double tempReal = ((c.getRe() * real) - (c.getIm() * imaginary));
        double tempImag = ((c.getIm() * real) + (c.getRe() * imaginary));

        Complex tempCom = new Complex(tempReal, tempImag);

        return tempCom;
      }

    public Complex plus(Complex c)
      {
        double tempReal = (real + c.getRe());
        double tempImag = (imaginary + c.getIm());

        Complex tempCom = new Complex(tempReal, tempImag);

        return tempCom;
      }

    public String toString()
      {
        String s = ("Real = " + real + " Imag = " + imaginary + "i");
        return s;
      }

    public double abs()
      {
        return Math.sqrt(((real * real) + (imaginary * imaginary)));
      }
  }
