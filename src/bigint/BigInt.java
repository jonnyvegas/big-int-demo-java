package bigint;

import java.util.Scanner;

/**
 * BigInt.java
 * COMP 182
 * October 6, 2014
 * Prof. Putnam
 * This program and class is able to take a large number, called BigInt, and 
 * perform the operations addition and multiplication. It can handle large
 * numbers up to 100 digits in length.
 * @author Jonathan Villegas
 */
public class BigInt 
{
    //Max size of the BigInt
    final int MAX_SIZE = 100;
    private char[] theDigits = new char[MAX_SIZE];
    private int numOfDigits = 0;
    
    //Constructor for BigInt
    public BigInt(String val)
    {
        int indexForVal = 0;
        //Start at the end, store number as chars in reverse order.
        for(int i = val.length() - 1; i >= 0; i--)
        {
            theDigits[indexForVal] = val.charAt(i);
            numOfDigits++;
            indexForVal++;
        }
    }

    /**
     * Prints the number.
     */
    public void printInt()
    {
        for(int i = numOfDigits - 1; i >= 0; i--)
        {
            System.out.print(theDigits[i]);
        }
        System.out.println("");
    }
    
    

    /**
     * Returns the number of digits in the character array for the number.
     * @return the number of digits in the char array.
     */
    public int getNumOfDigits()
    {
        return numOfDigits;
    }
    
    @Override
    public String toString()
    {
        String theNum = "";
        boolean nonZeroFound = false;
        for(int i = numOfDigits - 1; i >= 0; i--)
        {
            //If a digit in the array isn't zero, the number isn't zero.
            if(theDigits[i] != '0')
            {
                nonZeroFound = true;
            }
            //Once a nonzero is found, add the digit to the end of the string.
            if(nonZeroFound)
            {
                theNum = theNum + theDigits[i];
            }
        }
        //If a nonzero was not found, the number is 0.
        if(!nonZeroFound)
        {
            theNum = "0";
        }
        return theNum;
    }
    
    

    /**
     * Precondition: A BigInt calls max with a valid BigInt val.
     * Postcondition: The larger of the two numbers is returned. If they are
     * equal, it does not matter which is returned.
     * @param val the second value to compare
     * @return The greater of the two BigInts.
     */
        public BigInt max(BigInt val)
    {
        int theSmaller = 0;
        if(this.getNumOfDigits() > val.getNumOfDigits())
        {
            //This is so we don't go out of array bounds.
            theSmaller = val.getNumOfDigits();
            for(int i = 0; i < theSmaller; i++)
            {
                //Get each value from the array of chars.
                int x = Character.getNumericValue(this.theDigits[i]);
                int y = Character.getNumericValue(val.theDigits[i]);
                //this is greater than val.
                if(x > y)
                {
                    return this;
                }
                //val is greather than this.
                else if(y > x)
                {
                    return val;
                }
            }
            //If they are equal, return this.
            return this;
        }
        else if(val.getNumOfDigits() >= this.getNumOfDigits())
        {
            //This is so we don't go out of array bounds.
            theSmaller = this.getNumOfDigits();
            for(int i = 0; i < theSmaller; i++)
            {
                int x = Character.getNumericValue(this.theDigits[i]);
                int y = Character.getNumericValue(val.theDigits[i]);
                //This is greater than val
                if(x > y)
                {
                    return this;
                }
                //val is greater than this
                else if(y > x)
                {
                    return val;
                }
            }
            //If they are equal, val is returned.
            return val;
        }
        //If equal, this is returned.
        return this;
    }
    
    /**
     * Precondition: A BigInt calls min with a valid BigInt val.
     * Postcondition: The smaller of the two numbers is returned. If they are
     * equal, it does not matter which is returned.
     * @param val the second value to compare
     * @return The smaller of the two BigInts.
     */   
    public BigInt min(BigInt val)
    {
        int theSmaller = 0;
        if(this.getNumOfDigits() < val.getNumOfDigits())
        {
            //Do not step out of the array bounds for val.
            theSmaller = this.getNumOfDigits();
            for(int i = 0; i < theSmaller; i++)
            {
                int x = Character.getNumericValue(this.theDigits[i]);
                int y = Character.getNumericValue(val.theDigits[i]);
                //this is smaller.
                if(x < y)
                {
                    return this;
                }
                //val is smaller.
                else if(y < x)
                {
                    return val;
                }
            }
            //If equal, return this.
            return this;
        }
        else if(val.getNumOfDigits() <= this.getNumOfDigits())
        {
            //Do not go out of array bounds for this.
            theSmaller = val.getNumOfDigits();
            for(int i = 0; i < theSmaller; i++)
            {
                int x = Character.getNumericValue(this.theDigits[i]);
                int y = Character.getNumericValue(val.theDigits[i]);
                //this is smaller.
                if(x < y)
                {
                    return this;
                }
                //val is smaller.
                else if(y < x)
                {
                    return val;
                }
            }
            //If equal, returen val.
            return val;
        }
        //If equal, return this.
        return this;
    }

