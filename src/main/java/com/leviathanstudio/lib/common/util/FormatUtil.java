package com.leviathanstudio.lib.common.util;

public class FormatUtil
{
    public static String toLatinNumber(final int nbr)
    {
        final StringBuilder rtn = new StringBuilder();
        int temp = nbr;
        if (temp >= 100)
            while ((temp / 100) > 0)
            {
                rtn.append("C");
                temp /= 100;
            }
        if (temp >= 50)
            while ((temp / 50) > 0)
            {
                rtn.append("L");
                temp /= 50;
            }
        if (temp >= 10)
            while ((temp / 10) > 0)
            {
                rtn.append("X");
                temp /= 10;
            }
        switch (temp)
        {
            case 9:
                rtn.append("IX");
                break;
            case 8:
                rtn.append("VIII");
                break;
            case 7:
                rtn.append("VII");
                break;
            case 6:
                rtn.append("VI");
                break;
            case 5:
                rtn.append("V");
                break;
            case 4:
                rtn.append("IV");
                break;
            case 3:
                rtn.append("III");
                break;
            case 2:
                rtn.append("II");
                break;
            case 1:
                rtn.append("I");
                break;
            default:
                break;
        }
        return rtn.toString();
    }
}
