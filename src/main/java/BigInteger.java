import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BigInteger {
    private final int size=100;

    private int intArray[] = new int[size];

    public void printBigInteger()

    {

        for (int i=0; i< size; i++) {

            System.out.print(intArray[i]);

        }

        System.out.println();

    }

    public BigInteger add(BigInteger value)

    {

        BigInteger bigSum = new BigInteger();

        int carry = 0;

        for(int i = size - 1; i >= 0; i--)

        {

            int tmp = intArray[i] + value.intArray[i] + carry;

            carry = tmp/10;

            bigSum.intArray[i] = tmp%10;

        }

        return bigSum;

    }
    public BigInteger clone(BigInteger value)

    {

        BigInteger bigClone = new BigInteger();

        for(int i = 0; i < value.intArray.length; i++)

        {

            bigClone.intArray[i] = value.intArray[i];

        }

        return bigClone;

    }
    public BigInteger subtract(BigInteger value)

    {

        BigInteger bigSubstract = new BigInteger();

        BigInteger bigClone = new BigInteger();

        bigClone = clone(this);

        for(int i = size - 1; i >= 0; i--)

        {

            if(bigClone.intArray[i] < value.intArray[i])

            {

                bigClone.intArray[i - 1] = bigClone.intArray[i - 1] - 1;

                bigClone.intArray[i] = bigClone.intArray[i] + 10;

            }

            bigSubstract.intArray[i] = bigClone.intArray[i] - value.intArray[i];

        }

        return bigSubstract;

    }
    public void shift(int n)

    {

        for(int j = 0; j < n; j++)

        {

            for(int i = 1; i < size; i++)

            {

                intArray[i - 1] = intArray[i];

            }

            intArray[size - 1] = 0;

        }

    }
    public BigInteger multiply(BigInteger value)

    {

        BigInteger product = new BigInteger();

        BigInteger bigMap = new BigInteger();

        int carry = 0;

        int count = 0;

        for(int i = size - 1; i >= 0; i--)

        {

            for(int j = size - 1; j >= 0; j--)

            {

                int tmp = value.intArray[i] * intArray[j] + carry;

                carry = tmp/10;

                bigMap.intArray[j] = tmp%10;

            }

            bigMap.shift(count);

            count++;

            product = product.add(bigMap);

        }

        return product;

    }
  
    public void inputBigInteger()

    {

        BufferedReader input = new BufferedReader (new InputStreamReader(System.in));

        while(true)

        {

            try

            {

                System.out.print("enter the BigInteger: (do not pad with zeros): ");

                String str = input.readLine();

                if (str.length() > size) throw new ArithmeticException("OVERFLOW!");

                for (int i=0; i< str.length(); i++)

                {

                    intArray[size-str.length()+i] =

                            Integer.parseInt(str.substring(i, i+1));

                }

            }

            catch(NumberFormatException e)

            {

                System.out.println("Number Format Error");

                if(e.getMessage() != null)

                    System.out.println("message = " + e.getMessage());

            }

            catch(IOException e)

            {

                System.out.println("IOException");

            }

            break;

        }

    }


    public static void main(String[] args) throws IOException

    {

        BigInteger b1,b2,b3;

        b1 = new BigInteger();

        b2 = new BigInteger();

        System.out.println("input the 1st number:");

        b1.inputBigInteger();

        System.out.println("input the 2nd number:");

        b2.inputBigInteger();

        b1.printBigInteger();

        b2.printBigInteger();

        System.out.println("            =========================");

        b3 = b1.add(b2);

        System.out.print("SUM:        "); b3.printBigInteger();





        b3 = b1.subtract(b2);

        System.out.print("SUBTRACT: "); b3.printBigInteger();



        b3 = b1.multiply(b2);

        System.out.print("PRODUCT:    "); b3.printBigInteger();


    }


}