    /**
     * Adds two BigInts.
     * Precondition: a valid BigInt calls add with a parameter of a valid
     * BigInt val.
     * Postcondition: a BigInt is returned that is the sum of the calling
     * BigInt and val.
     * @param val The second value to add.
     * @return The sum of the two values.
     */
        public BigInt add(BigInt val)
    {
        int theSmaller = 0;
        int carry = 0;
        int theTotal = 0;
        String theSum = "";
        if(this.getNumOfDigits() < val.getNumOfDigits())
        {
            theSmaller = this.getNumOfDigits();
        }
        else if(val.getNumOfDigits() <= this.getNumOfDigits())
        {
            theSmaller = val.getNumOfDigits();
        }
        for(int i = 0; i < theSmaller; i++)
        {
            //Get the integer values from each array.
            int x = Character.getNumericValue(this.theDigits[i]);
            int y = Character.getNumericValue(val.theDigits[i]);
            if(carry == 1)
            {
                theTotal = x + y + carry;
                carry = 0;
            }
            else
            {
                theTotal = x + y;
            }
            if(theTotal >= 10)
            {
                carry = 1;
                theTotal = theTotal - 10;
                //Put the char into theSum string.
                char ch = (char) ('0' + theTotal);
                theSum = theSum + ch;
            }
            else
            {
                char ch = (char) ('0' + theTotal);
                theSum = theSum + ch;
            }
        }
        if(theSmaller == val.getNumOfDigits())
        {
            for(int i = theSmaller; i < this.getNumOfDigits(); i++)
            {
                if(carry == 1)
                {
                    int theNum = Character.getNumericValue(this.theDigits[i]);
                    theNum = theNum + 1;
                    char ch = (char) ('0' + theNum);
                    theSum = theSum + ch;
                    carry = 0;
                }
                else
                {
                    theSum = theSum + this.theDigits[i];
                }
            }
        }
        else
        {
            for(int i = theSmaller; i < val.getNumOfDigits(); i++)
            {
                if(carry == 1)
                {
                    int theNum = Character.getNumericValue(val.theDigits[i]);
                    theNum = theNum + 1;
                    char ch = (char) ('0' + theNum);
                    theSum = theSum + ch;
                    carry = 0;
                }
                else
                {
                    theSum = theSum + val.theDigits[i];
                }
            }
        }
        String theSum2;
        //The string is forwards, it must be stored backwards.
        theSum2 = new StringBuilder(theSum).reverse().toString();
        BigInt theInt = new BigInt(theSum2);
        return theInt;
    }
    
    

    /**
     * Multiplies two BigInts together and returns the product.
     * Precondition: a BigInt calls multiply with a valid BigInt val.
     * Postcondition: The two BigInts are multiplied together and their
     * product is returned.
     * @param val The second value to be multiplied.
     * @return The product of the two BigInts.
     */
        public BigInt multiply(BigInt val)
    {
        BigInt theNum = new BigInt("");
        int carry = 0;
        int theResult = 0;
        //Set each char in theNum to 0 to initialize.
        for(int i = 0; i < MAX_SIZE; i++)
        {
            theNum.theDigits[i] = '0';
        }
        for (int i = 0; i < val.getNumOfDigits(); i++)
        {
            //Start from each part of i to multiply by every other number.
            for(int j = i; j < this.getNumOfDigits() + i; j++)
            {
                int x = Character.getNumericValue(this.theDigits[i]);
                //Going from back to front and skipping each position after
                //we are done with it.
                int y = Character.getNumericValue(val.theDigits[j - i]);
                int z = Character.getNumericValue(theNum.theDigits[j]);
                theResult = z + (x * y) + carry;
                carry = theResult / 10;
                theResult = theResult % 10;
                theNum.theDigits[j] = (char) ('0' + theResult);
            }
            //Need to remember to account for extra carry.
            if(carry > 0)
            {
                int j = this.getNumOfDigits() + i - 1;
                int digit = Character.getNumericValue(theNum.theDigits[j]) + carry;
                carry = (digit / 10);
                digit = digit % 10;
                theNum.theDigits[j] = (char) ('0' + digit);
            }
            theNum.numOfDigits = val.getNumOfDigits() + this.getNumOfDigits();
        }
        return theNum;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter two numbers separated by a + or *: ");
        String input = in.nextLine();
        //Remove all spaces from the string.
        input = input.replaceAll("\\s+","");
        String largeNum1;
        String largeNum2;
        BigInt first;
        BigInt second;
        BigInt result;
        boolean correctData = false;
        //Do the correct operation and output the result.
        for(int i = 0; i < input.length(); i++)
        {
            if(input.charAt(i) == '+')
            {
                largeNum1 = input.substring(0,i);
                largeNum2 = input.substring(i + 1, input.length());
                first = new BigInt(largeNum1);
                second = new BigInt(largeNum2);
                result = first.add(second);
                System.out.println("  " + first);
                System.out.println("+ " + second);
                System.out.println("-----------");
                System.out.println("  " +result);
                correctData = true;
                BigInt third = first.max(second);
                BigInt fourth = first.min(second);
                System.out.println("The larger is: " + third);
                System.out.println("The smaller is: " + fourth);
            }
            else if(input.charAt(i) == '*')
            {
                largeNum1 = input.substring(0, i);
                largeNum2 = input.substring(i + 1, input.length());
                first = new BigInt(largeNum1);
                second = new BigInt(largeNum2);
                result = first.multiply(second);
                System.out.println("  " + first);
                System.out.println("* " + second);
                System.out.println("------------");
                System.out.println("  " + result);
                correctData = true;
                BigInt third = first.max(second);
                BigInt fourth = first.min(second);
                System.out.println("The larger is: " + third);
                System.out.println("The smaller is: " + fourth);
            }
        }
        //Correct data was not input.
        if(!correctData)
            System.out.println("Invalid data entered.");
        
        
    }
}